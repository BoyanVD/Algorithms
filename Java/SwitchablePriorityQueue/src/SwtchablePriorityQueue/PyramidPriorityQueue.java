package SwtchablePriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PyramidPriorityQueue<T> extends AbstractPriorityQueueDataStructure<T> {

    PyramidPriorityQueue(Comparator<T> comparator) {
        super(new ArrayList<>(), comparator);
    }

    @Override
    public void enqueue(T value) {
        List<T> list = this.getList();
        list.add(value);
        Comparator<T> comparator = this.getComparator();
        this.swim(list.size() - 1, list, comparator);
    }

    @Override
    public T dequeue() {
        List<T> list = this.getList();
        if (list.isEmpty()) {
            throw new EmptyQueueException("Queue is empty !");
        }
        if (list.size() == 1) {
            return list.remove(0);
        }

        T result = list.get(0);
        list.set(0, list.get(list.size() - 1));
        this.sink(0, list, this.getComparator());
        list.remove(result);

        return result;
    }

    @Override
    public T peek() {
        List<T> list = this.getList();
        if (list.isEmpty()) {
            throw new EmptyQueueException("Queue is empty !");
        }

        return list.get(list.size() - 1);
    }

    private void swim(int index, List<T> list, Comparator<T> comparator) {
        if (index == 0) {
            return;
        }
        int parentIndex = (index - 1)/2 ;
        int result = comparator.compare(list.get(index), list.get(parentIndex));

        if(result <= 0) {
            return;
        }

        this.swap(index, parentIndex);
        swim(index, list, comparator);
    }

    private void sink(int index, List<T> list, Comparator<T> comparator) {
        int leftChildIndex = 2*index + 1;
        int rightChildIndex = 2*index + 2;

        if (leftChildIndex >= list.size() || rightChildIndex >= list.size()) {
            return;
        }

        int childsComparisonResult = comparator.compare(list.get(leftChildIndex), list.get(rightChildIndex));
        int largestChildIndex = leftChildIndex;

        if(childsComparisonResult < 0) {
            largestChildIndex = rightChildIndex;
        }

        if (comparator.compare(list.get(index), list.get(largestChildIndex)) < 0) {
            this.swap(index, largestChildIndex);
            sink(largestChildIndex, list, comparator);
        }
    }

    private void swap(int firstIndex, int secondIndex) {
        List<T> list = this.getList();
        T value = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, value);

    }
}
