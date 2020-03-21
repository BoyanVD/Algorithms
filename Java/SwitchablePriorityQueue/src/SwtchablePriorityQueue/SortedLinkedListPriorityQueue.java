package SwtchablePriorityQueue;

import java.util.*;

public class SortedLinkedListPriorityQueue<T> extends AbstractPriorityQueueDataStructure<T> {

    SortedLinkedListPriorityQueue(Comparator<T> comparator) {
        super(new LinkedList<T>(), comparator);
    }

    @Override
    public void enqueue(T value) {
        List<T> list = this.getList();
        int index = this.findPlaceForValue(value);
        list.add(index, value);
    }

    @Override
    public T dequeue() {
        List<T> list = this.getList();
        if (list.isEmpty()) {
            throw new EmptyQueueException("Queue is empty !");
        }
        return list.remove(list.size() - 1);
    }

    @Override
    public T peek() {
        List<T> list = this.getList();
        if (list.isEmpty()) {
            throw new EmptyQueueException("Queue is empty !");
        }
        return list.get(list.size() - 1);
    }

    //could be optimized with binary search
    private int findPlaceForValue(T value) {
        List<T> list = this.getList();
        Comparator<T> comparator = this.getComparator();
        for (int index = 0; index < list.size(); index++) {
            if(comparator.compare(value, list.get(index)) >= 0) {
                return index + 1;
            }
        }
        return list.size();
    }
}
