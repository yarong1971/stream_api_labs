package stream_api.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeWithSalaries {
    private int id;
    private String name;
    private int[] salaries;
}
