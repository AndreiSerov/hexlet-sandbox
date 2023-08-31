package hexlet.teach.archive;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

/**
 * @author andreiserov
 */

public class Tree {
    Node root;

    public void add(int value) {
        root = addRecursive(root, value);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }

    public void traverseInOrderWithoutRecursion() {
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            Node top = stack.pop();
            System.out.print(" " + top.value);
            current = top.right;
        }
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
            ? containsNodeRecursive(current.left, value)
            : containsNodeRecursive(current.right, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            // Case 1: no children
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: only 1 child
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            // Case 3: 2 children
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            right = null;
            left = null;
        }
    }
}


class TreeDemo {
    public static void main(String[] args) {
        final Tree balanceTree = new Tree();
        balanceTree.add(10);
        balanceTree.add(8);
        balanceTree.add(7);
        balanceTree.add(9);

        balanceTree.add(12);
        balanceTree.add(11);
        balanceTree.add(13);

        balanceTree.traverseInOrder(balanceTree.root);
        balanceTree.traverseInOrderWithoutRecursion();

        /**
         *                 10
         *              /       \
         *             8         12
         *          /    \      /  \
         *        7       9 |  11   13
         *
         */

        final Tree unbalancedTree = new Tree();
        unbalancedTree.add(12);
        unbalancedTree.add(11);
        unbalancedTree.add(13);
        unbalancedTree.add(7);
        unbalancedTree.add(9);
        unbalancedTree.add(10);
        unbalancedTree.add(8);

        /**
         *                 12
         *              /       \
         *             11         13
         *          /
         *        7
         *         \
         *          9
         *        /  \
         *       8   10
         */
        unbalancedTree.traverseInOrderWithoutRecursion();




        System.out.println(unbalancedTree);
    }
}

class ComparatorDemo {

    // провалиться в классы

    public static void main(String[] args) {
        final List<Pupil> pupils = List.of(
            new Pupil("Andre", "Serov", 10),
            new Pupil("Vasya", "Pupkin", 10),
            new Pupil("Masha", "Vasilieva", 8)
        );

        final TreeSet<Pupil> engSchoolOrder = new TreeSet<>(Comparator.comparing(pupil -> pupil.getFirstName()));
        final TreeSet<Pupil> ruSchoolOrder = new TreeSet<>(Comparator.comparing(Pupil::getLastName));
        final TreeSet<Pupil> gradeOrder = new TreeSet<>(Comparator.comparing(Pupil::getGrade).reversed());

        engSchoolOrder.addAll(pupils);
        ruSchoolOrder.addAll(pupils);
        gradeOrder.addAll(pupils);



        ruSchoolOrder.addAll(pupils);

        System.out.println("kalia balia");
    }

}

class Pupil {
    String firstName;
    String lastName;

    Integer grade;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getGrade() {
        return grade;
    }

    public Pupil(String firstName, String lastName, Integer grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }

    public Pupil(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

