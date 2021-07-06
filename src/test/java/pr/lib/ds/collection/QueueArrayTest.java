package pr.lib.ds.collection;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class QueueArrayTest {
    @Test
    public void testQueue() {
        QueueArray<Integer> queue = new QueueArray<>();
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
    // At the time of this writing there is no support for PowerMockito with JUnit5
    // Therefore only doing resizing
    // You can use debug to test if resize method was called
    public void testResizing() {
        QueueArray<Integer> queue = new QueueArray<>();
        for (int i = 0; i < 30; i++) {
            queue.enque(i);
        }

        assertEquals(queue.size(), 30);
        for (int i = 0; i < 20; i++) {
            queue.deque();
        }

        System.out.println("Items: ");
        for (Integer item : queue) {
            System.out.printf("%d, ", item);
        }
        System.out.println();

        assertEquals(queue.size(), 10);
    }
}