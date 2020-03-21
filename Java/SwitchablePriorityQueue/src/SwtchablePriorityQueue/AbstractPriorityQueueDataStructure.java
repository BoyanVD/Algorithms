package SwtchablePriorityQueue;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractPriorityQueueDataStructure<T> implements IPriorityQueue<T> {

    private List<T> list;
    private Comparator<T> comparator;

    AbstractPriorityQueueDataStructure(List<T> list, Comparator<T> comparator) {
        if (list == null) {
            throw new SwitchablePriorityQueueException("List can't be null !");
        }

        if (comparator == null) {
            throw new SwitchablePriorityQueueException("Comparator can't be null !");
        }

        this.list = list;
        this.comparator = comparator;
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    @Override
    public void addAll(Collection<T> values) {
        for (T value : values) {
            this.enqueue(value);
        }
    }

    List<T> getList() {
        return this.list;
    }

    Comparator<T> getComparator() {
        return this.comparator;
    }

    void convert(AbstractPriorityQueueDataStructure<T> structure) {
        List<T> listToGetFrom = structure.getList();

        for (T value : listToGetFrom) {
            this.enqueue(value);
        }
    }
}
