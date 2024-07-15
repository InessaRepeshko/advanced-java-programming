package part2.lab3.task5;

import org.json.JSONObject;
import part2.lab3.task4.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtilsWithJSON extends FileUtils {
    public static void serializeToJSON(String inputFilePath, AcademicGroupWithJSON academicGroup) {
        System.out.println("The data to be serialised:\n" + academicGroup);

        try (FileWriter writer = new FileWriter(inputFilePath)) {
            writer.write(academicGroup.toJSON().toString(1));
            System.out.println("The file \"" + new File(inputFilePath).getName() + "\" successfully serialized.\n");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deserializeFromJSON(String outputFilePath, String inputFilePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(inputFilePath)));
            JSONObject json = new JSONObject(content);
            AcademicGroupWithJSON academicGroup = new AcademicGroupWithJSON().fromJSON(json);

            if (academicGroup != null) {
                writeToFile(outputFilePath, academicGroup.toString());
                System.out.println("The file \"" + new File(inputFilePath).getName() + "\" successfully deserialized.");
                System.out.println("The deserialized data:\n" + academicGroup);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
