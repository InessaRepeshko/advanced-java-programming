package part2.lab3.task4;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class FileUtils {
    public static List<String> readFromFile(String filePath) {
        try {
            return Files.lines(Paths.get(filePath)).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public static void printLines(List<String> lines) throws IOException {
        if (lines.isEmpty()) {
            System.out.println("The file is empty.");
        } else {
            System.out.println("The contents of the file:");
            lines.forEach(System.out::println);
        }
    }

    public static void writeToFile(String filePath, String data) throws  IOException {
        try {
            Files.write(Paths.get(filePath), data.getBytes());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void serializeToXML(String inputFilePath, AcademicGroup academicGroup) {
        System.out.println("The data to be serialised:\n" + academicGroup);

        XStream xStream = new XStream();
        xStream.alias("academicGroup", AcademicGroup.class);
        xStream.alias("student", Student.class);
        String xml = xStream.toXML(academicGroup);

        try {
            Files.write(Paths.get(inputFilePath), xml.getBytes());
            System.out.println("The file \"" + new File(inputFilePath).getName() + "\" successfully serialized.\n");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deserializeFromXML(String outputFilePath, String inputFilePath) {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);

        xStream.alias("academicGroup", AcademicGroup.class);
        xStream.alias("student", Student.class);

        AcademicGroup academicGroup = (AcademicGroup) xStream.fromXML(new File(inputFilePath));

        if (academicGroup != null) {
            try {
                writeToFile(outputFilePath, academicGroup.toString());
                System.out.println("The file \"" + new File(inputFilePath).getName() + "\" successfully deserialized.");
                System.out.println("The deserialized data:\n" + academicGroup);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void serializeToJSON(String inputFilePath, AcademicGroup academicGroup) {
        System.out.println("The data to be serialised:\n" + academicGroup);

        XStream xStream = new XStream(new JsonHierarchicalStreamDriver());
        xStream.alias("academicGroup", AcademicGroup.class);
        xStream.alias("studentList", Student.class);

        String json = xStream.toXML(academicGroup);

        try {
            Files.write(Paths.get(inputFilePath), json.getBytes());
            System.out.println("The file \"" + new File(inputFilePath).getName() + "\" successfully serialized.\n");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deserializeFromJSON(String outputFilePath, String inputFilePath) {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.addPermission(AnyTypePermission.ANY);

        xStream.alias("academicGroup", AcademicGroup.class);
        xStream.addImplicitCollection(AcademicGroup.class, "studentList");
        xStream.alias("studentList", Student.class);

        AcademicGroup academicGroup = (AcademicGroup) xStream.fromXML(new File(inputFilePath));

        if (academicGroup != null) {
            try {
                writeToFile(outputFilePath, academicGroup.toString());
                System.out.println("The file \"" + new File(inputFilePath).getName() + "\" successfully deserialized.");
                System.out.println("The deserialized data:\n" + academicGroup);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
