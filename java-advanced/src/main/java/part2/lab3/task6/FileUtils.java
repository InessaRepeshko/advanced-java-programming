package part2.lab3.task6;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;


public class FileUtils {
    public static class DOMFileUtils {
        public Document parseXMLFile(String fileName) throws ParserConfigurationException, SAXException, IOException {
            File inputFile = new File(fileName);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();

            return docBuilder.parse(inputFile);
        }

        public void printAcademicGroupInfo(Document document) {
            document.getDocumentElement().normalize();
            System.out.println("Group number:\t"
                    + document.getElementsByTagName("groupNumber").item(0).getTextContent()
                    + "\nStudent list:");

            NodeList nList = document.getElementsByTagName("student");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                System.out.print("\tStudent:\t");

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    System.out.println(
                            "Full name: \t\"" +
                                    eElement.getElementsByTagName("fullName").item(0).getTextContent() +
                                    "\";\tRating score:\t" +
                                    eElement.getElementsByTagName("ratingScore").item(0).getTextContent() + "."
                    );
                }
            }
        }

        public void updateRatingScore(Document document) {
            NodeList nodeList = document.getElementsByTagName("ratingScore");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                node.setTextContent(String.valueOf(Double.parseDouble(node.getTextContent()) + 3.5));
            }
            printAcademicGroupInfo(document);
        }

        public void writeModifiedToXML(Document document, String fileName) throws TransformerException {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);
        }
    }

    public static class SAXFileUtils extends DefaultHandler {
        private boolean inGroupNumber = false;
        private boolean inStudentList = false;
        private boolean inStudent = false;
        private boolean inFullName = false;
        private boolean inRatingScore = false;

        @Override
        public void startDocument() {
            System.out.println("\nParsed info from document:");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("groupNumber")) {
                inGroupNumber = true;
                System.out.print("Group number:\t");
            } else if (qName.equalsIgnoreCase("studentList")) {
                inStudentList = true;
                System.out.print("\nStudent list:");
            } else if (qName.equalsIgnoreCase("student")) {
                inStudent = true;
                System.out.print("\n\tStudent:");
            } else if (qName.equalsIgnoreCase("fullName")) {
                inFullName = true;
                System.out.print("\tFull name:\t\"");
            } else if (qName.equalsIgnoreCase("ratingScore")) {
                inRatingScore = true;
                System.out.print("\";\tRating score:\t");
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String value = new String(ch)
                    .substring(start, start + length)
                    .trim();

            if (!value.isEmpty()
                    && (inGroupNumber
                    || inFullName
                    || inRatingScore)) {
                System.out.print(value);
            }
        }
    }
}
