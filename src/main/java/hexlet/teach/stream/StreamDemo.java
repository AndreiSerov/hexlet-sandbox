package hexlet.teach.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author andreiserov
 */
public class StreamDemo {

    static Map<String, Person> fullNames = new HashMap<>();

    static {
        fullNames.put("Petya", new Person("Petya", "Petr"));
        fullNames.put("Vasya", new Person("Vasya", "Vasiliy"));
        fullNames.put("Sema", new Person("Sema", "Semen"));
        fullNames.put("Vova", new Person("Vova", "Vladimir"));
    }

    static List<String> names = List.of(
        "Petya",
        "Vasya",
        "Sema",
        "Vova"
    );

    public static void main(String[] args) {

        final int[] ints = IntStream.range(1, 1_000).toArray();

        final int sum = IntStream.range(1, 1_000)
            .boxed()
            .reduce(
                0, (acc, it) -> acc + it
            );

//        IntStream.range(1, 1_000)
//            .boxed()
//            .reduce(
////                new ArrayList<Integer>(),
////                (acc, it) -> acc.add(it)
//            );

        // reduce(acc, (x


        final long loopSum = sumOfRange(1000);
        System.out.println("loopResult");
    }
    
    static long sumOfRange(int num) {
        int[] ints = new int[10];
        int count = 0;
        for (int i = 1; i < num; i++) {
            if (ints.length == count) ints = Arrays.copyOf(ints, count * 2);
            ints[count++] = i;
        }
        ints = Arrays.copyOfRange(ints, 0, count);

        long sum = 0;
        for (int intNum : ints) {
            sum += intNum;
        }
        return sum;
    }

    static List<String> filterNamesThatFullnamesAreLongerThen5Symbols(List<String> list) {

        List<String> result = new ArrayList<>();

        for (String it : list) {
            final Person person = fullNames.get(it);

            if (person.fullName.length() > 5) {
                result.add(it);
            }
        }

        return result;
    }

    static BiFunction<String, Person, Person> changeName = (newName, person) -> new Person(newName, person.fullName);

    static List<String> filterNamesThatFullnamesAreLongerThen5SymbolsStream(List<String> list) {
        final Stream<Person> personStream = list.stream()
            .map(name -> fullNames.get(name));

        final Predicate<Person> fullNameBiggerthen5 = it -> it.fullName.length() > 5;

        Consumer<String> print = System.out::println;
        Consumer<String> printAgaing = System.out::println;

        print
            .andThen(printAgaing)
            .accept("kalia balia")
        ;


        final Predicate<Person> nameLessThen5 = it -> it.name.length() < 5;


        final Function<String, Person> stringPersonFunction = name -> fullNames.get(name);


        return list.stream()
            .map(stringPersonFunction)
            .filter(fullNameBiggerthen5.and(nameLessThen5))
            .map(it -> it.name)
            .collect(Collectors.toList());
    }

    void fun(Map<String, Integer> map) {
//        map.entrySet()
//            .stream()
//            .map((k, v) -> v + 10)

    }

//    static Stream<String> tmp(List<List<String>> listOfList) {
//        return listOfList.stream()
//            .map(
//                list -> list.stream() 
//            )
//    }

}

class Person {
    String name;
    String fullName;

    public Person(String name, String fullName) {
        this.name = name;
        this.fullName = fullName;
    }
}
