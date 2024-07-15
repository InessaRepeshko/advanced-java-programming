package part2_lab5.task2;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;


/**
 * This class prompts the user to input a class name. The console application then uses reflection to
 * obtain and display the modifiers, names and types of all fields of the class.
 * <p>Data for testing:
 * part2_lab5.task_control.TestEmptyClass
 * part2_lab5.task_control.TestClass
 */
public class ClassFieldLister {
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean isEnd(String input) {
        return input.equals("end");
    }

    public static String readClassName() {
        System.out.print("\nEnter the full name of the class:\t");
        return scanner.next();
    }

    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error: \tThe class with the entered name was not found.");
        }
    }

    public static Field[] getFields(Class<?> cl) {
        return cl.getDeclaredFields();
    }

    public static void printFieldsInfo(Class<?> cl) {
        Arrays.stream(getFields(cl)).forEach(field -> {
            String modifier = !Modifier.toString(field.getModifiers()).isEmpty() ?
                    Modifier.toString(field.getModifiers())
                    : "none";
            System.out.println("\tModifier: " + modifier
                    + "\n\tType: " + field.getType().getSimpleName()
                    + "\n\tName: " + field.getName() + "\n");
        });
    }

    public static void printClassInfo(String className) {
        try {
            Class<?> cl = getClass(className);
            Field[] fields = getFields(cl);

            System.out.println("\nModifier: " + Modifier.toString(cl.getModifiers())
                    + "\nClass name: " + cl.getSimpleName()
                    + "\nFields found: " + fields.length + "\n");
            printFieldsInfo(cl);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        while (true) {
            String input = readClassName();

            if (isEnd(input)) {
                break;
            }

            printClassInfo(input);
        }
    }
}
