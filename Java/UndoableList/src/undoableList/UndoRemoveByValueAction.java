package undoableList;

import java.util.List;

class UndoRemoveByValueAction<T> implements Undoable<T> {

    private T value;

    UndoRemoveByValueAction(T value) {
        this.value = value;
    }

    @Override
    public void undo(List<T> list) throws UndoableListException {
        if (list.contains(value)) {
            throw new UndoableListException("Value already in list, so can't add it in undo action !");
        }
        list.add(value);
    }
}
