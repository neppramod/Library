package pr.lib.ds.collection;

import java.util.*;

public class RandomizedQueue <Item> implements Iterable<Item>{

    private static final int INITIAL_SIZE = 10;

    private Item[] items;
    private int count = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[INITIAL_SIZE];
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
    public void enqueue(Item item) {

        if (item == null) {
            throw new IllegalArgumentException("input item is null");
        }

        items[count++] = item;

        if (count == items.length) {
            resize(items.length * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }

        Random random = new Random();

        int randomIndex = random.nextInt(count);
        Item item = items[randomIndex];
        items[randomIndex] = items[--count];
        items[count] = null;

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }

        Random random = new Random();

        int randomIndex = random.nextInt(count);
        return items[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        private Item[] iterItems;
        private int curIndex;

        public RandomIterator() {
            iterItems = (Item[]) new Object[count];
            for (int i = 0; i < count; i++) {
                iterItems[i] = items[i];
            }

            shuffleArray(iterItems);
            curIndex = 0;
        }

        private void shuffleArray(Item[] array) {
            List<Item> list = new ArrayList<>();

            for (Item i : array) {
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
        public Item next() {
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
        Item[] newItems = (Item[]) new Object[size];
        for (int i = 0; i < count; i++) {
            newItems[i] = items[i];
        }

        items = newItems;
    }
}
