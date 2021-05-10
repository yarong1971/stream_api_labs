package stream_api.services;

import stream_api.common.model.Category;
import stream_api.common.model.Employee;
import stream_api.common.model.EmployeeWithSalaries;
import stream_api.common.model.Gender;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface streamUtil {
    int getSumEmployeesSalary(List<Employee> employees);
    void sumEmployeesSalariesPerYear(List<EmployeeWithSalaries> employees);
    long getNumberOfWordsInFile(Path textFilePath);
    double getAverageLengthOfWordInFile(Path textFilePath);
    String getEmployeeNamesSeperatedByComma(List<Employee> employees);
    List<String> getEmployeeListWithUppercasedNameSortedByLength(List<Employee> employees);
    List<String> getEmployeeNamesListSortDescendingBySalary(List<Employee> employees);
    Map<Gender, Integer> getSumOfSalariesGroupedByGender(List<Employee> employees);
    boolean isManEmployeesMoreExpensive(List<Employee> employees);
    Map<String, List<Employee>> getListOfEmployeesPerCompanyName(List<Employee> employees);
    Map<String, Long> getNumberOfeEmployeesPerCompanyName(List<Employee> employees);
    Map<String, Map<Category, Long>> listOfCompaniesPerCategory(List<Employee> employees);
}
