/*
 * Fóton Informática S.A.
 * Criação : 15/09/2015
 */

package br.com.rodolfomartins.integrador;

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
public class IntegradorWebService implements Integrador
{

   private static final Logger LOGGER = Logger.getLogger(IntegradorWebService.class);
   private EndpointReference endpoint;
   private OMNamespace omNs;
   private String nameSpace;
   private String nomeMetodo;

   public IntegradorWebService(String endpoint, String nameSpace, String nomeMetodo)
   {
      super();
      this.endpoint = new EndpointReference(endpoint);
      this.nameSpace = nameSpace;
      this.nomeMetodo = nomeMetodo;
   }

   public OMElement envia() throws IntegradorException
   {
      try
      {
         // cria os parametros do servico com o EndPoint e o método que queremos usar
         Options options = new Options();
         options.setTo(endpoint);
         options.setAction("urn:" + nomeMetodo);

         // cria um client para o servico
         ServiceClient sender = new ServiceClient();
         sender.setOptions(options);

         // para criar os parametros de entrada do servico
         OMFactory fac = OMAbstractFactory.getOMFactory();
         omNs = fac.createOMNamespace(nameSpace, nomeMetodo);
         OMElement payload = fac.createOMElement(nomeMetodo, omNs);

         // Executa o serviço
         OMElement result = sender.sendReceive(payload);

         return result;
      }
      catch (Throwable t)
      {
         LOGGER.error(t.getMessage(), t);
         throw new IntegradorException(t);
      }
   }

   public String getNamespaceURI()
   {
      return omNs.getNamespaceURI();
   }
}
