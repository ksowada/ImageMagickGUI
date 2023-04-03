package de.ardoid;

import de.ardoid.files.ParseXPath;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import spark.Spark;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static de.ardoid.vars.StringHelper.*;
import static spark.Spark.*;

public class Main {

    final public static String CFG_FILE = "res/config-gui.xml";
    public static String pathWork;
    public static String pathDITA;
    public static ParseXPath configXML;

    public static void main(String[] args) throws Exception {

        System.out.println("webtool-gui started...");
        System.out.println("----------------------");

        // prepare environment
        // get working directory
        pathWork = System.getProperty("user.dir");
        pathWork = assureTrailingSlash(pathWork);

        // get environment var for magick command
        pathDITA = System.getenv("DITA_HOME");

        // read config.xml to DOM document
        Path configXMLPath = FileSystems.getDefault().getPath(Main.pathWork,"../","config.xml").normalize();
        configXML = new ParseXPath(configXMLPath.toString(), false);
        if (configXML.doc==null) throw new Exception("ERROR: config dont found");

        // read list of prj in config file
        ParseXPath cfgDoc = new ParseXPath(CFG_FILE, false);
        NodeList siteNamesNl = cfgDoc.getByXPath("//site/@name");
        for (int siteNamesIx = 0; siteNamesIx < siteNamesNl.getLength(); siteNamesIx++) {
            Node siteNameNode = siteNamesNl.item(siteNamesIx);
            System.out.println(siteNameNode.getTextContent());
        }

        // set external dir from which the web is served from
        String projectDir = System.getProperty("user.dir");
        String staticDir = "/htdocs/";
        staticFiles.externalLocation(projectDir + staticDir); // choose external for fast debug, overwrite without rebuild
        Spark.init();

        // react to Webapp ctrl
        put("ctrl/run", (request, response) -> {
            String[] prjs = request.queryParamsValues("prj");
            if (prjs.length>0) {
                System.out.println("PUT ctrl/run?prj="+prjs[0]);
                DitaTransform ditaTransform = new DitaTransform(prjs[0]);
                ditaTransform.run();
            }
            return "success";
        });
    }
}