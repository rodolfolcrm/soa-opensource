
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

   private static final String ENDPOINT = "http://localhost:9763/services/ImovelDataService?wsdl";
   private static final String NAMESPACE = "ImovelDataService";
   private static final String NOMEMETODO_FINDALL = "findAll";
   private static final String NOMEMETODO_FIND_BY_NAME = "findByName";
   private static final String NOMEMETODO_SALVA = "salva";

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
   public List<Imovel> findByName(String nome)
   {
      if (nome == null || nome.trim().isEmpty())
      {
         throw new RuntimeException("Campo Obrigatório não informado: nome");
      }

      Map<String, String> map = new HashMap<String, String>();
      map.put("nome", nome);
      IntegradorWebService integrador = IntegradorWebServiceFactory.cria(ENDPOINT, NAMESPACE, NOMEMETODO_FIND_BY_NAME, conversor);
      return conversor.converteLista(integrador.envia(map));
   }

   /**
    * Salva um imovel.
    * 
    * @param imovel
    */
   public void salva(Imovel imovel)
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
      if (imovel.getCpfProprietario() == null || imovel.getCpfProprietario() == 0)
      {
         throw new RuntimeException("Campo Obrigatório não informado: cpfProprietario");
      }

      if (imovel.getCodigoIbgeEstado() == null || imovel.getCodigoIbgeEstado() == 0)
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

      if (imovel.getValorAluguel() == null)
      {
         throw new RuntimeException("Campo Obrigatório não informado: valorAluguel");
      }

      if (imovel.getCodigoIbgeMunicipio() == null || imovel.getCodigoIbgeMunicipio() == 0)
      {
         throw new RuntimeException("Campo Obrigatório não informado: codigoIbgeMunicipio");
      }
   }

   public static void main(String[] args)
   {
      Imovel imovel = new Imovel();
      imovel.setCpfProprietario(12345678909L);
      imovel.setCodigoIbgeEstado(12);
      imovel.setEndereco("Rua A");
      imovel.setDescricao("Apartamento muito bom");
      imovel.setValorAluguel(new BigDecimal(1000));
      imovel.setCodigoIbgeMunicipio(120001);
      new ImovelService().salva(imovel);
   }
}
