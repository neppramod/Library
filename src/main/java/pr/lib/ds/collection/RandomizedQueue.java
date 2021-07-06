package pr.lib.ds.collection;

import java.util.*;

public class RandomizedQueue <T> implements Iterable<T>{

    private static final int INITIAL_SIZE = 10;

    private T[] items;
    private int count = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (T[]) new Object[INITIAL_SIZE];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(T item) {

        if (item == null) {
            throw new IllegalArgumentException("input item is null");
        }

        items[count++] = item;

        if (count == items.length) {
            resize(items.length * 2);
        }
    }

    // remove and return a random item
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }

        Random random = new Random();

        int randomIndex = random.nextInt(count);
        T item = items[randomIndex];
        items[randomIndex] = items[--count];
        items[count] = null;

        return item;
    }

    // return a random item (but do not remove it)
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }

        Random random = new Random();

        int randomIndex = random.nextInt(count);
        return items[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<T> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<T> {

        private T[] iterItems;
        private int curIndex;

        public RandomIterator() {
            iterItems = (T[]) new Object[count];
            for (int i = 0; i < count; i++) {
                iterItems[i] = items[i];
            }

            shuffleArray(iterItems);
            curIndex = 0;
        }

        private void shuffleArray(T[] array) {
            List<T> list = new ArrayList<>();

            for (T i : array) {
                list.add(i);
            }

            Collections.shuffle(list);

            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);
            }
        }

        @Override
        public boolean hasNext() {
            return curIndex < count;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more element to iterate");
            }
            return iterItems[curIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is not implemented");
        }
    }


    private void resize(int size) {
        T[] newItems = (T[]) new Object[size];
        for (int i = 0; i < count; i++) {
            newItems[i] = items[i];
        }

        items = newItems;
    }
}
