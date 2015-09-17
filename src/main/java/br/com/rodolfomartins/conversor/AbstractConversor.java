
package br.com.rodolfomartins.conversor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;

/**
 * Classe abstrata para conversores entre OMElement e Pojos
 * 
 * @author 16/09/2015: Rodolfo Martins <DD>
 */
public abstract class AbstractConversor<TIPO> implements Conversor<TIPO>
{

   private OMNamespace omNamespace;

   /**
    * (Ver Javadoc da super classe)
    * 
    * @see br.com.rodolfomartins.conversor.Conversor#converteLista(org.apache.axiom.om.OMElement)
    */
   public List<TIPO> converteLista(OMElement omElement)
   {
      List<TIPO> lista = new ArrayList<TIPO>();

      @SuppressWarnings("rawtypes")
      Iterator rows = omElement.getChildElements();

      while (rows.hasNext())
      {
         OMElement row = (OMElement) rows.next();

         lista.add(converte(row));
      }

      return lista;
   }

   /**
    * Retorna o primeiro OMElement pelo nome
    * 
    * @param elemento
    * @param nome
    * @return OMElement
    */
   protected OMElement obtemPrimeiroElementoComNome(OMElement elemento, String nome)
   {
      return elemento.getFirstChildWithName(new QName(getOmNamespace().getNamespaceURI(), nome));
   }

   /**
    * Retorna o OMNamespace
    * 
    * @return OMNamespace
    */
   public OMNamespace getOmNamespace()
   {
      return omNamespace;
   }

   /**
    * (Ver Javadoc da super classe)
    * 
    * @see br.com.rodolfomartins.conversor.Conversor#setOmNamespace(org.apache.axiom.om.OMNamespace)
    */
   public void setOmNamespace(OMNamespace omNamespace)
   {
      this.omNamespace = omNamespace;
   }
}
