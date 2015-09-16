package br.com.rodolfomartins.conversor;

import java.util.List;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;


/**
 * Descrição do Fonte
 * 
 * @author 16/09/2015: Rodolfo Martins <DD>
 */
public interface Conversor<TIPO>
{

   public List<TIPO> converteLista(OMElement elemento);

   public TIPO converte(OMElement elemento);

   public void setOmNamespace(OMNamespace omNamespace);
}
