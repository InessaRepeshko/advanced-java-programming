package part2.lab3.task6;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static part2.lab3.task4.FileUtils.*;
import static part2.lab3.task6.FileUtils.*;

public class FileUtilsDemo {
    private static final String INPUT_FILE_PATH = "src/main/resources/part2/lab3/task6/AcademicGroup.xml";
    private static final String OUTPUT_FILE_PATH = "src/main/resources/part2/lab3/task6/ModifiedAcademicGroup.xml";

    public static void testFileUtilsByDOM() {
        System.out.println("*********************************************************\n"
                + "Academic group XML file processor by DOM:\n"
                + "*********************************************************");

        try {
            printLines(readFromFile(INPUT_FILE_PATH));

            DOMFileUtils parser = new DOMFileUtils();
            Document document = parser.parseXMLFile(INPUT_FILE_PATH);

            System.out.println("\nParsed info from document:");
            parser.printAcademicGroupInfo(document);

            System.out.println("\nModified info from document:");
            parser.updateRatingScore(document);

            parser.writeModifiedToXML(document, OUTPUT_FILE_PATH);
            System.out.println("\nModified XML file saved.\n");

            printLines(readFromFile(OUTPUT_FILE_PATH));
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void testFileUtilsBySAX() {
        System.out.println("*********************************************************\n"
                + "Academic group XML file processor by SAX:\n"
                + "*********************************************************");

        try {
            printLines(readFromFile(INPUT_FILE_PATH));

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            if (saxParser != null) {
                InputSource input = new InputSource(INPUT_FILE_PATH);
                saxParser.parse(input, new SAXFileUtils());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        testFileUtilsByDOM();
        System.out.println("\n");
        testFileUtilsBySAX();
    }
}
