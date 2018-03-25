package ru.oz.mytutors.matorin.predicates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static ru.oz.mytutors.matorin.predicates.EmployeePredicates.filterEmployees;
import static ru.oz.mytutors.matorin.predicates.EmployeePredicates.isAdultFemale;
import static ru.oz.mytutors.matorin.predicates.EmployeePredicates.isAgeMoreThan;

/**
 * Created by Igor Ozol
 * on 03.09.2017.
 * Eldorado LLC
 */
public class PredicatesApplication {

    public static void main(String[] args) {
        Employee e1 = new Employee(1,23,"M","Rick","Beethovan");
        Employee e2 = new Employee(2,13,"F","Martina","Hengis");
        Employee e3 = new Employee(3,43,"M","Ricky","Martin");
        Employee e4 = new Employee(4,26,"M","Jon","Lowman");
        Employee e5 = new Employee(5,19,"F","Cristine","Maria");
        Employee e6 = new Employee(6,15,"M","David","Feezor");
        Employee e7 = new Employee(7,68,"F","Melissa","Roy");
        Employee e8 = new Employee(8,79,"M","Alex","Gussin");
        Employee e9 = new Employee(9,15,"F","Neetu","Singh");
        Employee e10 = new Employee(10,45,"M","Naveen","Jain");
        Employee e11 = new Employee(11,13,"M","Alex","Hengis");

        List<Employee> employees = new ArrayList<Employee>();
        employees.addAll(Arrays.asList(new Employee[]{e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11}));
//        employees.stream().forEach(System.out::println);

        // Композиция предикатов
        System.out.println("Взрослые женщины старше 35:");
        filterEmployees(employees, isAgeMoreThan(35).and(isAdultFemale()))
                .stream()
                .forEach(System.out::println);


        // Перенос приедиката в скоуп текущего метода, что бы не засорять класс PredicatesApplication локальным фильтром
        System.out.println(" Все сотрудники с именем Alex:");
        Function<String, Predicate<Employee>> withName =
                name ->  (employee) -> name.equalsIgnoreCase(employee.getFirstName());

        // withName.apply("Alex") -> создать предикат, который возвращает true, когда employee.name == "Alex"
        filterEmployees(employees, withName.apply("Alex"))
                .stream()
                .forEach(System.out::println);

//        System.out.println(filterEmployees(employees, isAdultMale()));
//
//        System.out.println(filterEmployees(employees, isAdultFemale()));
//
//        System.out.println(filterEmployees(employees, isAgeMoreThan(35)));
//
//        //Employees other than above collection of "isAgeMoreThan(35)" can be get using negate()
//        System.out.println(filterEmployees(employees, isAgeMoreThan(35).negate()));
    }
}
