package pr.lib.ds.collection;

import java.util.Iterator;

/**
 * Array based implementation of stack
 * Consumes less memory than Linked List version.
 * However, resizing may take significant time (may not be applicable for real time usage)
 * Overall performance is fast
 * @param <T>
 */
public class StackArray <T> implements Iterable<T> {
    private T[] s;
    private int top = 0;
    private static final int INITIAL_SIZE = 10;

    public StackArray() {
        s = (T[]) new Object[INITIAL_SIZE];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public void push(T item) {
        if (top == s.length) {
            resize(s.length * 2);
        }
        s[top++] = item;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NullPointerException("Stack is empty");
        }
        T item = s[--top];
        s[top] = null;

        if (top > INITIAL_SIZE && top == s.length / 4) {
            resize(s.length / 2);
        }

        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NullPointerException("Stack is empty");
        }

        return s[top-1];
    }

    private void resize(int size) {
        T[] copy = (T[]) new Object[size];
        for (int i = 0; i < top; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<T>();
    }

    // Iterate in reverse order
    private class ListIterator<T> implements Iterator<T> {
        private int current = top;

        @Override
        public boolean hasNext() {
            return current > 0;
        }

        @Override
        public T next() {
            return (T) s[--current];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is not supported");
        }
    }
}
