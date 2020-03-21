package undoableList;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
You may ask why i have different class for every undo action, instead of just keeping track of list history.
The reason is that if you keep track of the list history, you have to keep too many lists in-memory (separate list for every list modification)
if we have huge dataset with many modifications, it won't be good solution. There are still couple undo actions like
undoableList.UndoClearAction, undoableList.UndoAddAllAction etc., that are very memory-expensive, because we store whole sub-lists.
 */
public class UndoableList<T> {

    private List<T> list;
    private Stack<Undoable<T>> undoActions;

    public UndoableList() {
        this.list = new LinkedList<>();
        this.undoActions = new Stack<>();
    }

    public UndoableList(List<T> list) {
        this.list = list;
        this.undoActions = new Stack<>();
    }

    public boolean remove(T value) {
        boolean isRemoved = this.list.remove(value);
        if (isRemoved) {
            Undoable<T> undoable = new UndoRemoveByValueAction<>(value);
            this.undoActions.push(undoable);
        }
        return isRemoved;
    }

    public T remove(int index) {
        T value = this.list.remove(index);
        Undoable<T> undoable = new UndoRemoveByIndexAction<>(value, index);
        this.undoActions.push(undoable);

        return value;
    }

    public void add(T value) {
        this.list.add(value);
        Undoable<T> undoable = new UndoAddValueAction<>(value);
        this.undoActions.push(undoable);
    }

    public void add(int index, T value) {
        this.list.add(index, value);
        Undoable<T> undoable = new UndoAddValueOnIndexAction<>(index);
        this.undoActions.push(undoable);
    }

    public boolean addAll(Collection<T> values) {
        boolean areAdded = this.list.addAll(values);

        if (areAdded) {
           Undoable<T> undoable = new UndoAddAllAction<>(values);
            this.undoActions.push(undoable);
        }

        return areAdded;
    }

    public boolean removeAll(Collection<T> values) {
        boolean areRemoved = this.list.removeAll(values);

        if (areRemoved) {
            Undoable<T> undoable = new UndoRemoveAllAction<>(values);
            this.undoActions.push(undoable);
        }

        return areRemoved;
    }

    public void clear() {
        Undoable<T> undoable = new UndoClearAction<>(this.list);
        this.undoActions.push(undoable);
        this.list.clear();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public int size() {
        return this.list.size();
    }

    public boolean contains(T value) {
        return this.list.contains(value);
    }

    public T get(int index) {
        return this.list.get(index);
    }

    public boolean containsAll(Collection<T> values) {
        return this.list.containsAll(values);
    }

    public void undo() throws UndoableListException {
        Undoable<T> undoable = this.undoActions.pop();
        undoable.undo(this.list);
    }
}
