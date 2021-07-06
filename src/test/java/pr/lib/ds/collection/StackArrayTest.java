package pr.lib.ds.collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackArrayTest {

    @Test
    public void testQueueWithTwoStacks() {
        QueueUsingStack q = new QueueUsingStack();
        q.enque(3);
        q.enque(4);
        q.enque(6);
        q.enque(7);
        assertEquals(q.deque(), 3);
        q.enque(8);
        q.enque(9);
        assertEquals(q.deque(), 4);
        assertEquals(q.deque(), 6);
        assertEquals(q.deque(), 7);
        assertEquals(q.deque(), 8);
        assertEquals(q.deque(), 9);
    }

    @Test
    public void iterateStackArray() {
        StackArray<Character> charStack = new StackArray<>();
        String s = "apple";
        for (char c : s.toCharArray()) {
            charStack.push(c);
        }

        for (char c : charStack) {
            System.out.print(c + ", ");
        }
        System.out.println();
    }

    static class QueueUsingStack {
        private StackArray<Integer> enqueStack = new StackArray<>();
        private StackArray<Integer> dequeStack = new StackArray<>();

        public void enque(int num) {
            enqueStack.push(num);
        }

        public int deque() {
            if (isEmpty()) {
                throw new UnsupportedOperationException("Queue is empty");
            }

            if (!dequeStack.isEmpty()) {
                return dequeStack.pop();
            } else {
                while (!enqueStack.isEmpty()) {
                    dequeStack.push(enqueStack.pop());
                }
                return dequeStack.pop();
            }
        }

        public boolean isEmpty() {
            return enqueStack.isEmpty() && dequeStack.isEmpty();
        }
    }
}