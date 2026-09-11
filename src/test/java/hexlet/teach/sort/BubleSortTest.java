package hexlet.teach.sort;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author andreiserov
 *
 * Попробуем продебажить выводом в консоль
 * Точки останова
 * Остановка по условию
 * Остановка на ошибке
 * Изменение значения
 * Вычисление во время дебага
 */
public class BubleSortTest {

    int[] expectedArr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @Test
    @Disabled("Учебный пример для дебага — падает намеренно")
    void commonDebug() {
        final int[] ints = {1, 2, 1000, 9, 8, 7, 6, 5, 4, 3,};

        new BubleSort().method();
        BubleSort.sort(ints);
        assertArrayEquals(expectedArr, ints);
    }

    @Test
    @Disabled("Учебный пример для дебага — падает намеренно")
    void commonCondition() throws IOException {
        int[] ints = toIntArray(readFile("ints.txt").split(" "));

        BubleSort.sort(ints);
        assertArrayEquals(expectedArr, ints);
    }

    @Test
    @Disabled("Учебный пример для дебага — падает намеренно")
    void changeValue() throws IOException {
        int[] ints = toIntArray(readFile("mistake_ints.txt").split(" "));

        BubleSort.sort(ints);
        assertArrayEquals(expectedArr, ints);
    }

    private int[] toIntArray(String[] s) {
        var res = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            res[i] = Integer.parseInt(s[i]);
        }
        return res;
    }

    private String readFile(String filepath) throws IOException {
        return Files.readString(Path.of("./src/test/resources/" + filepath));
    }
}

class BubleSort {

    public void method() {
        System.out.println("method called");
    }


    static int temp;
    public static void sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (arr[j] < arr[j - 1]) {
                    if (temp > 123) throw new OurCustomException("We can't work with numbers bigger then 123");

                    temp = arr[j];

                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;

                    temp += 1000;
                }
            }
        }

    }
}

class OurCustomException extends RuntimeException {
    public OurCustomException(String message) {
        super(message);
    }
}











//    int temp;
//
//        for(int i=0; i < arr.length; i++){
//    for(int j=1; j < (arr.length - i); j++){
//
//    if (arr[j] > 123) throw new RuntimeException("We can't work with numbers bigger then 123");
//
//    if(arr[j-1] > arr[j]){
//    temp = arr[j-1];
//    arr[j-1] = arr[j];
//    arr[j] = temp;
//    }
//    }
//    }