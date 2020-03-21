package SwtchablePriorityQueue;

import java.util.Collection;
import java.util.List;

public interface IPriorityQueue<T> {
    public void enqueue(T value);
    public T dequeue();
    public T peek();
    public boolean isEmpty();
    public int size();
    public void addAll(Collection<T> values);
    public void clear();
}
