package ru.big198801;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

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
        Employee emp2 = new Employee(30,"Vadim", Position.MANAGER);
        Employee emp3 = new Employee(39,"Alexander", Position.DIRECTOR);
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


        //Найдите в списке слов самое длинное
        List<String> strings = Arrays.asList("qwer","qwerzy", "qwerty","qweray", "qwe", "qwerty12345");
        System.out.println(getLongestWord(strings));

        //Имеется строка с набором слов в нижнем регистре, разделенных пробелом.
        //Постройте хеш-мапы, в которой будут храниться пары: слово - сколько раз оно встречается во входной строке
        String s = "hi hi my name is is niko niko";
        System.out.println(getMapFromStringByCounting(s));

        //Отпечатайте в консоль строки из списка в порядке увеличения длины слова,
        //если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
        sortWordsByLength(strings);

        //Имеется массив строк, в каждой из которых лежит набор из 5 слов, разделенных пробелом,
        //найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них
        String[] array = new String[]{"hello how are you doinggg", "I'm fine thank you soooooo much"};
        System.out.println(getLongestWordFromArray(array));
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

    public static String getLongestWord(List<String> listOfWords)throws NoSuchElementException{
        return listOfWords.stream()
                .max(Comparator.comparing(String::length))
                .orElseThrow();
    }

    public static Map<String, Long> getMapFromStringByCounting(String string){
       return Arrays.stream(string.split(" "))
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()));
    }

    public static void sortWordsByLength(List<String> words){
         words.stream()
                 .sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()))
                 .forEach(System.out::println);
    }

    public static String getLongestWordFromArray(String[] array)throws NoSuchElementException{
        return Arrays.stream(array)
                .flatMap(str -> Arrays.stream(str.split(" ")))
                .max(Comparator.comparing(String::length))
                .orElseThrow();
    }
}