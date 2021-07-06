package pr.lib.ds.collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {

    @Test
    public void testRandomizedQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        System.out.println("Is randomizedQueue empty ? " + randomizedQueue.isEmpty());
        System.out.println("Size at beginning is: " + randomizedQueue.size());
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        for (int item: randomizedQueue) {
            System.out.printf(item + " ");
        }

        System.out.println();

        System.out.println("Current Item: " + randomizedQueue.peek());

        System.out.println("Size before removing is : " + randomizedQueue.size());

        assertEquals(randomizedQueue.size(), 2);

        randomizedQueue.dequeue();
        randomizedQueue.dequeue();

        assertEquals(randomizedQueue.size(), 0);

        System.out.println("Size after removing all : " + randomizedQueue.size());
    }
}