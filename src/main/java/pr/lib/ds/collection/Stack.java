package pr.lib.ds.collection;

import java.util.Iterator;


/**
 * O(1) access and add to top element. Good for real time need for add and remove
 * Consumes more memory than array implementation, as internal Node class requires more memory per node.
 * @param <T>
 */

public class Stack<T> implements Iterable<T> {
    private Node top = null;

    @Override
    public Iterator<T> iterator() {
       return new ListIterator<T>();
    }

    private class ListIterator<T> implements Iterator<T> {
        private Node current = top;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = (T) current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is not supported");
        }
    }

    private static class Node<T> {
        T item;
        Node next;

        public Node(T item) {
            this.item = item;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T item) {
        Node oldTop = top;
        top = new Node(item);
        top.next = oldTop;
    }

    public T pop() {
        T item = (T)top.item;
        top = top.next;
        return item;
    }
}
