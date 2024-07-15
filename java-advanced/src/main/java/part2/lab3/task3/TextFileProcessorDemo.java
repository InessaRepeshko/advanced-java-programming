package part2.lab3.task3;

import java.io.IOException;
import java.util.List;

import static part2.lab3.task3.TextFileProcessor.*;

public class TextFileProcessorDemo {
    private static String inputFilePath = "src/main/resources/part2/lab3/task3/input.txt";
    private static String outputFilePath = "src/main/resources/part2/lab3/task3/output.txt";

    public static void main(String[] args) {
        try {
            printContentsOfFile(inputFilePath);

            List<String> filteredLines = filterLinesContainingWordFragment(
                    sortLinesByLength(readLines(inputFilePath)),
                    "a");
            writeLinesToFile(outputFilePath, filteredLines);
            System.out.println("\nText strings successfully read, sorted, filtered and written to file \"" + getFileName(outputFilePath) + "\".\n");

            printContentsOfFile(outputFilePath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
