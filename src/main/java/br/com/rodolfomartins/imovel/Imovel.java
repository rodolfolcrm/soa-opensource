
package br.com.rodolfomartins.imovel;

import java.math.BigDecimal;

/**
 * Pojo para Imovel.
 * 
 * @author 15/09/2015: Rodolfo Martins <DD>
 */
public class Imovel
{

   private Long identificador;
   private Long cpf_proprietario;
   private Integer codigo_ibge_estado;
   private String endereco;
   private String descricao;
   private BigDecimal valor_aluguel;
   private Integer codigo_ibge_municipio;

   public Long getIdentificador()
   {
      return identificador;
   }

   public void setIdentificador(Long identificador)
   {
      this.identificador = identificador;
   }

   public Long getCpf_proprietario()
   {
      return cpf_proprietario;
   }

   public void setCpf_proprietario(Long cpf_proprietario)
   {
      this.cpf_proprietario = cpf_proprietario;
   }

   public Integer getCodigo_ibge_estado()
   {
      return codigo_ibge_estado;
   }

   public void setCodigo_ibge_estado(Integer codigo_ibge_estado)
   {
      this.codigo_ibge_estado = codigo_ibge_estado;
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

   public BigDecimal getValor_aluguel()
   {
      return valor_aluguel;
   }

   public void setValor_aluguel(BigDecimal valor_aluguel)
   {
      this.valor_aluguel = valor_aluguel;
   }

   public Integer getCodigo_ibge_municipio()
   {
      return codigo_ibge_municipio;
   }

   public void setCodigo_ibge_municipio(Integer codigo_ibge_municipio)
   {
      this.codigo_ibge_municipio = codigo_ibge_municipio;
   }

}
