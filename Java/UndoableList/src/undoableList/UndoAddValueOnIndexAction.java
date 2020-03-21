package undoableList;

import java.util.List;

class UndoAddValueOnIndexAction<T> implements Undoable<T> {
    private int index;

    UndoAddValueOnIndexAction(int index) {
        this.index = index;
    }

    @Override
    public void undo(List<T> list) throws UndoableListException {
        if (list.size() <= this.index) {
            throw new UndoableListException("There is no such index !");
        }
        list.remove(this.index);
    }
}
