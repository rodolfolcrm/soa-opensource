
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
      OMElement cpfProprietario = obtemPrimeiroElementoComNome(elemento, "cpf_proprietario");
      OMElement codigoIbgeEstado = obtemPrimeiroElementoComNome(elemento, "codigo_ibge_estado");
      OMElement endereco = obtemPrimeiroElementoComNome(elemento, "endereco");
      OMElement descricao = obtemPrimeiroElementoComNome(elemento, "descricao");
      OMElement valorAluguel = obtemPrimeiroElementoComNome(elemento, "valor_aluguel");
      OMElement codigoIbgeMunicipio = obtemPrimeiroElementoComNome(elemento, "codigo_ibge_municipio");

      Imovel imovel = new Imovel();
      imovel.setIdentificador(Long.valueOf(identificador.getText()));
      imovel.setCpf_proprietario(Long.valueOf(cpfProprietario.getText()));
      imovel.setCodigo_ibge_estado(Integer.valueOf(codigoIbgeEstado.getText()));
      imovel.setEndereco(endereco.getText());
      imovel.setDescricao(descricao.getText());
      imovel.setValor_aluguel(BigDecimal.ZERO);// TODO
      imovel.setCodigo_ibge_municipio(Integer.valueOf(codigoIbgeMunicipio.getText()));
      return imovel;
   }
}
