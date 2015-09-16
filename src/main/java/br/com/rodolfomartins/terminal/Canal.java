/*
 * Fóton Informática S.A.
 * Criação : 16/09/2015
 */

package br.com.rodolfomartins.terminal;

/**
 * Descrição do Fonte
 * 
 * @author 16/09/2015: Rodolfo Martins <DD>
 */
public class Canal
{

   private Integer codigoCanal;
   private String descricaoCanal;
   private Integer timCanal;

   public Integer getCodigoCanal()
   {
      return codigoCanal;
   }

   public void setCodigoCanal(Integer codigoCanal)
   {
      this.codigoCanal = codigoCanal;
   }

   public String getDescricaoCanal()
   {
      return descricaoCanal;
   }

   public void setDescricaoCanal(String descricaoCanal)
   {
      this.descricaoCanal = descricaoCanal;
   }

   public Integer getTimCanal()
   {
      return timCanal;
   }

   public void setTimCanal(Integer timCanal)
   {
      this.timCanal = timCanal;
   }

}
