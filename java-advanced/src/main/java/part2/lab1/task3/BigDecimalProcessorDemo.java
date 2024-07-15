package part2.lab1.task3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This is the main class for demonstrating the functionality of {@link BigDecimalProcessor} implementations.
 * It provides methods to demonstrate the functionality of {@link BigDecimalProcessor} implementations such as
 * filling a list with random BigDecimal values, sorting the list in descending order by absolute value,
 * calculating the product of positive numbers in the list, and printing the list.
 * <p> The class uses Java's functional interfaces, Consumer and Function, to accept the methods as parameters
 * and execute them. This allows for a high degree of flexibility, as any method that fits the signature
 * of the functional interfaces can be passed in and executed.
 */
public class BigDecimalProcessorDemo {
    /** The upper limit for generating random BigDecimal values. */
    public static int BOUND = 200;

    /** The scale to be used for the BigDecimal product calculation, the number of digits to the right of the decimal point. */
    public static int PRODUCT_SCALE = 10;

    /** The size of the list to be filled with random BigDecimal values. */
    public static int LIST_SIZE = 4;

    /**
     * Demonstrates the methods of a {@link BigDecimalProcessor} implementation.
     * @param processor the {@link BigDecimalProcessor} implementation;
     * @param ListModifier a Consumer that modifies a list of BigDecimal values;
     * @param ProductCalculator a Function that calculates the product of positive numbers in a list;
     * @param ListPrinter a Consumer that prints a list of BigDecimal values.
     */
    public static void demonstrateMethods(BigDecimalProcessor processor,
                                          Consumer<List<BigDecimal>> ListModifier,
                                          Function<List<BigDecimal>, BigDecimal> ProductCalculator,
                                          Consumer<List<BigDecimal>> ListPrinter) {
        List<BigDecimal> list = new ArrayList<>();
        processor.fillWithRandomValues(list, LIST_SIZE);
        System.out.println("\tList after filling with random values:");
        ListPrinter.accept(list);

        ListModifier.accept(list);
        System.out.println("\tList after sorting by absolute value in descending order:");
        ListPrinter.accept(list);

        BigDecimal product = ProductCalculator.apply(list);
        product = product.setScale(PRODUCT_SCALE, RoundingMode.HALF_UP);
        System.out.println("\tProduct of positive numbers: " + product);
    }

    /**
     * The main method for this class. Creates instances of {@link BigDecimalProcessor} implementations
     * and demonstrates their methods. The {@code args} are not used.
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        BigDecimalProcessorWithLoops processor1 = new BigDecimalProcessorWithLoops(BOUND);
        BigDecimalProcessorWithCollection processor2 = new BigDecimalProcessorWithCollection(BOUND);
        BigDecimalProcessorWithStreams processor3 = new BigDecimalProcessorWithStreams(BOUND);

        System.out.println("***Demonstrating methods for BigDecimalHandlerByLoops:***");
        demonstrateMethods(processor1,
                BigDecimalProcessorWithLoops::sortDescendingByAbsoluteValue,
                BigDecimalProcessorWithLoops::calculateProductOfPositiveNumbers,
                BigDecimalProcessorWithLoops::printList);
        System.out.println("\n***Demonstrating methods for BigDecimalHandlerByCollection:***");
        demonstrateMethods(processor2,
                BigDecimalProcessorWithCollection::sortDescendingByAbsoluteValue,
                BigDecimalProcessorWithCollection::calculateProductOfPositiveNumbers,
                BigDecimalProcessorWithCollection::printList);

        System.out.println("\n***Demonstrating methods for BigDecimalHandlerByStreams:***");
        demonstrateMethods(processor3,
                BigDecimalProcessorWithStreams::sortDescendingByAbsoluteValue,
                BigDecimalProcessorWithStreams::calculateProductOfPositiveNumbers,
                BigDecimalProcessorWithStreams::printList);
    }
}
