package pr.lib.ds.collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {

    @Test
    public void testDeque() {
        Deque<Integer> deque = new Deque<>();
        System.out.println("Is deque empty ? " + deque.isEmpty());
        System.out.println("Size at beginning is: " + deque.size());
        deque.addFirst(1);
        deque.addLast(2);
        for (int item: deque) {
            System.out.printf(item + " ");
        }

        System.out.println();

        System.out.println("Size before removing is : " + deque.size());

        assertEquals(deque.size(), 2);

        deque.removeFirst();
        deque.removeLast();

        assertEquals(deque.size(), 0);

        System.out.println("Size after removing all : " + deque.size());
    }
}