package part2_lab5.task_control;

import lombok.Value;

/**
 * A class representing a person with various attributes, using Lombok annotations to generate immutable objects.
 * The @Value annotation is similar to the @Data annotation, but it creates immutable objects.
 * It combines @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE),
 * @Getter, @AllArgsConstructor, @ToString, and @EqualsAndHashCode.
 */
@Value
public class EmployeeReadOnlyUsingLombok {
    public String firstName;
    protected String lastName;
    private int experience;
    double salary;
}
