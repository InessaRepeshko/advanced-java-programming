package part2.lab3.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

public class DirectoryLister {
    public static void listFilesRecursivelyByIOFileClass(File directory) throws NullPointerException {
        Arrays.stream(directory.listFiles())
                .forEach(file -> {
                    if (file.isDirectory()) {
                        System.out.println("Directory:\t" + file.getAbsolutePath());
                        listFilesRecursivelyByIOFileClass(file);
                    } else {
                        System.out.println("\tFile:\t" + file.getAbsolutePath());
                    }
                });
    }

    public static void listFilesRecursivelyByNIO(Path directory) throws IOException {
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                System.out.println("Directory:\t" + dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                System.out.println("\tFile:\t" + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                System.err.println(e);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
