# Library
Java Library for various algorithms and datastructures

- Create various fundamental datastructures like [**Queue**](src/main/java/pr/lib/ds/collection/Queue.java), [**Stack**](src/main/java/pr/lib/ds/collection/Stack.java) (Array and Linked List Implementation). Create some other variations like [**Deque**](src/main/java/pr/lib/ds/collection/Dequeue.java) and [**RandomizedQueue**](src/main/java/pr/lib/ds/collection/RandomizedQueue.java). Array implementation([QueueArray](src/main/java/pr/lib/ds/collection/QueueArray.java), [StackArray](src/main/java/pr/lib/ds/collection/StackArray.java)) takes less memory and can be fast for most use cases, however most of the array implementation use resizing with 75% full 25% empty rule. This can create slowdown during resizing. Therefore, LinkedList versions is more suited, where add/remove mandates strict O(1), but with the cost of little more memory.

- **UnionFind**. This implementation externalizes the logic for merge using interface. Details can be filled at usage and based on use case.  UnionFind is useful in accumulating or progressing use cases, where elements have to be added and processed at the same time.

- **SegmentTree**, **FenwickTree (BIT)**: These are range query data structures. However, this implementation fits with add value to the range rather than changing old values with new one. So be careful with its usage. Here we have two implementations of SegmentTree (traditional iterative) and recursive.
