package undoableList;

import java.util.Collection;
import java.util.List;

class UndoAddAllAction<T> implements Undoable<T> {

    private Collection<T> addedValues;

    UndoAddAllAction(Collection<T> list) {
        this.addedValues = list;
    }

    @Override
    public void undo(List<T> list) throws UndoableListException {
        if (!list.containsAll(addedValues)) {
            throw new UndoableListException("There are no such values to remove on undo action");
        }
        list.removeAll(addedValues);
    }
}
