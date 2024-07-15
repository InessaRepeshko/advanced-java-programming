package part2.lab3.task2;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static part2.lab3.task2.DirectoryLister.listFilesRecursivelyByIOFileClass;
import static part2.lab3.task2.DirectoryLister.listFilesRecursivelyByNIO;

public class DirectoryListerDemo {
    private static String[] DIR_PATHES = {
            "src/main/java/part2/lab2",
            "invalid_path",
            "src/main/resources/part2/lab3/task2"
    };

    public static void main(String[] args) {
        Arrays.stream(DIR_PATHES)
                .forEach(path -> {
                    System.out.println("\nThe directory path:\t" + path);
                    File directory = new File(path);

                    if (!directory.exists()
                            || !directory.isDirectory()) {
                        System.out.println("The given directory does not exist");
                        return;
                    }

                    System.out.println("List subdirectories and files recursively by java.io.File:");

                    try {
                        listFilesRecursivelyByIOFileClass(directory);
                    } catch (NullPointerException e) {
                        System.err.println(e.getMessage());
                    }

                    System.out.println("List subdirectories and files recursively by java.nio.file:");

                    try {
                        listFilesRecursivelyByNIO(directory.toPath());
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });
    }
}
