class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        final ListNode head4 = new ListNode(4, null);
        final ListNode head3 = new ListNode(3, head4);
        final ListNode head2 = new ListNode(2, head3);

        head4.next = head2;

        final ListNode head1 = new ListNode(1, head2);
//        final ListNode result = reverseList(head1);

        final boolean result = hasCycle(head1);




        System.out.println("stop here");
        // 1   ->   2 ->   3
        //          |      |
        //             --


    }

    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }
        return false;
    }



    // [1, 2, 3, 4] -> [4, 3, 2, 1]
    public static ListNode reverseList(ListNode head) {
        ListNode newHead = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }

        return newHead;
    }
}

class ListNode{
    Integer val;
    ListNode next;

    public ListNode(Integer val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}