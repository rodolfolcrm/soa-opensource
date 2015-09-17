
package br.com.rodolfomartins.integrador;

import org.apache.axiom.om.OMNamespace;
import br.com.rodolfomartins.conversor.Conversor;

/**
 * Fábrica para {@link IntegradorWebService}
 * 
 * @author 16/09/2015: Rodolfo Martins <DD>
 */
public class IntegradorWebServiceFactory
{

   /**
    * Cria um {@link IntegradorWebService}
    * 
    * @param endpoint
    * @param nameSpace
    * @param nomeMetodo
    * @param conversor
    * @return
    * @throws IntegradorException
    */
   public static IntegradorWebService cria(String endpoint, String nameSpace, String nomeMetodo, Conversor<?> conversor)
      throws IntegradorException
   {
      IntegradorWebService integrador = new IntegradorWebService();
      OMNamespace omNamespace = integrador.inicializa(endpoint, nameSpace, nomeMetodo);
      if (conversor != null)
      {
         conversor.setOmNamespace(omNamespace);
      }
      return integrador;
   }
}
