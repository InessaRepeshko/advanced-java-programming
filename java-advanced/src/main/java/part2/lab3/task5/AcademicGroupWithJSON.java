package part2.lab3.task5;

import org.json.JSONArray;
import org.json.JSONObject;
import part2.lab3.task4.AcademicGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AcademicGroupWithJSON extends AcademicGroup {
    private List<StudentWithJSON> studentList = new ArrayList<>();
    public AcademicGroupWithJSON() {
    }

    public AcademicGroupWithJSON(String groupNumber) {
        super(groupNumber);
    }

    public AcademicGroupWithJSON(String groupNumber,
                                 List<StudentWithJSON> studentList) throws IllegalArgumentException {
        super(groupNumber);

        if (studentList.isEmpty()) {
            throw new IllegalArgumentException("The list of students cannot be empty.");
        }

        studentList.forEach(this::addStudent);
    }

    public List<StudentWithJSON> getStudentList() {
        return this.studentList;
    }

    public void addStudent(StudentWithJSON student) {
        studentList.add(student);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The group number:\t").append(getGroupNumber()).append(".\n")
                .append("The student list:\n")
                .append(getStudentList().stream()
                        .map(StudentWithJSON::toString)
                        .collect(Collectors.joining("\n")));

        return stringBuilder.toString();
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        getStudentList().forEach(student -> jsonArray.put(student.toJSON()));
        json.put("group_number", getGroupNumber());
        json.put("student_list", jsonArray);

        return json;
    }

    public AcademicGroupWithJSON fromJSON(JSONObject json) {
        AcademicGroupWithJSON academicGroup = new AcademicGroupWithJSON(json.getString("group_number"));
        JSONArray jsonArray = json.getJSONArray("student_list");

        for (int i = 0; i < jsonArray.length(); i++) {
            academicGroup.addStudent(
                    new StudentWithJSON().fromJSON(
                            jsonArray.getJSONObject(i))
            );
        }

        return academicGroup;
    }

    public static AcademicGroupWithJSON createAcademicGroupWithJSON() {
        return new AcademicGroupWithJSON(
                "CS-222a",
                Arrays.asList(
                        new StudentWithJSON("Blits Anna", 90.0),
                        new StudentWithJSON("Chizh Alexander", 92.30),
                        new StudentWithJSON("Ermolov Vladislav", 89.7),
                        new StudentWithJSON("Khorunzhii Tetiana", 95.02)
                )
        );
    }
}
