
package br.com.rodolfomartins.imovel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.rodolfomartins.conversor.Conversor;
import br.com.rodolfomartins.integrador.IntegradorWebService;
import br.com.rodolfomartins.integrador.IntegradorWebServiceFactory;
import br.com.rodolfomartins.util.OMElementHelper;

/**
 * Service para Imovel.
 * 
 * @author 15/09/2015: Rodolfo Martins <DD>
 */
public class ImovelService
{

   private static final String ENDPOINT = "http://localhost:9764/services/ImovelDataService?wsdl";
   private static final String NAMESPACE = "ImovelDataService";
   // private static final String NAMESPACE = "http://ws.wso2.org/dataservice";
   private static final String NOMEMETODO_FINDALL = "findAll";
   private static final String NOMEMETODO_FIND_BY_DESCRICAO = "findByDescricao";
   private static final String NOMEMETODO_SALVA = "addImovel";

   private Conversor<Imovel> conversor = new ConversorImovel();

   /**
    * Pesquisa todos os imoveis.
    * 
    * @return lista com todos os imoveis.
    */
   public List<Imovel> findAll()
   {
      IntegradorWebService integrador = IntegradorWebServiceFactory.cria(ENDPOINT, NAMESPACE, NOMEMETODO_FINDALL, conversor);
      return conversor.converteLista(integrador.envia());
   }

   /**
    * Pesquisa Imovel por nome.
    * 
    * @param nome
    * @return lista de imoveis por nome
    */
   public List<Imovel> findByDescricao(String descricao)
   {
      validaConsultaPorDescricao(descricao);

      Map<String, String> map = new HashMap<String, String>();
      map.put("descricao", "%" + descricao + "%");
      IntegradorWebService integrador = IntegradorWebServiceFactory.cria(ENDPOINT, NAMESPACE, NOMEMETODO_FIND_BY_DESCRICAO, conversor);
      return conversor.converteLista(integrador.envia(map));
   }


   private void validaConsultaPorDescricao(String descricao)
   {
      if (descricao == null || descricao.trim().isEmpty())
      {
         throw new RuntimeException("Campo Obrigatório não informado: descricao");
      }
   }

   /**
    * Salva um imovel.
    * 
    * @param imovel
    */
   public void inclui(Imovel imovel)
   {
      validaImovel(imovel);
      IntegradorWebService integrador = IntegradorWebServiceFactory.cria(ENDPOINT, NAMESPACE, NOMEMETODO_SALVA, null);
      integrador.envia(OMElementHelper.toOM(imovel));
   }

   /**
    * Valida um imovel para salvar.
    * 
    * @param imovel
    */
   private void validaImovel(Imovel imovel)
   {
      if (imovel.getCpf_proprietario() == null || imovel.getCpf_proprietario() == 0)
      {
         throw new RuntimeException("Campo Obrigatório não informado: cpfProprietario");
      }

      if (imovel.getCodigo_ibge_estado() == null || imovel.getCodigo_ibge_estado() == 0)
      {
         throw new RuntimeException("Campo Obrigatório não informado: codigoIbgeEstado");
      }

      if (imovel.getEndereco() == null || imovel.getEndereco().isEmpty())
      {
         throw new RuntimeException("Campo Obrigatório não informado: endereco");
      }

      if (imovel.getDescricao() == null || imovel.getDescricao().isEmpty())
      {
         throw new RuntimeException("Campo Obrigatório não informado: descricao");
      }

      if (imovel.getValor_aluguel() == null)
      {
         throw new RuntimeException("Campo Obrigatório não informado: valorAluguel");
      }

      if (imovel.getCodigo_ibge_municipio() == null || imovel.getCodigo_ibge_municipio() == 0)
      {
         throw new RuntimeException("Campo Obrigatório não informado: codigoIbgeMunicipio");
      }
   }

   public static void main(String[] args)
   {
      Imovel imovel = new Imovel();
      imovel.setIdentificador(4L);
      imovel.setCpf_proprietario(12345678909L);
      imovel.setCodigo_ibge_estado(12);
      imovel.setEndereco("Rua A");
      imovel.setDescricao("Apartamento muito bom");
      imovel.setValor_aluguel(new BigDecimal(1000));
      imovel.setCodigo_ibge_municipio(120001);
      new ImovelService().inclui(imovel);

      List<Imovel> imoveis = new ImovelService().findByDescricao("Casa");
      if (imoveis != null && !imoveis.isEmpty())
      {
         for (Imovel im : imoveis)
         {
            System.out.println(im.getDescricao());
         }
      }
      else
      {
         System.out.println("resultado vazio");
      }
   }
}
