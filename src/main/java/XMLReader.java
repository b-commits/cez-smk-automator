import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class XMLReader {

    private static final String XML_FILE_PATH = "C:\\Users\\busio\\Desktop\\untitled2\\src\\main\\java\\raport.xml";
    private static final String MAIN_DATA_NODE_NAME = "Row";
    private static final HashMap<Integer, ArrayList<String>> procedures = new HashMap<>();


    public static void main(String[] args) {
        tokenizeDataDump();
        getTokens();
    }

    public static Document getDocument(String docString)  {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            factory.setIgnoringComments(true);
            return builder.parse(new InputSource(docString));
        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void tokenizeDataDump() {
        Document xmlDoc = getDocument(XML_FILE_PATH);
        NodeList rows = Objects.requireNonNull(xmlDoc).getElementsByTagName(MAIN_DATA_NODE_NAME);
        for (int i = 0; i < rows.getLength(); i++) {
            ArrayList<String> tokens = new ArrayList<>();
            for (int j = 0; j < rows.item(i).getChildNodes().getLength(); j++) {
                if (rows.item(i).getChildNodes().item(j).hasChildNodes()) {
                    if (rows.item(i).getChildNodes().item(j).getFirstChild().getFirstChild() != null) {
                        tokens.add(rows.item(i).getChildNodes().item(j).getFirstChild().getFirstChild().getNodeValue());
                    }
                    procedures.put(i, tokens);
                }
            }
        }
    }

    public static void serialiseDataDump() {

    }

    public static void getTokens() {
        procedures.values().forEach(value -> {
            value.forEach(System.out::println);
            System.out.println("\n");
        });
    }

}
