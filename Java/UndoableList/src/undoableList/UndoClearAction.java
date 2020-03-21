package undoableList;

import java.util.LinkedList;
import java.util.List;

class UndoClearAction<T> implements Undoable<T> {

    private List<T> listToRestore;

    UndoClearAction(List<T> list) {
        this.listToRestore = new LinkedList<>(list);
    }

    @Override
    public void undo(List<T> list) throws UndoableListException {
        if (!list.isEmpty()) {
            throw new UndoableListException("List must be empty on clear undo action !");
        }
        list.addAll(listToRestore);
    }
}
