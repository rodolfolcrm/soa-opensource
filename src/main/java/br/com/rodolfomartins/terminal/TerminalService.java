
package br.com.rodolfomartins.terminal;

import java.util.List;
import org.apache.axiom.om.OMElement;
import org.apache.log4j.Logger;
import br.com.rodolfomartins.integrador.IntegradorWebService;

/**
 * Descrição do Fonte
 * 
 * @author 15/09/2015: Rodolfo Martins <DD>
 */
public class TerminalService
{

   private static final Logger LOGGER = Logger.getLogger(TerminalService.class);

   private static final String ENDPOINT = "http://localhost:9763/services/ImovelDataService?wsdl";
   private static final String NAMESPACE = "ImovelDataService";
   private static final String NOMEMETODO_FINDALLTERMINAL = "findAllTerminal";

   private ConversorTerminal conversorTerminal = new ConversorTerminal();

   public Terminal[] findAll()
   {
      try
      {
         IntegradorWebService integrador = new IntegradorWebService(ENDPOINT, NAMESPACE, NOMEMETODO_FINDALLTERMINAL);

         OMElement result = integrador.envia();

         List<Terminal> terminais = conversorTerminal.converte(integrador.getNamespaceURI(), result);

         return terminais.toArray(new Terminal[terminais.size()]);
      }
      catch (Exception ex)
      {
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
