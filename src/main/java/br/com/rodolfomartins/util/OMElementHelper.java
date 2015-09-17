
package br.com.rodolfomartins.util;

import javax.xml.stream.XMLStreamReader;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMXMLBuilderFactory;
import org.apache.axiom.om.OMXMLParserWrapper;
import org.apache.axis2.databinding.utils.BeanUtil;
import org.apache.axis2.util.StreamWrapper;

/**
 * Classe utilitária para {@link OMElement}
 * 
 * @author 16/09/2015: Rodolfo Martins <DD>
 */
public class OMElementHelper
{

   /**
    * Converte um objeto para um objeto {@link OMElement}
    * 
    * @param tipo
    * @return {@link OMElement}
    */
   public static OMElement toOM(Object tipo)
   {
      XMLStreamReader reader = BeanUtil.getPullParser(tipo);
      StreamWrapper parser = new StreamWrapper(reader);
      OMXMLParserWrapper stAXOMBuilder = OMXMLBuilderFactory.createStAXOMBuilder(OMAbstractFactory.getOMFactory(), parser);
      OMElement element = stAXOMBuilder.getDocumentElement();
      return element;
   }

}
