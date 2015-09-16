
package br.com.rodolfomartins.terminal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import br.com.rodolfomartins.conversor.Conversor;
import br.com.rodolfomartins.integrador.IntegradorWebService;
import br.com.rodolfomartins.integrador.IntegradorWebServiceFactory;
import br.com.rodolfomartins.util.OMElementHelper;

/**
 * Descrição do Fonte
 * 
 * @author 15/09/2015: Rodolfo Martins <DD>
 */
public class TerminalService
{

   private static final Logger LOGGER = Logger.getLogger(TerminalService.class);

   private static final String ENDPOINT = "http://localhost:9763/services/ImovelDataService?wsdl";
   private static final String NAMESPACE = "ImovelDataService";
   private static final String NOMEMETODO_FINDALL_TERMINAL = "findAllTerminal";
   private static final String NOMEMETODO_FIND_BY_NAME = "findByName";
   private static final String NOMEMETODO_SALVA = "salva";

   private Conversor<Terminal> conversor = new ConversorTerminal();

   public List<Terminal> findAll()
   {
      IntegradorWebService integrador = IntegradorWebServiceFactory.cria(ENDPOINT, NAMESPACE, NOMEMETODO_FINDALL_TERMINAL, conversor);
      return conversor.converteLista(integrador.envia());
   }

   public List<Terminal> findByName(String nome)
   {
      Map<String, String> map = new HashMap<String, String>();
      map.put("nome", nome);
      IntegradorWebService integrador = IntegradorWebServiceFactory.cria(ENDPOINT, NAMESPACE, NOMEMETODO_FIND_BY_NAME, conversor);
      return conversor.converteLista(integrador.envia(map));
   }

   public void salva(Terminal terminal)
   {
      IntegradorWebService integrador = IntegradorWebServiceFactory.cria(ENDPOINT, NAMESPACE, NOMEMETODO_SALVA, null);
      integrador.envia(OMElementHelper.toOM(terminal));
   }

   public void salvaCanal(Canal canal)
   {
      IntegradorWebService integrador = IntegradorWebServiceFactory.cria(ENDPOINT, NAMESPACE, "addCanal", null);
      integrador.envia(OMElementHelper.toOM(canal));
   }

   public static void main(String[] args)
   {
      Terminal ter = new Terminal();
      // ter.setPtacod(123);
      // ter.setTercod("123465");
      // ter.setTeride("ide ter");
      // ter.setTernom("nom ter");
      // new TerminalService().salva(ter);
      //
      List<Terminal> list = new TerminalService().findAll();

      for (Terminal terminal : list)
      {
         LOGGER.info(terminal.getTercod() + "," + terminal.getPtacod() + ", " + terminal.getTernom());
      }

      Canal canal = new Canal();
      canal.setCodigoCanal(6);
      canal.setDescricaoCanal("teste");
      canal.setTimCanal(123456);
      new TerminalService().salvaCanal(canal);
   }

}
