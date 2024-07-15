package part2_lab5.task_control;

import lombok.Data;

/**
 * A class representing a person with various attributes, using Lombok annotations to generate boilerplate code.
 * The @Data annotation combines @Getter, @Setter, @RequiredArgsConstructor, @ToString, and @EqualsAndHashCode.
 */
@Data
public class EmployeeUsingLombok {
    public String firstName;
    protected String lastName;
    private int experience;
    double salary;
}
