package part2.lab3.task5;

import part2.lab3.task4.FileUtilsDemo;

import static part2.lab3.task5.FileUtilsWithJSON.*;

public class FileUtilsWithJSONDemo {
    private static final String INPUT_FILE_PATH = "src/main/resources/part2/lab3/task5/AcademicGroup.json";
    private static final String OUTPUT_FILE_PATH = "src/main/resources/part2/lab3/task5/AcademicGroup.txt";

    public static void testJSONSerialization() {
        System.out.println("*********************************************************\n"
                + "JSON serialization and deserialization:\n"
                + "*********************************************************");

        serializeToJSON(INPUT_FILE_PATH, AcademicGroupWithJSON.createAcademicGroupWithJSON());
        deserializeFromJSON(OUTPUT_FILE_PATH, INPUT_FILE_PATH);
        FileUtilsDemo.printOutputFiles(INPUT_FILE_PATH, OUTPUT_FILE_PATH);
    }

    public static void main(String[] args) {
        testJSONSerialization();
    }
}
