package eu.sowada.fileUploader;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

/**
 * grant access to XML-File
 * @author Karl Sowada
 * @version 0.1
 * @since 180113
 */
public class XmlReader {

	/** access this variable to get the DOM of the given XML-File */
	public Document document;
	public Element root;

	/** tell me the xmlFile and I build up the document Field */
	public void initFile(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		document = builder.parse(xmlFile);
		
		root = document.getDocumentElement();
		
		System.out.println("runned. read "+root.getTagName());
		
	}
	
	/**
	 * Find the named subnode in a node's sublist.
	 * <ul>
	 * <li>Ignores comments and processing instructions.
	 * <li>Ignores TEXT nodes (likely to exist and contain
	 *         ignorable whitespace, if not validating.
	 * <li>Ignores CDATA nodes and EntityRef nodes.
	 * <li>Examines element nodes to find one with
	 *        the specified name.
	 * </ul>
	 * @param name  the tag name for the element to find
	 * @param node  the element node to start searching from
	 * @return the Node found
	 */
	public Node findSubNode(String name, Node node) {
	    if (node.getNodeType() != Node.ELEMENT_NODE) {
	        System.err.println("Error: Search node not of element type");
	        System.exit(22);
	    }

	    if (! node.hasChildNodes()) return null;

	    NodeList list = node.getChildNodes();
	    for (int i=0; i < list.getLength(); i++) {
	        Node subnode = list.item(i);
	        if (subnode.getNodeType() == Node.ELEMENT_NODE) {
	           if (subnode.getNodeName().equals(name)) 
	               return subnode;
	        }
	    }
	    return null;
	}

	/**
	 * Find the named subnode in a node's sublist.
	 * <ul>
	 * <li>Ignores comments and processing instructions.
	 * <li>Ignores TEXT nodes (likely to exist and contain
	 *         ignorable whitespace, if not validating.
	 * <li>Ignores CDATA nodes and EntityRef nodes.
	 * <li>Examines element nodes to find one with
	 *        the specified name.
	 * </ul>
	 * @param name  the tag name for the element to find
	 * @param node  the element node to start searching from
	 * @return the Node found
	 */
	public ArrayList<Node> findSubNodes(String name) {
		return findSubNodes(name, root);
	}
	public ArrayList<Node> findSubNodes(String name, Node node) {
		
	    if (node.getNodeType() != Node.ELEMENT_NODE) {
	        System.err.println("Error: Search node not of element type");
	        System.exit(22);
	    }

	    if (! node.hasChildNodes()) return null;

	    ArrayList<Node> subNodes = new ArrayList<Node>();
	    NodeList list = node.getChildNodes();
	    for (int i=0; i < list.getLength(); i++) {
	        Node subnode = list.item(i);
	        if (subnode.getNodeType() == Node.ELEMENT_NODE) {
	           if (subnode.getNodeName().equals(name)) 
	        	   subNodes.add(subnode);
	        }
	    }
        return subNodes;
	}
	/**
	  * Return the text that a node contains. This routine:
	  * <ul>
	  * <li>Ignores comments and processing instructions.
	  * <li>Concatenates TEXT nodes, CDATA nodes, and the results of
	  *     recursively processing EntityRef nodes.
	  * <li>Ignores any element nodes in the sublist.
	  *     (Other possible options are to recurse into element 
	  *      sublists or throw an exception.)
	  * </ul>
	  * @param    node  a  DOM node
	  * @return   a String representing its contents
	  */
	public String getText(Node node) {
	    StringBuffer result = new StringBuffer();
	    if (! node.hasChildNodes()) return "";

	    NodeList list = node.getChildNodes();
	    for (int i=0; i < list.getLength(); i++) {
	        Node subnode = list.item(i);
	        if (subnode.getNodeType() == Node.TEXT_NODE) {
	            result.append(subnode.getNodeValue());
	        }
	        else if (subnode.getNodeType() == Node.CDATA_SECTION_NODE) {
	            result.append(subnode.getNodeValue());
	        }
	        else if (subnode.getNodeType() == Node.ENTITY_REFERENCE_NODE) {
	            // Recurse into the subtree for text
	            // (and ignore comments)
	            result.append(getText(subnode));
	        }
	    }

	    return result.toString();
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
