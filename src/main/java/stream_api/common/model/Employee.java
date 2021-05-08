package stream_api.common.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int id;
    private String name;
    private String gender;
    private int salary;
    private String companyName;
    private Category category;

    public Employee(String name) {
        this.name = name;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Employee;
    }

}
