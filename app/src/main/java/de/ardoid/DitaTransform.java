package de.ardoid;

import de.ardoid.files.Callee;
import org.w3c.dom.NodeList;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static de.ardoid.Main.configXML;

public class DitaTransform {
    private final String prj;

    public DitaTransform(String prj) {
        this.prj = prj;
    }

    public void run() {
        // build path to dita ant file
        NodeList pathRelContentNl = configXML.getByXPath("//site[@name='"+this.prj+"']/content");
        String pathRelContent = pathRelContentNl.item(0).getTextContent();
        Path pathPrj = FileSystems.getDefault().getPath(Main.pathWork,"../","../webtool.de",pathRelContent, "index.xml").normalize();

        // call process
        Process ditaProcess = Callee.call(Main.pathDITA, "bin/ant", "-f", pathPrj.toString());
        Callee.printProcessOutput(ditaProcess);
    }
}
