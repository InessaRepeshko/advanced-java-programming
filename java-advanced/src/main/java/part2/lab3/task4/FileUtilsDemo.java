package part2.lab3.task4;

import java.io.IOException;

import static part2.lab3.task4.FileUtils.*;

public class FileUtilsDemo {
    private static final String XML_INPUT_FILE_PATH = "src/main/resources/part2/lab3/task4/AcademicGroup.xml";
    private static final String JSON_INPUT_FILE_PATH = "src/main/resources/part2/lab3/task4/AcademicGroup.json";
    private static final String OUTPUT_FILE_PATH = "src/main/resources/part2/lab3/task4/AcademicGroup.txt";

    public static void printOutputFiles(String inputFilePath, String outputFilePath) {
        try {
            System.out.println();
            printLines(readFromFile(inputFilePath));
            System.out.println();
            printLines(readFromFile(outputFilePath));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void testXMLSerialization() {
        System.out.println("*********************************************************\n"
                + "XML serialization and deserialization:\n"
                + "*********************************************************");

        serializeToXML(XML_INPUT_FILE_PATH, AcademicGroup.createAcademicGroup());
        deserializeFromXML(OUTPUT_FILE_PATH, XML_INPUT_FILE_PATH);
        printOutputFiles(XML_INPUT_FILE_PATH, OUTPUT_FILE_PATH);
    }


    public static void testJSONbyXSteamSerialization() {
        System.out.println("*********************************************************\n"
                + "JSON by XStream serialization and deserialization:\n"
                + "*********************************************************");

        serializeToJSON(JSON_INPUT_FILE_PATH, AcademicGroup.createAcademicGroup());
        deserializeFromJSON(OUTPUT_FILE_PATH, JSON_INPUT_FILE_PATH);
        printOutputFiles(JSON_INPUT_FILE_PATH, OUTPUT_FILE_PATH);
    }

    public static void main(String[] args) {
        testXMLSerialization();
        System.out.println("\n");
        testJSONbyXSteamSerialization();
    }
}
