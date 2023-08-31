package hexlet.teach.stream;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author andreiserov
 */
public class Lambdas {
    public static String[][] enlargeArrayImage(String[][] image) {

        String[][] horizontalyStretched = Arrays.stream(image)
            .map(items -> Arrays.stream(items)
                .flatMap(item -> Stream.of(item, item))
                .toArray(String[]::new)
            )
            .toArray(String[][]::new);


        return Arrays.stream(horizontalyStretched)
            .flatMap(item -> Arrays.stream(new String[][]{item, item}))
            .toArray(String[][]::new);
    }
    public static <elem> void main(String[] args) {
        String[][] image1 = {
            {"*", "*", "*", "*"},
            {"*", " ", " ", "*"},
            {"*", " ", " ", "*"},
            {"*", "*", "*", "*"},
        };

        final String[][] func = enlargeArrayImage(image1);
        final String[][] expr = Arrays.stream(image1)
            .map(items ->
                Arrays.stream(items)
                    .flatMap(item -> Arrays.stream(new String[]{item, item}))
                    .toArray(String[]::new)
            )
            .toArray(String[][]::new);
        final boolean b = func == expr;


//        Arrays.stream(image1)
//            .map(it -> {
//                final int doubleSize = it.length * 2;
//                final String[] strings = new String[doubleSize];
//
//            })




        String[][] bigImage1 = Arrays.stream(image1)
            .flatMap(x -> Arrays.stream(x)
                    .map(y -> y + ":" + y)
                    .map(z -> z.split(":"))
//                .flatMap(w -> Arrays.stream(w))
            )
            .toArray(String[][]::new);


        final Stream<String[]> stream1 = Arrays.stream(image1)
            .map(x -> Arrays.stream(x)
                    .map(y -> y + ":" + y)
                    .map(z -> z.split(":"))
                    .toArray(String[][]::new)
//                .flatMap(w -> Stream.of(w, w))
            )
            .flatMap(Arrays::stream);

//        Arrays.stream(image1)
//            .map(x -> Arrays.stream(new String[][] {x, x})
//                .flatMap(w -> Stream.of(w, w))
//                .toArray(String[][]::new)
//            )
//            .toArray(String[][]::new);

        System.out.println(Arrays.deepToString(bigImage1));

        String[] strings = {"1", "2", "3", "11"};

//        Arrays.stream(strings)
//            .collect(
//                ArrayList::new, List::add,
//                (left, right) -> { left.addAll(right); return left; },
//                CH_ID
//            );

//CH_ID
//        final String[][] strings = Arrays.stream(image1)
//            .flatMap((String[] x) -> Stream.of(x, x)
//                .map(items -> Arrays.stream(items)
//                    .flatMap(item -> Stream.of(item, item)
//                ))
//
//                .toArray(String[]::new)
//            )
//            .toArray(String[][]::new);

        System.out.println(Arrays.deepToString(bigImage1));
    }
}
