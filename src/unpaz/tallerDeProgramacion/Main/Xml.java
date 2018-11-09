package unpaz.tallerDeProgramacion.Main;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import unpaz.tallerDeProgramacion.JLogoData.Comando;

public class Xml
{
	
	static void agregarComando(Comando command,Document doc,Element listDeComandos)
	{
		Element comando = doc.createElement(command.getNombre());
		if(command.getParametros()!=null)
		{
			Element parametros_comando = doc.createElement("parametros");
			for(int i=0;i<command.getParametros().length;i++)
			{
				Attr attribute = doc.createAttribute("param"+i);
				attribute.setValue(command.getParametros()[i]);
				parametros_comando.setAttributeNode(attribute);
			}
	    	comando.appendChild(parametros_comando);
		}
    	listDeComandos.appendChild(comando);
	}
	static void guardar(String fileName,Document doc)
	{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
		try
		{
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e)
		{
			e.printStackTrace();
		}
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName));

        try
        {
			transformer.transform(source, result);
		} catch (TransformerException e)
        {
			e.printStackTrace();
		}
	}
	
	static Document cargar(String fileName)
	{
		Document doc = null;
		
        try 
        {
        	File fXmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            try
            {
            	 doc = dBuilder.parse(fXmlFile);
                 return doc;
            }catch(FileNotFoundException e)
            {
            	System.out.println("No se pudo encontrar el archivo "+fileName);
            	return null;
            }
           
        } catch (Exception e) 
        {
        	System.out.println("Error "+e);
            return null;
        }     
	}

}

