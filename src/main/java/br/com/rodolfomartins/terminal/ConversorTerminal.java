/*
* Fóton Informática S.A.
* Criação : 15/09/2015
*/
package br.com.rodolfomartins.terminal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.axiom.om.OMElement;


/**
 * Descrição do Fonte
 * 
 * @author 15/09/2015: Rodolfo Martins <DD>
 */
public class ConversorTerminal
{

   public List<Terminal> converte(String namespaceURI, OMElement omElement)
   {
      List<Terminal> lst = new ArrayList<Terminal>();

      // Recupeara o retorno
      Iterator rows = omElement.getChildElements();

      // Enquanto houver linhas no retorno
      while (rows.hasNext())
      {
         OMElement row = (OMElement) rows.next();

         // recupera os elementos
         OMElement ptaCod = row.getFirstChildWithName(new QName(namespaceURI, "ptacod"));
         OMElement terCod = row.getFirstChildWithName(new QName(namespaceURI, "tercod"));
         OMElement terIde = row.getFirstChildWithName(new QName(namespaceURI, "teride"));
         OMElement terNom = row.getFirstChildWithName(new QName(namespaceURI, "ternom"));

         // cria um objeto do tipo Funcionario
         Terminal ter = new Terminal();
         ter.setPtacod(Integer.parseInt(ptaCod.getText()));
         ter.setTercod(terCod.getText());
         ter.setTeride(terIde.getText());
         ter.setTernom(terNom.getText());

         // Adiciona na lista
         lst.add(ter);
      }

      return lst;
   }
}
