package part2.lab3.task_control;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class NumberProcessor {
    /**
     * Control Task #1. Read real values from a text file (to the end of the file),
     * find their sum and output to another text file. Use the Stream API tools.
     */
    public static void findAndWriteToFileRealNumbersSum(String outputFilePath, String inputFilePath) {
        try (Stream<String> stream = Files.lines(Paths.get(inputFilePath));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            Double realNumbersSum = stream.mapToDouble(Double::parseDouble).sum();
            writer.println("The real numbers sum:\t" + realNumbersSum);
            System.out.println("Successfully read real numbers from the text file, " +
                    "\ncalculated its sum " +
                    "\nand wrote them to another text file.\n");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Control Task #2. Read integer values from a text file,
     * replace negative values with modules, positive values with zeros,
     * and output the resulting values to another text file. Use the Stream API tools.
     */
    public static void findAndWriteToFileAbsoluteIntegers(String outputFilePath, String inputFilePath) {
        try (Stream<String> stream = Files.lines(Paths.get(inputFilePath));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            stream.mapToInt(Integer::parseInt)
                    .map(number -> number < 0 ? Math.abs(number) : 0)
                    .forEach(writer::println);
            System.out.println("Successfully read integer numbers from the text file, " +
                    "\nreplaced negative values with modules, positive values with zeros" +
                    "\nand wrote them to another text file.\n");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /** Control Task #3. Read integer values from a text file,
     * divide even elements by 2, increase odd elements by 2
     * and output the resulting values to another text file. Use the Stream API tools.*/
    public static void findAndWriteToFileOddAndEvenIntegers(String outputFilePath, String inputFilePath) {
        try (Stream<String> stream = Files.lines(Paths.get(inputFilePath));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            stream.mapToInt(Integer::parseInt)
                    .map(number -> number % 2 == 0 ? number / 2 : number * 2)
                    .forEach(writer::println);
            System.out.println("Successfully read integer numbers from the text file, " +
                    "\ndivided even values by 2, increase odd values by 2" +
                    "\nand wrote them to another text file.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
