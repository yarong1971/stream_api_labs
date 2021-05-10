package stream_api.services;

import lombok.SneakyThrows;
import stream_api.common.model.Category;
import stream_api.common.model.Employee;
import stream_api.common.model.EmployeeWithSalaries;
import stream_api.common.model.Gender;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class streamUtilImpl implements streamUtil {
    @Override
    public int getSumEmployeesSalary(List<Employee> employees) {
        return employees.stream().mapToInt(Employee::getSalary).sum();
    }

    @Override
    public void sumEmployeesSalariesPerYear(List<EmployeeWithSalaries> employees) {

    }

    @SneakyThrows
    @Override
    public long getNumberOfWordsInFile(Path textFilePath) {
        Stream<String> fileLines = Files.lines(textFilePath, Charset.defaultCharset());
        return fileLines.flatMap(line -> Arrays.stream(line.split(" "))).count();
    }

    @SneakyThrows
    @Override
    public OptionalDouble getAverageLengthOfWordInFile(Path textFilePath) {
        Stream<String> fileLines = Files.lines(textFilePath, Charset.defaultCharset());
        return fileLines.flatMap(line -> Arrays.stream(line.split(" ")))
                .mapToInt(String::length)
                .average();
    }

    @Override
    public Optional<String> getEmployeeNamesSeperatedByComma(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getName)
                .reduce((nameA, nameB) -> nameA + "," + nameB);
    }

    @Override
    public List<String> getEmployeeListWithUppercasedNameSortedByLength(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getName)
                .filter(name -> name.equals(name.toUpperCase(Locale.ROOT)))
                .sorted((nameA, nameB) -> nameB.length() - nameA.length())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getEmployeeNamesListSortDescendingBySalary(List<Employee> employees) {
        return employees.stream()
                .sorted((empA, empB) -> empB.getSalary() - empA.getSalary())
                .map(employee -> employee.getName() + ": " + employee.getSalary())
                .collect(Collectors.toList());
    }

    @Override
    public Map<Gender, Integer> getSumOfSalariesGroupedByGender(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.summingInt(Employee::getSalary)));
    }

    @Override
    public boolean isManEmployeesMoreExpensive(List<Employee> employees) {
        Map<Gender, Integer> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                        Collectors.summingInt(Employee::getSalary)));

        return map.get(Gender.MALE) > map.get(Gender.FEMALE);
    }

    @Override
    public Map<String, List<Employee>> getListOfEmployeesPerCompanyName(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getCompanyName));
    }

    @Override
    public Map<String, Long> getNumberOfeEmployeesPerCompanyName(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getCompanyName,
                        Collectors.counting()));
    }

    public Map<String, Map<Category, Long>> listOfCompaniesProfileByEmployeesCategory(List<Employee> employees) {
        System.out.println("Exercise 12: List of companies per category:");
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getCompanyName,
                        Collectors.groupingBy(Employee::getCategory,
                                Collectors.counting())));
    }
}
