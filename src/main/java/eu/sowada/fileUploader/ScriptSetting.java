package eu.sowada.fileUploader;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * handles one ScriptSetting of a Script, to get additional optional info for
 * one line of ScriptParameter
 * 
 * @author Karl Sowada
 * @version 0.1
 * @since 180116
 */
public class ScriptSetting {

	private static final String ELEM_EXAMPLE = "Example";
	public String parameterText;
	public String example;

	public ScriptSetting(Node node, XmlReader xmlReader) {
		
		parameterText = xmlReader.getText(node);

		// read SubElements of Node
		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node subNode = nodes.item(i);
			String nodeName = subNode.getNodeName();
			switch (nodeName) {
			case ELEM_EXAMPLE:
				example = xmlReader.getText(subNode);
				
			}
		}
	}

	public String toString() {
		return parameterText;
	}
}
