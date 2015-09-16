/*
 * Fóton Informática S.A.
 * Criação : 16/09/2015
 */

package br.com.rodolfomartins.integrador;

import org.apache.axiom.om.OMNamespace;
import br.com.rodolfomartins.conversor.Conversor;

/**
 * Descrição do Fonte
 * 
 * @author 16/09/2015: Rodolfo Martins <DD>
 */
public class IntegradorWebServiceFactory
{

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
