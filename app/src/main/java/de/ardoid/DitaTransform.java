package de.ardoid;

import de.ardoid.webappsrv.App;
import de.ardoid.files.Callee;
import org.w3c.dom.NodeList;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static de.ardoid.webappsrv.App.configXML;

public class DitaTransform {
    private final String prj;

    public DitaTransform(String prj) {
        this.prj = prj;
    }

    public void run() {
        // build path to dita ant file
        NodeList pathRelContentNl = configXML.getByXPath("//site[@name='"+this.prj+"']/content");
        String pathRelContent = pathRelContentNl.item(0).getTextContent();
        Path pathPrj = FileSystems.getDefault().getPath(App.pathWork,"../","../webtool.de",pathRelContent, "index.xml").normalize();

        // call process
        Process ditaProcess = Callee.call(App.pathDITA, "bin/ant", "-f", pathPrj.toString());
        Callee.printProcessOutput(ditaProcess);
    }
}
