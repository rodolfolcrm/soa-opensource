
package br.com.rodolfomartins.conversor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;

/**
 * Descrição do Fonte
 * 
 * @author 16/09/2015: Rodolfo Martins <DD>
 */
public abstract class AbstractConversor<TIPO> implements Conversor<TIPO>
{

   private OMNamespace omNamespace;

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

   protected OMElement obtemPrimeiroElementoComNome(OMElement elemento, String nome)
   {
      return elemento.getFirstChildWithName(new QName(getOmNamespace().getNamespaceURI(), nome));
   }

   public OMNamespace getOmNamespace()
   {
      return omNamespace;
   }

   public void setOmNamespace(OMNamespace omNamespace)
   {
      this.omNamespace = omNamespace;
   }
}
