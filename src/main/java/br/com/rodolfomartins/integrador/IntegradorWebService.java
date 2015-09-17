
package br.com.rodolfomartins.integrador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.log4j.Logger;

/**
 * Classe com inteligência para realizar a integração com WebServices utilizando Axis2.
 * 
 * @author 15/09/2015: Rodolfo Martins <DD>
 */
public class IntegradorWebService
{

   private static final Logger LOGGER = Logger.getLogger(IntegradorWebService.class);
   private EndpointReference endpointReference;
   private OMNamespace omNamespace;
   private ServiceClient serviceClient;
   private OMElement payload;

   /**
    * Inicializa o integrador.
    * 
    * @param endpoint do webService
    * @param nameSpace do webService
    * @param nomeMetodo do webService
    * @return OMNamespace
    * @throws IntegradorException
    */
   public OMNamespace inicializa(String endpoint, String nameSpace, String nomeMetodo) throws IntegradorException
   {
      try
      {
         this.endpointReference = new EndpointReference(endpoint);

         // para criar os parametros de entrada do servico
         OMFactory omFactory = OMAbstractFactory.getOMFactory();
         omNamespace = omFactory.createOMNamespace(nameSpace, nomeMetodo);
         payload = omFactory.createOMElement(nomeMetodo, omNamespace);

         // cria os parametros do servico com o EndPoint e o método que queremos usar
         Options options = new Options();
         options.setTo(endpointReference);
         options.setAction("urn:" + nomeMetodo);

         // cria um client para o servico
         serviceClient = new ServiceClient();
         serviceClient.setOptions(options);

         return omNamespace;
      }
      catch (Throwable t)
      {
         LOGGER.error(t.getMessage(), t);
         throw new IntegradorException(t);
      }
   }

   /**
    * Envia para o WebService o mapa de parâmetros informados.
    * 
    * @param parametros - parâmetros a serem enviados.
    * @return resposta do WebService.
    * @throws IntegradorException
    */
   public OMElement envia(Map<String, String> parametros) throws IntegradorException
   {
      List<OMElement> elementos = null;
      if (parametros != null)
      {
         elementos = new ArrayList<OMElement>();
         for (Map.Entry<String, String> entry : parametros.entrySet())
         {
            String chave = entry.getKey();
            String valor = entry.getValue();
            elementos.add(criaParametro(chave, valor));
         }
      }

      return envia(elementos);
   }

   /**
    * Envia para o WebService uma lista de OMElement
    * 
    * @param elementos objetos do tipo OMElement para envio
    * @return objeto OMElement de resposta do WebService
    * @throws IntegradorException
    */
   public OMElement envia(List<OMElement> elementos) throws IntegradorException
   {
      if (elementos != null)
      {
         for (OMElement elemento : elementos)
         {
            payload.addChild(elemento);
         }
      }

      return envia();
   }

   /**
    * Envia para o WebService um OMElement
    * 
    * @param elemento objeto para envio
    * @return objeto OMElement de resposta do WebService
    * @throws IntegradorException
    */
   public OMElement envia(OMElement elemento) throws IntegradorException
   {
      payload.addChild(elemento);
      return envia();
   }

   /**
    * Faz a chamada para o WebService.
    * 
    * @return objeto OMElement de resposta do WebService
    * @throws IntegradorException
    */
   public OMElement envia() throws IntegradorException
   {
      try
      {
         return serviceClient.sendReceive(payload);
      }
      catch (Throwable t)
      {
         LOGGER.error(t.getMessage(), t);
         throw new IntegradorException(t);
      }
   }

   /**
    * Cria um objeto OMElement a partir da chave/valor informados.
    * 
    * @param chave
    * @param valor
    * @return objeto OMElement
    */
   private OMElement criaParametro(String chave, String valor)
   {
      OMFactory omFactory = OMAbstractFactory.getOMFactory();
      OMElement parametro = omFactory.createOMElement(chave, omNamespace);
      parametro.setText(valor);
      return parametro;
   }
}
