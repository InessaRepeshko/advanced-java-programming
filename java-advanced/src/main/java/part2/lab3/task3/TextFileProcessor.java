package part2.lab3.task3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TextFileProcessor {
    public static List<String> readLines(String inputFilePath) throws IOException {
        return Files.lines(Paths.get(inputFilePath))
                .collect(Collectors.toList());
    }

    public static List<String> sortLinesByLength(List<String> lines) {
        return lines.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
    }

    public static List<String> filterLinesContainingWordFragment(List<String> lines, String fragment) {
        return lines.stream()
                .filter(line -> line.contains(fragment))
                .collect(Collectors.toList());
    }

    public static void writeLinesToFile(String outputFilePath, List<String> lines) throws IOException {
        Files.write(Paths.get(outputFilePath), lines);
    }

    public static void printContentsOfFile(String filePath) throws IOException {
        System.out.println("The contents of the file \"" + getFileName(filePath) + "\":");

        try {
            Files.readAllLines(Paths.get(filePath)).stream().forEach(System.out::println);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String getFileName(String filePath) {
        return new File(filePath).getName();
    }
}
