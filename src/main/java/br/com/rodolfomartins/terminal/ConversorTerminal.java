
package br.com.rodolfomartins.terminal;

import org.apache.axiom.om.OMElement;
import br.com.rodolfomartins.conversor.AbstractConversor;

/**
 * Descrição do Fonte
 * 
 * @author 15/09/2015: Rodolfo Martins <DD>
 */
public class ConversorTerminal extends AbstractConversor<Terminal>
{

   public Terminal converte(OMElement elemento)
   {
      OMElement ptaCod = obtemPrimeiroElementoComNome(elemento, "ptacod");
      OMElement terCod = obtemPrimeiroElementoComNome(elemento, "tercod");
      OMElement terIde = obtemPrimeiroElementoComNome(elemento, "teride");
      OMElement terNom = obtemPrimeiroElementoComNome(elemento, "ternom");

      Terminal ter = new Terminal();
      ter.setPtacod(Integer.parseInt(ptaCod.getText()));
      ter.setTercod(terCod.getText());
      ter.setTeride(terIde.getText());
      ter.setTernom(terNom.getText());
      return ter;
   }
}
