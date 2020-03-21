package SwtchablePriorityQueue;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/*
 * The idea is that we have three implementations of priority queue.
 * The LinkedListPriorityQueue implementation gives O(1) time complexity for adding new value and O(N) for retrieving the max value
 * The SortedLinkedListQueue implementation gives O(logN) time complexity for adding new (because of binary search method) value and O(1) for retrieving the max value
 * The PyramidPriorityQueue implementation gives O(logN) time complexity for adding new value and O(logN) for retrieving the max value
 */
public class SwitchablePriorityQueue<T> implements IPriorityQueue<T> {

    private AbstractPriorityQueueDataStructure<T> structure;

    public SwitchablePriorityQueue(Comparator<T> comparator) {
        this.structure = new PyramidPriorityQueue<>(comparator);
    }

    public void switchToPyramid() {
        if (this.structure instanceof PyramidPriorityQueue) {
            return;
        }
        this.convertToPyramid(this.structure);
    }

    public void switchToLinkedList() {
        if (this.structure instanceof LinkedListPriorityQueue) {
            return;
        }
        this.convertToLinkedList(this.structure);
    }

    public void switchToSortedLinkedList() {
        if (this.structure instanceof SortedLinkedListPriorityQueue) {
            return;
        }
        this.convertToSortedLinkedList(this.structure);
    }

    @Override
    public void enqueue(T value) {
        this.structure.enqueue(value);
    }

    @Override
    public T dequeue() {
        return this.structure.dequeue();
    }

    @Override
    public T peek() {
        return this.structure.peek();
    }

    @Override
    public boolean isEmpty() {
        return this.structure.isEmpty();
    }

    @Override
    public int size() {
        return this.structure.size();
    }

    @Override
    public void addAll(Collection<T> values) {
        this.structure.addAll(values);
    }

    @Override
    public void clear() {
        this.structure.clear();
    }

    private void convertToPyramid(AbstractPriorityQueueDataStructure<T> structure) {
        PyramidPriorityQueue<T> pyramidPriorityQueue =
                new PyramidPriorityQueue<>(this.structure.getComparator());

        pyramidPriorityQueue.convert(this.structure);

        this.structure = pyramidPriorityQueue;
    }

    private void convertToLinkedList(AbstractPriorityQueueDataStructure<T> structure) {
        LinkedListPriorityQueue<T> linkedListPriorityQueue =
                new LinkedListPriorityQueue<>(this.structure.getComparator());

        linkedListPriorityQueue.convert(this.structure);

        this.structure = linkedListPriorityQueue;
    }

    private void convertToSortedLinkedList(AbstractPriorityQueueDataStructure<T> structure) {
        SortedLinkedListPriorityQueue<T> sortedLinkedListPriorityQueue =
                new SortedLinkedListPriorityQueue<>(this.structure.getComparator());

        sortedLinkedListPriorityQueue.convert(this.structure);

        this.structure = sortedLinkedListPriorityQueue;
    }
}