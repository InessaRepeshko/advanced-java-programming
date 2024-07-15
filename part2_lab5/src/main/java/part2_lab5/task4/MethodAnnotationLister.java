package part2_lab5.task4;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class prompts the user to input a class and method name. The console application then uses reflection to
 * obtain and display the modifiers, names and types of all methods of the class.
 * <p>Data for testing:
 * part2_lab5.task4.EmptyClass
 * part2_lab5.task4.StringProcessor
 */
public class MethodAnnotationLister {
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean isEnd(String input) {
        return input.equals("end");
    }

    public static String readClassName() {
        System.out.print("Enter the full name of the class:\t");
        return scanner.next();
    }

    public static String readMethodName() {
        System.out.print("Enter the method name:\t");
        return scanner.next();
    }

    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error:\tThe class with the entered name was not found.\n");
        }
    }

    public static Method[] getMethods(Class<?> cl) {
        return cl.getDeclaredMethods();
    }

    public static String getMethodsNames(Method[] methods) {
        if (methods.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            Arrays.stream(methods)
                    .forEach(method ->
                            stringBuilder.append("\n\t")
                                    .append(method.getName()));

            if (stringBuilder.isEmpty()) {
                stringBuilder.append("None");
            }

            stringBuilder.append("\n");

            return stringBuilder.toString();
        } else {
            throw new RuntimeException("Error:\tThere is no methods for class.\n");
        }
    }

    public static Method[] findMethodsByName(Method[] methods, String methodName) {
        Method[] array = Arrays.stream(methods)
                .filter(method ->
                        method.getName().equals(methodName))
                .toArray(Method[]::new);

        if (array.length > 0) {
            return  array;
        } else {
            throw new RuntimeException("Error:\tThe method with the entered name was not found.\n");
        }
    }

    public static Annotation[] getAnnotations(Method method) {
        return method.getDeclaredAnnotations();
    }

    public static void printAnnotationsInfo(Annotation[] annotations) {
        Arrays.stream(annotations).forEach(ann ->
                System.out.println("\t\tName: " + ann.annotationType().getSimpleName()));
    }
    public static void printMethodInfo(String className, String methodName) {
        try {
            Arrays.stream(
                    findMethodsByName(
                            getMethods(getClass(className)),
                            methodName))
                    .forEach(method -> {
                        String modifier = !Modifier.toString(
                                method.getModifiers()).isEmpty() ?
                                Modifier.toString(method.getModifiers())
                                : "none";
                        System.out.println("\n\tModifier: " + modifier
                                + "\n\tReturn type: " + method.getReturnType().getSimpleName()
                                + "\n\tName: " + method.getName()
                                + "\n\tAnnotations found: " + getAnnotations(method).length);
                        printAnnotationsInfo(getAnnotations(method));});
            System.out.println();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void printClassInfo(String className) {
        try {
            Class<?> cl = getClass(className);
            System.out.println("\nModifier: " + Modifier.toString(cl.getModifiers())
                    + "\nClass name: " + cl.getSimpleName()
                    + "\nMethods found: " + getMethods(cl).length);
            System.out.println("Methods list: " + getMethodsNames(getMethods(cl)));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        while (true) {
            String className, methodName;

            try {
                if (isEnd(className = readClassName())) {
                    break;
                }

                printClassInfo(className);

                while (true) {
                    try {
                        if (isEnd(methodName = readMethodName())) {
                            break;
                        }

                        printMethodInfo(className, methodName);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
