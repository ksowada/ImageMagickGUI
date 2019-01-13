package eu.sowada.fileUploader;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;


public class XmlReader {

	/** access this variable to get the DOM of the given XML-File */
	public Document document;

	/** tell me the xmlFile and I build up the document Field */
	public void initFile(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		document = builder.parse(xmlFile);
		
		Element root = document.getDocumentElement();
		
		System.out.println("runned. read "+root.getTagName());
		
	}
	
	/** test XmlReader */
	public static void main(String[] args) {
		XmlReader xmlReader = new XmlReader();
		try {
			xmlReader.initFile(new File("script/script.xml"));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
