package part2.lab3.task5;

import org.json.JSONObject;
import part2.lab3.task4.Student;

public class StudentWithJSON extends Student {
    public StudentWithJSON() {}

    public StudentWithJSON(String fullName, Double ratingScore) throws IllegalArgumentException {
        super(fullName, ratingScore);
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        json.put("full_name", getFullName());
        json.put("rating_score", getRatingScore());

        return json;
    }

    public StudentWithJSON fromJSON(JSONObject json) {
        return new StudentWithJSON(
                json.getString("full_name"),
                json.getDouble("rating_score")
        );
    }
}

