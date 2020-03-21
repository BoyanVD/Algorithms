package undoableList;

import java.util.List;

class UndoAddValueAction<T> implements Undoable<T> {

    private T value;

    UndoAddValueAction(T value) {
        this.value = value;
    }

    @Override
    public void undo(List<T> list) throws UndoableListException {

        if (!list.contains(this.value)) {
           throw new UndoableListException("There is no such value to remove on undo action !");
        }
        list.remove(this.value);
    }
}
