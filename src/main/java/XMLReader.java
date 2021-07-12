import model.Procedure;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class XMLReader {

    private static final SimpleDateFormat xmlDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static final SimpleDateFormat formFillFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final Integer COMPLETE_ROW_LENGTH = 12;
    private static final Integer INCOMPLETE_ROW_LENGTH = 8;
    private static final Integer MIN_VALID_ROW_LENGTH = 7;
    private static final String XML_FILE_PATH = "C:\\Users\\busio\\Desktop\\untitled2\\src\\main\\java\\raport.xml";
    private static final String MAIN_DATA_NODE_NAME = "Row";
    private static final HashMap<Integer, ArrayList<String>> procedureTokens = new HashMap<>();
    private static final ArrayList<Procedure> procedures = new ArrayList<>();


    public static void main(String[] args) {
        tokenizeDataDump();
        serializeValidData();
        getAllProcedureInfo();
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
                    procedureTokens.put(i, tokens);
                }
            }
        }
    }

    public static void serializeValidData()  {
        // todo Gotta refactor this.
        procedureTokens.values().forEach(value -> {
            if (value.size() == COMPLETE_ROW_LENGTH) {
                String patientName = value.get(1);
                String procedureName = value.get(4);
                LocalDate date = null;
                try {
                    date = LocalDate.parse(formFillFormat.format(xmlDateFormat.parse(value.get(6))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String[] names = patientName.split(" ");
                procedures.add(new Procedure(names[1], names[0], procedureName, date));
            }
            if (value.size() == INCOMPLETE_ROW_LENGTH) {
                String patientName = value.get(1);
                String procedureName = value.get(4);
                String[] names = patientName.split(" ");
                procedures.add(new Procedure(names[1], names[0], procedureName));
            }
        });
    }

    public static void getAllProcedureInfo() {
        procedures.forEach(System.out::println);
    }

    public static int getValidRecordCount() {
       return (int) procedureTokens.values().stream().filter(value -> value.size() > MIN_VALID_ROW_LENGTH).count();
    }

    public static void getTokens() {
        procedureTokens.values().forEach(value -> {
            value.forEach(System.out::println);
            System.out.println("===============");
        });
    }

}
