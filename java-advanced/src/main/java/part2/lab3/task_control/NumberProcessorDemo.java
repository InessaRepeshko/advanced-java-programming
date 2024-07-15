package part2.lab3.task_control;

import static part2.lab3.task_control.NumberProcessor.*;

public class NumberProcessorDemo {
    private static final String INPUT_REAL_NUMENRS = "src/main/resources/part2/lab3/task_control/inputRealNumbers.txt";
    private static final String INPUT_INTEGERS = "src/main/resources/part2/lab3/task_control/inputIntegers.txt";
    private static final String OUTPUT_REAL_NUMBERS = "src/main/resources/part2/lab3/task_control/outputRealNumbers.txt";
    private static final String OUTPUT_ABS_AND_ZERO_INTEGERS = "src/main/resources/part2/lab3/task_control/outputAbsoluteAndZeroIntegers.txt";
    private static final String OUTPUT_ODD_AND_EVEN_INTEGERS = "src/main/resources/part2/lab3/task_control/outputOddAndEvenIntegers.txt";

    public static void main(String[] args) {
        findAndWriteToFileRealNumbersSum(OUTPUT_REAL_NUMBERS, INPUT_REAL_NUMENRS);
        findAndWriteToFileAbsoluteIntegers(OUTPUT_ABS_AND_ZERO_INTEGERS, INPUT_INTEGERS);
        findAndWriteToFileOddAndEvenIntegers(OUTPUT_ODD_AND_EVEN_INTEGERS, INPUT_INTEGERS);
    }
}
