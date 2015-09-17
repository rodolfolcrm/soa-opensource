
package br.com.rodolfomartins.imovel;

import java.math.BigDecimal;
import org.apache.axiom.om.OMElement;
import br.com.rodolfomartins.conversor.AbstractConversor;

/**
 * Conversor para Imovel
 * 
 * @author 16/09/2015: Rodolfo Martins <DD>
 */
public class ConversorImovel extends AbstractConversor<Imovel>
{

   /**
    * (Ver Javadoc da super classe)
    * 
    * @see br.com.rodolfomartins.conversor.Conversor#converte(org.apache.axiom.om.OMElement)
    */
   public Imovel converte(OMElement elemento)
   {
      OMElement identificador = obtemPrimeiroElementoComNome(elemento, "identificador");
      OMElement cpfProprietario = obtemPrimeiroElementoComNome(elemento, "cpfProprietario");
      OMElement codigoIbgeEstado = obtemPrimeiroElementoComNome(elemento, "codigoIbgeEstado");
      OMElement endereco = obtemPrimeiroElementoComNome(elemento, "endereco");
      OMElement descricao = obtemPrimeiroElementoComNome(elemento, "descricao");
      OMElement valorAluguel = obtemPrimeiroElementoComNome(elemento, "valorAluguel");
      OMElement codigoIbgeMunicipio = obtemPrimeiroElementoComNome(elemento, "codigoIbgeMunicipio");

      Imovel imovel = new Imovel();
      imovel.setIdentificador(Long.valueOf(identificador.getText()));
      imovel.setCpfProprietario(Long.valueOf(cpfProprietario.getText()));
      imovel.setCodigoIbgeEstado(Integer.valueOf(codigoIbgeEstado.getText()));
      imovel.setEndereco(endereco.getText());
      imovel.setDescricao(descricao.getText());
      imovel.setValorAluguel(BigDecimal.ZERO);// TODO
      imovel.setCodigoIbgeMunicipio(Integer.valueOf(codigoIbgeMunicipio.getText()));
      return imovel;
   }
}
