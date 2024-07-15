package part2_lab6.task4;

import java.util.Scanner;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;

public class MathExpressionInterpreter {
    private static final String language = "js";

    private static boolean isEnd(String expression) {
        return expression.contains("end");
    }

    private static double interpretMathExpression(String expression) throws IllegalArgumentException {
        try (Context ctx = Context.newBuilder(language)
                .option("engine.WarnInterpreterOnly", "false")
                .build()) {
            try {
                Object result = ctx.eval(language, expression);
                return Double.parseDouble(result.toString());
            } catch (PolyglotException | NumberFormatException e) {
                throw new IllegalArgumentException("Invalid mathematical expression." + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a mathematical expression\n" +
                "(can consist of constants, mathematical operations, and brackets)\t" +
                "or \"end\" to finish the program execution:");

        while (true) {
            System.out.print("\nExpression:\t");
            String expression = scanner.nextLine();

            if (isEnd(expression)) {
                break;
            }

            try {
                double result = interpretMathExpression(expression);
                System.out.println("Result:\t" + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Error:\t" + e.getMessage());
            }
        }
    }
}
