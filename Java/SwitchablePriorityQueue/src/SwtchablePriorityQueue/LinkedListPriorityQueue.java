package SwtchablePriorityQueue;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListPriorityQueue<T> extends AbstractPriorityQueueDataStructure<T> {

    LinkedListPriorityQueue(Comparator<T> comparator) {
        super(new LinkedList<T>(), comparator);
    }

    @Override
    public void enqueue(T value) {
        List<T> list = this.getList();
        list.add(value);
    }

    @Override
    public T dequeue() {
        List<T> list = this.getList();
        T maxValue = this.findMaxValue();

        list.remove(maxValue);

        return maxValue;
    }

    @Override
    public T peek() {
        return this.findMaxValue();
    }

    private T findMaxValue() {
        Comparator<T> comparator = this.getComparator();
        List<T> list = this.getList();
        if (list.isEmpty()) {
            throw new EmptyQueueException("Queue is empty !");
        }

        Iterator<T> iterator = list.iterator();
        T maxValue = iterator.next();

        while (iterator.hasNext()) {
            T value = iterator.next();
            if (comparator.compare(maxValue, value) < 0) {
                maxValue = value;
            }
        }
        return maxValue;
    }
}
