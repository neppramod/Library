package pr.lib.ds.collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    public void testQueue() {
        Queue<Integer> queue = new Queue<>();
        System.out.println("Is queue empty ? " + queue.isEmpty());
        System.out.println("Size at beginning is: " + queue.size());
        queue.enque(1);
        queue.enque(2);

        for (int item: queue) {
            System.out.printf(item + " ");
        }

        System.out.println();

        System.out.println("Size before removing is : " + queue.size());

        assertEquals(queue.size(), 2);

        queue.deque();
        queue.deque();

        assertEquals(queue.size(), 0);

        System.out.println("Size after removing all : " + queue.size());
    }

    @Test
    public void testMoreItems() {
        Queue<Integer> queue = new Queue<>();

        for (int i = 0; i < 1000; i++) {
            queue.enque(i);
        }

        for (int i = 0; i < 990; i++) {
            queue.deque();
        }

        for (Integer item: queue) {
            System.out.printf("%d, ", item);
        }

        assertEquals(queue.size(), 10);
        for (int i = 0; i < 10; i++) {
            queue.deque();
        }
        assertEquals(queue.size(), 0);
    }
}