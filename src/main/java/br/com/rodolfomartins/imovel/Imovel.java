
package br.com.rodolfomartins.imovel;

import java.math.BigDecimal;

/**
 * Descrição do Fonte
 * 
 * @author 15/09/2015: Rodolfo Martins <DD>
 */
public class Imovel
{

   private Long identificador;
   private Long cpfProprietario;
   private Integer codigoIbgeEstado;
   private String endereco;
   private String descricao;
   private BigDecimal valorAluguel;
   private Integer codigoIbgeMunicipio;

   public Long getIdentificador()
   {
      return identificador;
   }

   public void setIdentificador(Long identificador)
   {
      this.identificador = identificador;
   }

   public Long getCpfProprietario()
   {
      return cpfProprietario;
   }

   public void setCpfProprietario(Long cpfProprietario)
   {
      this.cpfProprietario = cpfProprietario;
   }

   public Integer getCodigoIbgeEstado()
   {
      return codigoIbgeEstado;
   }

   public void setCodigoIbgeEstado(Integer codigoIbgeEstado)
   {
      this.codigoIbgeEstado = codigoIbgeEstado;
   }

   public String getEndereco()
   {
      return endereco;
   }

   public void setEndereco(String endereco)
   {
      this.endereco = endereco;
   }

   public String getDescricao()
   {
      return descricao;
   }

   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }

   public BigDecimal getValorAluguel()
   {
      return valorAluguel;
   }

   public void setValorAluguel(BigDecimal valorAluguel)
   {
      this.valorAluguel = valorAluguel;
   }

   public Integer getCodigoIbgeMunicipio()
   {
      return codigoIbgeMunicipio;
   }

   public void setCodigoIbgeMunicipio(Integer codigoIbgeMunicipio)
   {
      this.codigoIbgeMunicipio = codigoIbgeMunicipio;
   }

}
