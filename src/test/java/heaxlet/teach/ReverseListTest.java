package heaxlet.teach;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author andreiserov
 */
public class ReverseListTest {

    @Test
    void test1() {

        final ListNode node = new ListNode(10);
        node.add(13);
        node.add(23);

        ListNode list = new ListNode(
            1,
            new ListNode(
                2,
                new ListNode(
                    3,
                    new ListNode(
                        4,
                        new ListNode(
                            5
                        )
                    )
                )
            )
        );

        final ListNode actual = Solution.reverseList(list);
        final ListNode expected = new ListNode(
            5,
            new ListNode(
                4,
                new ListNode(
                    3,
                    new ListNode(
                        2,
                        new ListNode(
                            1
                        )
                    )
                )
            )
        );

        assertEquals(expected, actual);
    }


    private static void assertEquals(ListNode l1, ListNode l2) {

        ListNode node1 = l1;
        ListNode node2 = l2;

        while(node1 != null && node2 != null) {
            if (node1.val != node2.val) fail(l1, l2);

            node1 = node1.next;
            node2 = node2.next;
        }

        if (!(node1 == null && node2 == null)) fail(l1, l2);
    }

    static void fail(ListNode expected, ListNode actual) {
        throw new AssertionFailedError(format("Not equal %s, %s", expected.toString(), actual.toString()), expected.toString(), actual.toString());
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }


    public void add(int value) {
        ListNode cur = this;

        while (cur != null) {
            if (cur.next == null) cur = new ListNode(value);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.val);
        ListNode cur = this;

        while (cur != null) {
            sb.append(cur.val);
            cur = cur.next;
        }

        return sb.toString();
    }
}

class Solution {
    public static ListNode reverseListIter(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            head.next = prev;
            ListNode next = head.next;
            prev = head;
            head = next;
        }
        return prev;
    }


    public static ListNode reverseList(ListNode head) {
        return reverseList(head, null);
    }

    public static ListNode reverseList(ListNode head, ListNode prev) {
        if (head == null) return prev;
        ListNode next = head.next;
        head.next = prev;
        return reverseList(next, head);
    }
}

class AppTest {


    public static boolean scrabble3(String symbols, String word) {




        char[] charArray = symbols.toLowerCase().toCharArray();
        Arrays.sort(charArray);

        var balia = Arrays.stream(word.toLowerCase().split("")).sorted().toArray(String[]::new);
        var kalia = Arrays.stream(symbols.toLowerCase().split("")).sorted().toArray(String[]::new);
        final String join = String.join("", kalia);
        final String joi2n = String.join("", balia);


        return (
            join.length() > joi2n.length()) ?  join.contains(joi2n) : joi2n.contains(join);
    }

    private record Pair<T1, T2>(T1 t1, T2 t2) { }

    public static boolean scrabble(String symbols, String word) {
        List<String> coll = new ArrayList<>(Arrays.stream(symbols.split("")).toList());
        return !Arrays.stream(word.toLowerCase().split(""))
            .map(
                cur -> {
                    if (!coll.contains(cur)) return new Pair<String, String>("left", null);
                    coll.remove(cur);
                    return new Pair<String, String>(null, "right");
                }
            )
            .map(it -> it.t1)
            .anyMatch(Objects::nonNull);
    }


    @Test
    void testSrabble1() throws Exception {
        boolean result = scrabble("rkqodlw", "woRld");
        assertThat(result).isTrue();
    }

    @Test
    void testSrabble2() throws Exception {
        boolean result = scrabble("begsdhhtsexoult", "Hexlet");
        assertThat(result).isTrue();
    }

    @Test
    void testSrabble3() throws Exception {
        boolean result = scrabble("thlxertwq", "hexlet");
        assertThat(result).isFalse();
    }

    @Test
    void testSrabble4() throws Exception {
        boolean result = scrabble("jvayu", "java");
        assertThat(result).isFalse();
    }

    @Test
    void testSrabble5() throws Exception {
        boolean result = scrabble("", "java");
        assertThat(result).isFalse();
    }
}

