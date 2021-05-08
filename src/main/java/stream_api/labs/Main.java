package stream_api.labs;

import org.w3c.dom.ls.LSOutput;
import stream_api.common.model.Category;
import stream_api.common.model.Employee;
import stream_api.common.model.EmployeeWithSalaries;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee(1,"YARON", "Male", 23000,"NayaCollage", Category.findBySalary(23000)),
                new Employee(2,"SHACHAR", "Female", 12000,"JohnBryce", Category.findBySalary(12000)),
                new Employee(3,"SHAKED", "Male", 17000,"NayaCollage", Category.findBySalary(17000)),
                new Employee(4,"Shira", "Female", 22000,"Sela", Category.findBySalary(22000)),
                new Employee(4,"Sivan", "Female", 15000,"JohnBryce", Category.findBySalary(14000)),
                new Employee(4,"Yakir", "Male", 11000,"Sela", Category.findBySalary(11000)),
                new Employee(5,"Ophir", "Male", 15000,"NayaCollage", Category.findBySalary(15000)));

        sumEmployeesSalary(employees);
        sumEmployeeSalariesPerYear();
        numberOfWordsInFile();
        averageLengthOfWordInFile();
        employeeNamesSeperatedByComma(employees);
        employeeListWithUppercasedNameSortedByLength(employees);
        employeeNamesListSortDescendingBySalary(employees);
        sumOfSalariesGroupedByGender(employees);
        System.out.println("Exercise 9: Is man employees more expensive? \n" + isManEmployeesMoreExpensive(employees));
        listOfEmployeesPerCompanyName(employees);
        NumberOfemployeesPerCompanyName(employees);
        listOfCompaniesPerCategory(employees);
    }

    public static void sumEmployeesSalary(List<Employee> employees) {
        System.out.println("Exercise 1: Sum of Employees Salary:");
        int totalSalaries = employees.stream().mapToInt(Employee::getSalary).sum();
        System.out.println("Total employees salary: " + totalSalaries);
    }

    //Lab2:
    private static void sumEmployeeSalariesPerYear() {
        System.out.println("Exercise 2: Sum of Employee Salaries per year:");
        EmployeeWithSalaries employee = new EmployeeWithSalaries(1,"Yaron",
                new int[] {10000, 10000, 10000,11000, 11000, 11000,13000, 13000, 14000,14000, 15000, 15000});

        int totalSalaries = IntStream.of(employee.getSalaries()).sum();
        System.out.println("Total employee salaries: " + totalSalaries);
    }

    private static void numberOfWordsInFile(){
        System.out.println("Exercise 3: Number of words in file:");
        long wordCount = 0;
        Path textFilePath = Paths.get("C:\\tmp\\BohemianRaphsody.txt");
        try {
            Stream<String> fileLines = Files.lines(textFilePath, Charset.defaultCharset());
            wordCount = fileLines.flatMap(line -> Arrays.stream(line.split(" "))).count();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("Number of words in BohemianRaphsody.txt: " + wordCount);
    }

    private static void averageLengthOfWordInFile(){
        System.out.println("Exercise 4: Average length of word in file:");
        double avgLengthOfWord = 0;
        Path textFilePath = Paths.get("C:\\tmp\\BohemianRaphsody.txt");
        try {
            Stream<String> fileLines = Files.lines(textFilePath, Charset.defaultCharset());
            avgLengthOfWord = fileLines.flatMap(line -> Arrays.stream(line.split(" ")))
                                 .mapToInt(String::length)
                                 .average()
                                 .getAsDouble();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        System.out.println("Average length of word in BohemianRaphsody.txt: " + avgLengthOfWord);
    }

    private static void employeeNamesSeperatedByComma(List<Employee> employees){
        System.out.println("Exercise 5: Employee names seperated by comma:");
        String names = employees.stream()
                                .map(Employee::getName)
                                .reduce((nameA, nameB) -> nameA + "," + nameB)
                                .get();
        System.out.println(names);
    }

    private static void employeeListWithUppercasedNameSortedByLength(List<Employee> employees){
        System.out.println("Exercise 6: Employee list with uppercased name sorted by length:");
        employees.stream()
            .map(Employee::getName)
            .filter(name -> name == name.toUpperCase(Locale.ROOT))
            .sorted((nameA, nameB) -> nameB.length() - nameA.length())
            .forEach(System.out::println);
    }

    private static void employeeNamesListSortDescendingBySalary(List<Employee> employees){
        System.out.println("Exercise 7: Employee names list sorted by salary in descending order:");
        employees.stream()
                .sorted((empA, empB) -> empB.getSalary() - empA.getSalary())
                .map(employee -> employee.getName() + ": " + employee.getSalary())
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private static void sumOfSalariesGroupedByGender(List<Employee> employees){
        System.out.println("Exercise 8: Sum of salaries grouped by gender:");
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.summingInt(Employee::getSalary)))
                .forEach((k,v) -> System.out.println(k + ": " + v));
    }

    private static boolean isManEmployeesMoreExpensive(List<Employee> employees){
        Map<String, Integer> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,
                         Collectors.summingInt(Employee::getSalary)));

        return map.get("Male").intValue() > map.get("Female").intValue();
    }

    private static void listOfEmployeesPerCompanyName(List<Employee> employees){
        System.out.println("Exercise 10: List of employees grouped by company name:");
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getCompanyName))
                .forEach((k,v) -> System.out.println(k + ": " + v));
    }

    private static void NumberOfemployeesPerCompanyName(List<Employee> employees){
        System.out.println("Exercise 11: Number of employees per company name:");
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getCompanyName,
                         Collectors.counting()))
                .forEach((k,v) -> System.out.println(k + ": " + v));
    }

    private static void listOfCompaniesPerCategory(List<Employee> employees){
        System.out.println("Exercise 12: List of companies per category:");
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getCategory))
                .forEach((k,v) -> System.out.println(k + ": " +
                                        v.stream().map(Employee::getCompanyName).distinct()
                                        .reduce((nameA, nameB) -> nameA + "," + nameB)
                                        .get()));
    }
}