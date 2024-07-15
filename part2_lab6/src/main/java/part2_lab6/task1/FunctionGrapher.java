package part2_lab6.task1;

import javafx.application.Platform;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class FunctionGrapher implements Runnable {
    private double constantA;
    private double constantB;

    private double rangeFrom;
    private double rangeTo;
    private double rangeStep;

    private String functionF;
    private String functionG;

    private final SortedMap<Number, Number> pairsXY;

    private final Runnable displayFunction;
    private final Runnable showErrorFunction;
    private String errorMessage;

    private final String className = "FunctionProcessor";
    private final File sourcePath = new File("src/main/resources/part2_lab6/task1/" + className + ".java");

    public FunctionGrapher(Runnable buildGraphFunction, Runnable showErrorFunction) {
        this.displayFunction = buildGraphFunction;
        this.showErrorFunction = showErrorFunction;
        pairsXY = new TreeMap<>(Comparator.comparingDouble(Number::doubleValue));
    }

    public synchronized double getCostantA() {
        return constantA;
    }
    public synchronized void setCostantA(double costantA) {
        this.constantA = costantA;
    }
    public synchronized double getCostantB() {
        return constantB;
    }
    public synchronized void setConstantB(double costantB) {
        this.constantB = costantB;
    }

    public synchronized double getRangeFrom() {
        return rangeFrom;
    }
    public synchronized void setRangeFrom(double from) {
        this.rangeFrom = from;
    }
    public synchronized double getRangeTo() {
        return rangeTo;
    }
    public synchronized void setRangeTo(double to) {
        this.rangeTo = to;
    }
    public synchronized double getRangeStep() {
        return rangeStep;
    }
    public synchronized void setRangeStep(double step) {
        this.rangeStep = step;
    }
    public boolean isValidRangeBoundaries() {
        return getRangeFrom() < getRangeTo();
    }
    public boolean isValidRangeStep() {
        return getRangeStep() > 0.0 && getRangeStep() <= Math.abs(getRangeTo() - getRangeFrom());
    }


    public synchronized String getFunctionF() {
        return functionF;
    }
    public synchronized void setFunctionF(String functionF) {
        this.functionF = functionF;
    }
    public synchronized String getFunctionG() {
        return functionG;
    }
    public synchronized void setFunctionG(String functionG) {
        this.functionG = functionG;
    }


    public synchronized String getErrorMessage() {
        return errorMessage;
    }
    public synchronized void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public synchronized SortedMap<Number, Number> getPairsXY() {
        return pairsXY;
    }

    @Override
    public void run() {
        compileCodeDynamically(getFunctionF(), getFunctionG());
    }

    private void compileCodeDynamically(String functionF, String functionG) {
        if (sourcePath.exists()) {
            sourcePath.delete();
        }

        File fileClass = new File(sourcePath.getName().replace(".java", ".class"));

        if (fileClass.exists()) {
            fileClass.delete();
        }

        pairsXY.clear();

        generateSource(functionF, functionG);
        compileSource();
        runClass();
    }

    private void generateSource(String functionF, String functionG) {
        try (PrintWriter out = new PrintWriter(new FileWriter(sourcePath))) {
            out.println("public class " + className + " {");

            out.println("    public double f(double x) {");
            out.println("        return " + functionF + ";");
            out.println("    }");

            out.println("    public double g(double x) {");
            out.println("        return " + functionG + ";");
            out.println("    }");

            out.println("    public double h(double a, double b, double x) {");
            out.println("        return f(a / x) - g(b / x);");
            out.println("    }");
            out.println("}");
        } catch (IOException e) {
            setErrorMessage("Could not generate source file to process functions:\n" + e);
            System.err.println("\n" + getErrorMessage());
            Platform.runLater(showErrorFunction);
        }
    }

    private void compileSource() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        if (compiler.run(null, null, null, sourcePath.getPath()) != 0) {
            setErrorMessage("Could not compile code dynamically to process functions.");
            System.err.println("\n" + getErrorMessage());
            Platform.runLater(showErrorFunction);
        }
    }

    private void runClass() {
        try {
            File pathName = new File("src/main/resources/part2_lab6/task1");
            URLClassLoader classLoader = new URLClassLoader(
                    new URL[] {pathName.toURI().toURL()});
            Class<?> dynamicClass = Class.forName(
                    className,
                    true,
                    classLoader);

            Object instance = dynamicClass.getDeclaredConstructor().newInstance();
            java.lang.reflect.Method method = dynamicClass.getDeclaredMethod(
                    "h",
                    double.class,
                    double.class,
                    double.class);
            runMethodForRange(
                    instance,
                    method);
        } catch (MalformedURLException
                 | ClassNotFoundException
                 | NoSuchMethodException
                 | InvocationTargetException
                 | InstantiationException
                 | IllegalAccessException
                 | IllegalArgumentException e) {
            setErrorMessage("Could not run dynamic class file to process functions:\n" + e);
            System.err.println("\n" + getErrorMessage());
            Platform.runLater(showErrorFunction);
        }
    }

    private void runMethodForRange(Object instance, java.lang.reflect.Method method) {
        try {
            for (double x = getRangeFrom(); x <= getRangeTo(); x+= getRangeStep()) {
                double y = (double) method.invoke(
                        instance,
                        getCostantA(),
                        getCostantB(),
                        x);
                pairsXY.putIfAbsent(x, y);
            }

            if (displayFunction != null) {
                Platform.runLater(displayFunction);
            }
        } catch (IllegalAccessException
                 | InvocationTargetException
                 | IllegalArgumentException e) {
            setErrorMessage("Could not run dynamic class method for range to process functions:\n" + e);
            System.err.println("\n" + getErrorMessage());
            Platform.runLater(showErrorFunction);
        }
    }

    public void start() {
        Thread grapherThread = new Thread(this);
        grapherThread.start();
    }
}
