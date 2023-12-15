package nextstep.courses.utils;

import nextstep.courses.domain.SessionCover;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class VectorProcessor implements ImageProcessor {

    @Override
    public SessionCover processImage(File file) throws IOException {
        Element svgRoot = parseXML(file);
        return new SessionCover(1L, parseInt(svgRoot.getAttribute("width")), parseInt(svgRoot.getAttribute("height")), file.length(), writeVectorImage(file));
    }

    private int parseInt(String pixel) {
        if (pixel == null) {
            return 0;
        }
        return Integer.parseInt(pixel.replaceAll("px", ""));
    }

    private Element parseXML(File file) throws IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }

        doc.getDocumentElement().normalize();
        return doc.getDocumentElement();
    }

    private byte[] writeVectorImage(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }


}
