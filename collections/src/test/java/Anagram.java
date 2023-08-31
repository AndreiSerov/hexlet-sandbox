import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author andreiserov
 */
class Anagram {

    //входные слова -> "нос", "зов", "сон", "атлас", "салат", "воз", "марка", "рамка", "лапша", "масло", "смола", "макар"
    //вывести результат -> [[нос, сон], [марка, рамка, макар], [атлас, салат], [зов, воз], [масло, смола], [лапша]]

    @Test
    void groupByAnagram() {

        final List<String> words =
                List.of("нос", "зов", "сон", "атлас", "салат", "воз", "марка", "рамка", "лапша", "масло", "смола", "макар");

        Collection<List<String>> expected = Set.of(
                List.of("нос", "сон"),
                List.of("марка", "рамка", "макар"),
                List.of("атлас", "салат"),
                List.of("зов", "воз"),
                List.of("масло", "смола"),
                List.of("лапша")
        );

        Collection<List<String>> actual = Task1.groupByAnagramMapStream(words);

        System.out.println(actual);
        assertTrue(expected.containsAll(actual));
    }
}


class Task1 {

    /*

        public static void main(String[] args) {
        //входные слова -> "нос", "зов", "сон", "атлас", "салат", "воз", "марка", "рамка", "лапша", "масло", "смола", "макар"
        //вывести результат -> [[нос, сон], [марка, рамка, макар], [атлас, салат], [зов, воз], [масло, смола], [лапша]]
        final var words = groupByAnagram;//
        System.out.println(groupByAnagram(words));

     */

    public static Collection<List<String>> groupByAnagramMapStream(List<String> input) {
        return input
                .stream()
                .collect(Collectors.toMap(
                        it -> {
                            char[] chars = it.toCharArray();
                            Arrays.sort(chars);
                            return new String(chars);
                        },
                        it -> {
                            final List<String> value = new ArrayList<>();
                            value.add(it);
                            return value;
                        },
                        (exist, replace) -> {
                            exist.addAll(replace);
                            return exist;
                        }
                ))
                .values();

    }
}


