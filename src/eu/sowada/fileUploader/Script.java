/**
 * 
 */
package eu.sowada.fileUploader;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * transform XML.Node to a Script-Object holding the command for a Script
 * @author Karl Sowada
 * @version 0.1
 * @since 180113
 */
public class Script {

	private static final String ATTR_NAME = "name";
	private static final String ATTR_OUT_FILE = "outFile";
	private static final String ELEM_COMMAND = "Command";
	private static final String ELEM_OPERATOR = "Operator";
	private static final String ELEM_SETTING = "Setting";
	private static final String ELEM_CHANNEL_OPERATOR = "ChannelOperator";
	private static final String ELEM_SEQUENCE_OPERATOR = "SequenceOperator";
	private static final String ELEM_GEOMETRY = "Geometry";
	
	public String name;
	public String command;
	public String operator;
	public String outFile;
	public String setting;
	private String channelOperator;
	private String sequenceOperator;
	private String geometry;
	
	public Script(Node node, XmlReader xmlReader) {
		
		// read Attributes of Node
		NamedNodeMap nodeAttributes = node.getAttributes();
		this.name = nodeAttributes.getNamedItem(ATTR_NAME).getNodeValue();
		
		Node attributeNode = nodeAttributes.getNamedItem(ATTR_OUT_FILE);
//		this.outFile = nodeAttributes.getNamedItem(ATTR_OUT_FILE).getNodeValue();
		this.outFile = (attributeNode == null) ? "" : attributeNode.getNodeValue();
		
		// read Elements of Node
		NodeList nodes = node.getChildNodes();
	    for (int i=0; i < nodes.getLength(); i++) {
	        Node subNode = nodes.item(i);
	        String nodeName = subNode.getNodeName();
			switch (nodeName) {
			case ELEM_COMMAND:
				this.command = xmlReader.getText(subNode);//subNode.getTextContent();
				break;

			case ELEM_SETTING:
				this.setting = xmlReader.getText(subNode);//subNode.getTextContent();
				break;
				
			case ELEM_OPERATOR:
				this.operator = xmlReader.getText(subNode);//subNode.getTextContent();
				break;

			case ELEM_CHANNEL_OPERATOR:
				this.channelOperator = xmlReader.getText(subNode);//subNode.getTextContent();
				break;

			case ELEM_SEQUENCE_OPERATOR:
				this.sequenceOperator = xmlReader.getText(subNode);//subNode.getTextContent();
				break;
			
			case ELEM_GEOMETRY:
				this.geometry = xmlReader.getText(subNode);//subNode.getTextContent();
				break;	
			default:
				break;
			}
	    }
	}
	
	public String toText() {
		return this.name;
	}
}
