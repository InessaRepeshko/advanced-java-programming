package part2_lab5.task4;

public class StringProcessor {
    public String testFieldString;
    public int testFieldInt;

    @CustomAnnotation
    public String getSubstring(String str, String after) {
        return str.substring(str.lastIndexOf(after));
    }

    @CustomAnnotation
    protected String getSubstring(String str, int atIndex) {
        return str.substring(atIndex);
    }

    private String getSubstring(String str, int begin, int end) {
        return str.substring(begin, end);
    }

    public boolean containsSubstring(String str, String substr) {
        return str.contains(substr);
    }

    public int getSubstringIndex(String str, String substr) {
        return str.indexOf(substr);
    }

    public String toString() {
        return testFieldString + " " + testFieldInt;
    }
}
