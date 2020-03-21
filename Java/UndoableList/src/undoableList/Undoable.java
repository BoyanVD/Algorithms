package undoableList;

import java.util.List;

interface Undoable<T> {
    public void undo(List<T> list) throws UndoableListException;
}

//Must create the following delete actions :
//boolean remove(Object var1);Done

//boolean addAll(Collection<? extends E> var1); Done

//boolean removeAll(Collection<?> var1); Done

//default void replaceAll


//default void sort(Comparator<? super E> c)


//void clear(); Done

//    boolean add(E var1); Done

//    boolean remove(Object var1); Done

//    void add(int var1, E var2); Done

//    remove by index Done