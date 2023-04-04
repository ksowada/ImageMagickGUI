package de.ardoid.files;

import net.sf.saxon.s9api.*;
import net.sf.saxon.s9api.Serializer;
import org.htmlcleaner.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;

/**
 * utility for XML, XPATH and XSLT with external File and others
 */
public class ParseXPath {
    /**
     * the DOM of a XML-file
     * null: if not readed well (may be constructed with wrong filename)
     */
    public final Document doc;

    /**
     * read or create a File in XML-Format to a document a member of this class
     *
     * @param file                 a path to a XML-File, that will be fetched into DOM
     * @param createADomIfNotFound if false, and no file is found, a new blank Document will be build else if no file is found it return member doc null
     */
    public ParseXPath(String file, boolean createADomIfNotFound) {
        doc = createDomFromFile(file, createADomIfNotFound);
    }

    public ParseXPath() {
        doc = createDom();
    }

    /**
     * read or create a File in XML-Format to a document a member of this class
     *
     * @param file                 a path to a XML-File, that will be fetched into DOM
     * @param createADomIfNotFound if false, and no file is found, a new blank Document will be build else if no file is found it return member doc null
     * @return DOM Document of XML-File
     */
    public static Document createDomFromFile(String file, boolean createADomIfNotFound) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(file));
            return document;
        } catch (Exception e) {
            if (createADomIfNotFound) {
                return createDom();
            } else {
                return null;
            }
        }
    }

    public static Document createDom() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            return doc;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * parse the document with XPath
     *
     * @param xpathStr a String representing a XPath expression
     * @return may return null if nothing found
     */
    public NodeList getByXPath(String xpathStr) {
        return getByXPath(doc, xpathStr);
    }

    /**
     * evaluate XPath against Document itself
     */
    public static NodeList getByXPath(Document doc, String xpath) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodes;
        try {
            nodes = (NodeList) xPath.evaluate(xpath, doc, XPathConstants.NODESET);
            return nodes;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveAsXml(String urlStr) {
        try {
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(urlStr));
            transformer.transform(source, result);
        } catch (Exception e) {
        }
    }

    public static boolean transformXslt(String xsltUrlStr, String inpUrlStr, String outUrlStr) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File(xsltUrlStr));
            Transformer transformer = factory.newTransformer(xslt);

            Source text = new StreamSource(new File(inpUrlStr));
            transformer.transform(text, new StreamResult(new File(outUrlStr)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean transformXsltSaxon(String xsltUrlStr, String inpUrlStr, String outUrlStr, Pair[] parameters) {
        try {
            Processor processor = new Processor(false);
            XsltCompiler compiler = processor.newXsltCompiler();
            if (parameters != null && parameters.length > 0) {
                for (Pair parameter : parameters) {
                    compiler.setParameter(new QName(parameter.name), new XdmAtomicValue(parameter.val));
                }
            }
            XsltExecutable stylesheet = compiler.compile(new StreamSource(new File(xsltUrlStr)));
            Serializer out = processor.newSerializer(new File(outUrlStr));
//			out.setOutputProperty(Serializer.Property.METHOD, "html");
//			out.setOutputProperty(Serializer.Property.INDENT, "yes");
            Xslt30Transformer transformer = stylesheet.load30();
            transformer.transform(new StreamSource(new File(inpUrlStr)), out);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean transformXsltSaxonDom(String xsltUrlStr, String outUrlStr, Pair[] parameters) {
        try {
            Processor processor = new Processor(false);
            XsltCompiler compiler = processor.newXsltCompiler();
            if (parameters != null && parameters.length > 0) {
                for (Pair parameter : parameters) {
                    compiler.setParameter(new QName(parameter.name), new XdmAtomicValue(parameter.val));
                }
            }
            XsltExecutable stylesheet = compiler.compile(new StreamSource(new File(xsltUrlStr)));
            Serializer out = processor.newSerializer(new File(outUrlStr));
            Xslt30Transformer transformer = stylesheet.load30();
            transformer.transform(new DOMSource(doc), out);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean saveXmlNode(Node doc, String outUrlStr) {
        try {
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(outUrlStr));
            transformer.transform(source, result);
            return true;
        } catch (Exception e) {
            return false;
        }
//        // Output to console for testing
//        StreamResult consoleResult = new StreamResult(System.out);
//        transformer.transform(source, consoleResult);
    }


    public static Element appendChild(Document doc, Element el, String childName, int val) {
        return appendChild(doc, el, childName, Integer.toString(val));
    }

    public static Element appendChild(Document doc, Element el, String childName, String val) {
        Element aEl = doc.createElement(childName);
        aEl.setTextContent("val");
        el.appendChild(aEl);
        return aEl;
    }

    public static Element appendChild(Document doc, Element el, String childName, String attName, int attVal) {
        return appendChild(doc, el, childName, attName, Integer.toString(attVal));
    }

    public static Element appendChild(Document doc, Element el, String childName, String attName, String attVal) {
        Element aEl = doc.createElement(childName);
        aEl.setAttribute(attName, attVal);
        el.appendChild(aEl);
        return aEl;
    }

    public static Element getChild(Document doc, Element el, String elName, String attName, String value) {
        NodeList nl = el.getElementsByTagName(elName);
        int nlLen = nl.getLength();
        for (int i = 0; i < nlLen; i++) {
            Node node = nl.item(i);
            Element subEl = (Element) node;
            String attVal = subEl.getAttribute(attName);
            if (attVal.equals(value)) return subEl;
        }
        return null;
    }

}