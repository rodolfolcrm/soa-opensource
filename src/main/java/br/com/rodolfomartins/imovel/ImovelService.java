
package br.com.rodolfomartins.imovel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.rodolfomartins.conversor.Conversor;
import br.com.rodolfomartins.integrador.IntegradorWebService;
import br.com.rodolfomartins.integrador.IntegradorWebServiceFactory;
import br.com.rodolfomartins.util.OMElementHelper;

/**
 * Descrição do Fonte
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

   public List<Imovel> findAll()
   {
      IntegradorWebService integrador = IntegradorWebServiceFactory.cria(ENDPOINT, NAMESPACE, NOMEMETODO_FINDALL, conversor);
      return conversor.converteLista(integrador.envia());
   }

   public List<Imovel> findByName(String nome)
   {
      Map<String, String> map = new HashMap<String, String>();
      map.put("nome", nome);
      IntegradorWebService integrador = IntegradorWebServiceFactory.cria(ENDPOINT, NAMESPACE, NOMEMETODO_FIND_BY_NAME, conversor);
      return conversor.converteLista(integrador.envia(map));
   }

   public void salva(Imovel imovel)
   {
      IntegradorWebService integrador = IntegradorWebServiceFactory.cria(ENDPOINT, NAMESPACE, NOMEMETODO_SALVA, null);
      integrador.envia(OMElementHelper.toOM(imovel));
   }
}
