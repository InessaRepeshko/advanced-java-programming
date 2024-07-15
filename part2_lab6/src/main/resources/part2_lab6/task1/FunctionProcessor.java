public class FunctionProcessor {
    public double f(double x) {
        return Math.pow(x,3);
    }
    public double g(double x) {
        return x + 5;
    }
    public double h(double a, double b, double x) {
        return f(a / x) - g(b / x);
    }
}
