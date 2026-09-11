import java.util.Collection;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * @author andreiserov
 */
public class Reduce {
    private Stream<Point> p1;

    record Point(int x, int y) {}
    record Vector(Point p1, Point p2) {}
    record Rectangle(double a, double b) {
        double area() {
            return a * b;
        }
    }

    public static double getRectangleArea(Collection<Rectangle> rectangles) {
        double initialValue = 0;
        return rectangles.stream()
            .reduce(
                initialValue,
                (acc, it) -> it.area() + acc,
                (acc, it) -> .0
            );
    }

    public static void main(String[] args) {
        final List<Rectangle> rectangles = List.of(
            new Rectangle(10, 10),
            new Rectangle(11, 150),
            new Rectangle(12, 101),
            new Rectangle(13, 106),
            new Rectangle(1023, 180)
        );
        System.out.println(getRectangleArea(rectangles));
    }
}
