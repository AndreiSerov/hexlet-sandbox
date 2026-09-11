package hexlet.teach;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author andreiserov
 */
public class Fib {

    public static long fib(long n) {
        if (n == 0) return 0;
        return (n == 1 || n == 2) ? 1 : fib(n - 2) + fib(n - 1);
    }

    public static long fibLoop(long n) {
        long n1 = 1;
        long n2 = 1;

        for (int i = 1; i < n; i++) {
            long tmp = n2;
            n2 += n1;
            n1 = tmp;
        }

        return n1;
    }

    public static Stream<BigInteger[]> fibStream(long n) {
        return Stream.iterate(new BigInteger[]{BigInteger.ZERO, BigInteger.ONE}, // the only array created in the stream
            arr -> {
                arr[1] = arr[0].add(arr[1]);      // calculating the next member of the sequence
                arr[0] = arr[1].subtract(arr[0]); // calculating the previous member of the sequence
                return arr;
            });
    }

    public static BigInteger getFibFromStream(long n) {
        return fibStream(n)
            .limit(n + 1)
            .skip(n)
            .findFirst()
            .get()[0];
    }

    public static void fibNothing(long n) {
        long n1 = 1;
        long n2 = 1;

        for (int i = 1; i < n; i++) {
            long tmp = n2;
            n2 += n1;
            n1 = tmp;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        final Method fib = Fib.class.getMethod("fib", long.class);
        profile(fib, 30);

        final Method fibLoop = Fib.class.getMethod("fibLoop", long.class);
        profile(fibLoop, 30);

        final Method fibStream = Fib.class.getMethod("fibStream", long.class);
        profile(fibStream, 30);
    }

    public static void profile(Method fun, long n) throws InvocationTargetException, IllegalAccessException {
        long start = System.nanoTime();

        for (int i = 0; i <= 1000; i++) {
            fun.invoke(null, n);
        }
        long elapsed = System.nanoTime() - start;

        System.out.println("Profile method");

        System.out.println(fun.getName() + " : " + elapsed);
    }


    @Test
    void test1() {
        assertEquals(BigInteger.valueOf(610), getFibFromStream(15));

        assertEquals(610, fibLoop(15));

        assertEquals(610, fib(15));
    }
}
