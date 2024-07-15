package part2.lab3.task4;

import java.util.regex.Pattern;

public class Student {
    private String fullName;

    private Double ratingScore;

    public Student() {}

    public Student(String fullName, Double ratingScore) throws IllegalArgumentException {
        if (fullName.isEmpty()) {
            throw new IllegalArgumentException("The full name cannot be null.");
        }

        if (!Pattern.compile("^[A-Z][a-z]+ [A-Z][a-z]+$").matcher(fullName).matches()) {
            throw new IllegalArgumentException("The full name must be in the format \"Surname Name\".");
        }

        if (ratingScore == null || ratingScore <= 0) {
            throw new IllegalArgumentException("The rating score must be greater than zero.");
        }

        this.fullName = fullName;
        this.ratingScore = ratingScore;
    }

    public String getFullName() {
        return fullName;
    }

    public Double getRatingScore() {
        return ratingScore;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRatingScore(Double ratingScore) {
        this.ratingScore = ratingScore;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\tFull name: \t\"").append(getFullName())
                .append("\";\tRating score:\t").append(getRatingScore()).append(".");

        return stringBuilder.toString();
    }
}
