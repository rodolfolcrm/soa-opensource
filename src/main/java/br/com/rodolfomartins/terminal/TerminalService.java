
package br.com.rodolfomartins.terminal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.log4j.Logger;

/**
 * Descrição do Fonte
 * 
 * @author 15/09/2015: Rodolfo Martins <DD>
 */
public class TerminalService
{

   private static final Logger LOGGER = Logger.getLogger(TerminalService.class);

   public Terminal[] findAll()
   {
      try
      {
         LOGGER.info("findAll()");
         EndpointReference rhEndpoint = new EndpointReference("http://localhost:9763/services/ImovelDataService?wsdl");

         String nameSpace = "ImovelDataService";
         String nomeMetodo = "findAllTerminal";

         List<Terminal> lst = new ArrayList<Terminal>();

         // cria os parametros do servico com o EndPoint e o método que queremos usar
         Options options = new Options();
         options.setTo(rhEndpoint);
         options.setAction("urn:" + nomeMetodo);

         // cria um client para o servico
         ServiceClient sender = new ServiceClient();
         sender.setOptions(options);

         // para criar os parametros de entrada do servico
         OMFactory fac = OMAbstractFactory.getOMFactory();
         OMNamespace omNs = fac.createOMNamespace(
            nameSpace, // namespace do método
            nomeMetodo); // nome do método que vamos invocar
         OMElement payload = fac.createOMElement(nomeMetodo, omNs);

         // Executa o DataService
         OMElement result = sender.sendReceive(payload);

         // Recupeara o retorno
         Iterator rows = result.getChildElements();

         // Enquanto houver linhas no retorno
         while (rows.hasNext())
         {
            OMElement row = (OMElement) rows.next();

            // recupera os elementos
            OMElement ptaCod = row.getFirstChildWithName(new QName(omNs.getNamespaceURI(), "ptacod"));
            OMElement terCod = row.getFirstChildWithName(new QName(omNs.getNamespaceURI(), "tercod"));
            OMElement terIde = row.getFirstChildWithName(new QName(omNs.getNamespaceURI(), "teride"));
            OMElement terNom = row.getFirstChildWithName(new QName(omNs.getNamespaceURI(), "ternom"));

            // cria um objeto do tipo Funcionario
            Terminal ter = new Terminal();
            ter.setPtacod(Integer.parseInt(ptaCod.getText()));
            ter.setTercod(terCod.getText());
            ter.setTeride(terIde.getText());
            ter.setTernom(terNom.getText());

            // Adiciona na lista
            lst.add(ter);
         }

         // verifica se a lista possui elementos
         if (lst.size() > 0)
            return lst.toArray(new Terminal[lst.size() - 1]);
         else
            return lst.toArray(new Terminal[0]);

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
         LOGGER.error(ex.getMessage(), ex);
         throw new RuntimeException(ex.getMessage());
      }
   }

   public static void main(String[] args)
   {
      Terminal[] list = new TerminalService().findAll();

      for (Terminal terminal : list)
      {
         LOGGER.info(terminal.getTercod() + "," + terminal.getPtacod() + ", " + terminal.getTernom());
      }
   }
}
