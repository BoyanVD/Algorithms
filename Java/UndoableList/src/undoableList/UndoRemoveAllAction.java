package undoableList;

import java.util.Collection;
import java.util.List;

class UndoRemoveAllAction<T> implements Undoable<T> {

    private Collection<T> removedValues;

    UndoRemoveAllAction(Collection<T> values) {
        this.removedValues = values;
    }

    @Override
    public void undo(List<T> list) throws UndoableListException {
        if (list.containsAll(removedValues)) {
            throw new UndoableListException("List contains removed values to be restored !");
        }
        list.addAll(removedValues);
    }
}
