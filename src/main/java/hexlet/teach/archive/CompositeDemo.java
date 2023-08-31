package hexlet.teach.archive;

/**
 * @author andreiserov
 */

import java.util.List;

/**
 *              (начальник)
 *             /           \
 *           (рабочий)   (млад нач)
         *                /   \
         *             (рабоч)   (рабоч)
 *
 */

abstract class Component {
    String name;

    abstract void addOne();
    abstract void print();
}

class Leaf extends Component {
    int number;
    String name;


    public Leaf(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public void addOne() {
        number++;
    }

    @Override
    public void print() {
        System.out.println("Leaf name is " + name + ":" + number);
    }
}

class Composite extends Component {
    public int number;
    public String name;
    List<Component> components;

    public Composite(int number, String name, List<Component> components) {
        this.number = number;
        this.name = name;
        this.components = components;
    }


    @Override
    public void addOne() {
        number++;

        for (Component component : components) {
            component.addOne();
        }

    }

    @Override
    public void print() {
        System.out.println("Composite name is " + name + ":" + number);

        for (Component component : components) {
            component.print();
        }
    }

}
/**
 *              (10)
 *             /   \
 *           (5)   (12)
 *                /   \
 *             (11)   (14)
 *
 */

public class CompositeDemo {

    public static void main(String[] args) {
        final Leaf five = new Leaf(5, "five");
        final Leaf eleven = new Leaf(11, "eleven");
        final Leaf forty = new Leaf(14, "forty");

        final Composite twelve = new Composite(12,
            "twelve", List.of(eleven, forty)
        );

        final Composite ten = new Composite(10, "ten", List.of(five, twelve));

        ten.addOne();
        ten.print();

        System.out.println("\n");

        ten.addOne();
        ten.print();

        System.out.println("\n");
        ten.addOne();
        ten.print();

        System.out.println("\n");
        ten.addOne();
        ten.print();





    }
}
