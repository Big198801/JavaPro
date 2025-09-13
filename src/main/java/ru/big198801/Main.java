package ru.big198801;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
        System.out.println(getMaxThirdNumber(Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13)));

        //Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9,
        //в отличие от прошлой задачи здесь разные 10 считает за одно число)
        System.out.println(getUniqueMaxThirdNumber(Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13)));


        //Имеется список объектов типа Сотрудник (имя, возраст, должность),
        //необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
        Employee emp1 = new Employee(34,"Nikolay", Position.ENGINEER);
        Employee emp2 = new Employee(30,"Vadim", Position.JUNIOR);
        Employee emp3 = new Employee(39,"Alexander", Position.SENIOR);
        Employee emp4 = new Employee(23,"Danil", Position.ENGINEER);
        Employee emp5 = new Employee(45,"Eugine", Position.ENGINEER);
        Employee emp6 = new Employee(60,"Igor", Position.ENGINEER);
        List<Employee> employeeList= new ArrayList<>();
        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);
        employeeList.add(emp4);
        employeeList.add(emp5);
        employeeList.add(emp6);
        List<String> result = getThreeOldestEngineers(employeeList);
        result.forEach(System.out::println);


        //Имеется список объектов типа Сотрудник (имя, возраст, должность),
        //посчитайте средний возраст сотрудников с должностью «Инженер»
        System.out.println(getEngineersAverageAge(employeeList));

    }

    public static int getMaxThirdNumber(List<Integer> numbers) throws NoSuchElementException {
        return numbers.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow();
    }

    public static int getUniqueMaxThirdNumber(List<Integer> numbers)throws NoSuchElementException{
        return numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow();
    }

    public static List<String> getThreeOldestEngineers(List<Employee> employees){
        return employees.stream()
                .filter(pos -> pos.getPosition().equals(Position.ENGINEER))
                .sorted((o1, o2) -> o2.getAge()- o1.getAge())
                .map(Employee::getName)
                .limit(3)
                .toList();
    }

    public static int getEngineersAverageAge(List<Employee> employeeList)throws NoSuchElementException{
        return (int) Math.round(employeeList.stream()
                .filter(pos -> pos.getPosition().equals(Position.ENGINEER))
                .mapToInt(Employee::getAge)
                .average()
                .orElseThrow());
    }

}