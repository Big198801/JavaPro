package ru.big198801;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
        System.out.println(maxThirdNumber(Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13)));

        //Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9,
        // в отличие от прошлой задачи здесь разные 10 считает за одно число)
        System.out.println(uniqMaxThirdNumber(Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13)));

        //Имеется список объектов типа Сотрудник (имя, возраст, должность),
        // необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста

    }

    public static int maxThirdNumber(List<Integer> numbers) throws NoSuchElementException {
        return numbers.stream().sorted(Comparator.reverseOrder()).skip(2).findFirst().orElseThrow();
    }

    public static int uniqMaxThirdNumber(List<Integer> numbers)throws NoSuchElementException{
        return numbers.stream().distinct().sorted(Comparator.reverseOrder()).skip(2).findFirst().orElseThrow();
    }

    public static List<String> getThreeOldestEngineers(List<Employee> employees){
        return employees.stream().filter(pos -> pos.getPosition().equals(Position.ENGINEER))
                .sorted((o1, o2) -> o2.getAge()- o1.getAge())
                .map(Employee::getName)
                .limit(3)
                .toList();
    }

}