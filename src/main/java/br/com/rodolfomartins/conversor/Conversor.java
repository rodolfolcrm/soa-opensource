
package br.com.rodolfomartins.conversor;

import java.util.List;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;

/**
 * Interface para conversores entre OMElement e Pojos
 * 
 * @author 16/09/2015: Rodolfo Martins <DD>
 */
public interface Conversor<TIPO>
{

   /**
    * Converte OMElement para uma lista de Pojos.
    * 
    * @param elemento elemento axis2
    * @return lista de pojos
    */
   public List<TIPO> converteLista(OMElement elemento);

   /**
    * Converte OMElement para um Pojo.
    * 
    * @param elemento elemento axis2
    * @return Pojo
    */
   public TIPO converte(OMElement elemento);

   /**
    * Seta o omNamespace, utilizado para recuperar informações do OMElement.
    * 
    * @param omNamespace
    */
   public void setOmNamespace(OMNamespace omNamespace);
}
