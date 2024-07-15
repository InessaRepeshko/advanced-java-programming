package part2.lab3.task4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AcademicGroup {
    private String groupNumber;

    private List<Student> studentList = new ArrayList<>();

    public AcademicGroup() {
    }

    public AcademicGroup(String groupNumber) throws IllegalArgumentException {
        if (groupNumber.isEmpty()) {
            throw new IllegalArgumentException("The group number cannot be null.");
        }

        if (!Pattern.compile("^[A-Z]{1,3}-\\d{3}[a-z]$").matcher(groupNumber).matches()) {
            throw new IllegalArgumentException("The group number must be in the format \"NNN-000n\".");
        }

        setGroupNumber(groupNumber);
    }

    public AcademicGroup(String groupNumber,
                         List<Student> studentList) throws IllegalArgumentException {
        if (groupNumber.isEmpty()) {
            throw new IllegalArgumentException("The group number cannot be null.");
        }

        if (!Pattern.compile("^[A-Z]{1,3}-\\d{3}[a-z]$").matcher(groupNumber).matches()) {
            throw new IllegalArgumentException("The group number must be in the format \"NNN-000n\".");
        }

        if (studentList.isEmpty()) {
            throw new IllegalArgumentException("The list of students cannot be empty.");
        }

        setGroupNumber(groupNumber);
        studentList.forEach(this::addStudent);
    }

    public String getGroupNumber() {
        return this.groupNumber;
    }

    private List<Student> getStudentList() {
        return this.studentList;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    private void addStudent(Student student) {
        this.studentList.add(student);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The group number:\t").append(getGroupNumber()).append(".\n")
                .append("The student list:\n")
                .append(getStudentList().stream()
                        .map(Student::toString)
                        .collect(Collectors.joining("\n")));

        return stringBuilder.toString();
    }

    public static AcademicGroup createAcademicGroup() {
        return new AcademicGroup(
                "CS-222a",
                Arrays.asList(
                        new Student("Blits Anna", 90.0),
                        new Student("Chizh Alexander", 92.30),
                        new Student("Ermolov Vladislav", 89.7),
                        new Student("Khorunzhii Tetiana", 95.02)
                )
        );
    }
}
