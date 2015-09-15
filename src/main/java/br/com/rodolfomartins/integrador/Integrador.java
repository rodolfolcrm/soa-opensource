
package br.com.rodolfomartins.integrador;

import org.apache.axiom.om.OMElement;

/**
 * Descrição do Fonte
 * 
 * @author 15/09/2015: Rodolfo Martins <DD>
 */
public interface Integrador
{

   public OMElement envia() throws IntegradorException;
}
