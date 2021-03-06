package stream_api.labs;

import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.ls.LSOutput;
import stream_api.common.model.Category;
import stream_api.common.model.Employee;
import stream_api.common.model.EmployeeWithSalaries;
import stream_api.common.model.Gender;
import stream_api.services.streamUtil;
import stream_api.services.streamUtilImpl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "YARON", Gender.MALE, 23000, "NayaCollage", Category.findBySalary(23000)),
                new Employee(2, "SHACHAR", Gender.FEMALE, 12000, "JohnBryce", Category.findBySalary(12000)),
                new Employee(3, "SHAKED", Gender.MALE, 17000, "NayaCollage", Category.findBySalary(17000)),
                new Employee(4, "Shira", Gender.FEMALE, 22000, "JohnBryce", Category.findBySalary(22000)),
                new Employee(4, "Sivan", Gender.FEMALE, 15000, "JohnBryce", Category.findBySalary(14000)),
                new Employee(4, "Yakir", Gender.MALE, 11000, "NayaCollage", Category.findBySalary(11000)),
                new Employee(5, "Ophir", Gender.MALE, 15000, "NayaCollage", Category.findBySalary(15000)));

        List<EmployeeWithSalaries> employeesWithSalaries = Arrays.asList(
                new EmployeeWithSalaries(1,"Yaron",
                        new int[] {10000, 10000, 10000,11000, 11000, 11000,13000, 13000, 14000,14000, 15000, 15000}),
                new EmployeeWithSalaries(1,"Shira",
                        new int[] {14000, 14000, 15000,16000, 16000, 16000,17000, 17000, 17000,18000, 18000, 19000}));

        streamUtil util = new streamUtilImpl();

        int sumEmployeesSalary = util.getSumEmployeesSalary(employees);
        System.out.println("sumEmployeesSalary: " + sumEmployeesSalary);

        long numberOfWordsInFile = util.getNumberOfWordsInFile(Path.of("C:\\tmp\\BohemianRaphsody.txt"));
        System.out.println("numberOfWordsInFile: " + numberOfWordsInFile);

        OptionalDouble averageLengthOfWordInFile = util.getAverageLengthOfWordInFile(Path.of("C:\\tmp\\BohemianRaphsody.txt"));
        averageLengthOfWordInFile.ifPresent(avgLength -> System.out.println("averageLengthOfWordInFile: " + avgLength));

        Optional<String> employeeNamesSeperatedByComma = util.getEmployeeNamesSeperatedByComma(employees);
        employeeNamesSeperatedByComma.ifPresent(names -> System.out.println("employeeNamesSeperatedByComma: " + names));

        List<String> employeeListWithUppercasedNameSortedByLength = util.getEmployeeListWithUppercasedNameSortedByLength(employees);
        System.out.println("employeeListWithUppercasedNameSortedByLength: " + employeeListWithUppercasedNameSortedByLength);

        List<String> employeeNamesListSortDescendingBySalary = util.getEmployeeNamesListSortDescendingBySalary(employees);
        System.out.println("employeeNamesListSortDescendingBySalary: " + employeeNamesListSortDescendingBySalary);

        Map<Gender, Integer> sumOfSalariesGroupedByGender = util.getSumOfSalariesGroupedByGender(employees);
        System.out.println("sumOfSalariesGroupedByGender: " + sumOfSalariesGroupedByGender);

        Map<String, Long> numberOfeEmployeesPerCompanyName = util.getNumberOfeEmployeesPerCompanyName(employees);
        System.out.println("numberOfeEmployeesPerCompanyName: " + numberOfeEmployeesPerCompanyName);

        boolean manEmployeesMoreExpensive = util.isManEmployeesMoreExpensive(employees);
        System.out.println("isManEmployeesMoreExpensive: " + manEmployeesMoreExpensive);

        Map<String, List<Employee>> listOfEmployeesPerCompanyName = util.getListOfEmployeesPerCompanyName(employees);
        System.out.println("listOfEmployeesPerCompanyName: " + listOfEmployeesPerCompanyName);

        Map<String, Map<Category, Long>> listOfCompaniesProfileByEmployeesCategory = util.listOfCompaniesProfileByEmployeesCategory(employees);
        System.out.println("listOfCompaniesProfileByEmployeesCategory: " + listOfCompaniesProfileByEmployeesCategory);
    }
}