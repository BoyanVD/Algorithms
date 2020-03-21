package undoableList;

import java.util.List;

class UndoRemoveByIndexAction<T> implements Undoable<T> {

    private T value;
    private int index;

    UndoRemoveByIndexAction(T value, int index) {
        this.value = value;
        this.index = index;
    }

    @Override
    public void undo(List<T> list) throws UndoableListException {
        list.add(this.index, this.value);
    }
}
