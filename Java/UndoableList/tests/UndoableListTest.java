import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import undoableList.UndoableList;
import undoableList.UndoableListException;

import java.util.Arrays;
import java.util.List;

public class UndoableListTest {

    private static final String TEST_STRING = "Test String";
    private static final int RANDOM_VALUES_TO_ADD_NUMBER = 10;
    private static final int RANDOM_EXPECTED_NUMBER_TO_EXIST_IN_LIST = 6;
    private static final List<String> strings = Arrays.asList("A", "B", "C", "D");
    private static final int RANDOM_INDEX = 2;
    private static final String RANDOM_VALUE = "G";
    public static final String RANDOM_VALUE_FROM_STRINGS_LIST = "B";
    private UndoableList<String> undoableList;

    @Before
    public void setUp() throws Exception {
        this.undoableList = new UndoableList<String>();
    }

    @Test
    public void testUndoAddValueAction() throws UndoableListException {

        Assert.assertTrue(undoableList.isEmpty());

        undoableList.add(TEST_STRING);

        Assert.assertTrue(undoableList.contains(TEST_STRING));
        Assert.assertFalse(undoableList.isEmpty());

        undoableList.undo();

        Assert.assertTrue(undoableList.isEmpty());

        for (int i = 0; i < RANDOM_VALUES_TO_ADD_NUMBER; i++ ) {
            String string = Integer.toString(i);
            undoableList.add(string);
        }

        Assert.assertEquals(undoableList.size(), RANDOM_VALUES_TO_ADD_NUMBER);
        Assert.assertTrue(undoableList.contains(Integer.toString(RANDOM_EXPECTED_NUMBER_TO_EXIST_IN_LIST)));

        for (int i = 0; i < RANDOM_VALUES_TO_ADD_NUMBER; i++ ) {
            undoableList.undo();
        }

        Assert.assertTrue(undoableList.isEmpty());
    }

    @Test
    public void testUndoAddAllAction() throws UndoableListException {
        Assert.assertTrue(undoableList.isEmpty());

        undoableList.addAll(strings);

        Assert.assertEquals(undoableList.size(), strings.size());

        undoableList.undo();

        Assert.assertTrue(undoableList.isEmpty());
    }

    @Test
    public void testUndoAddValueOnIndexAction() throws UndoableListException {
        Assert.assertTrue(undoableList.isEmpty());

        undoableList.addAll(strings);
        undoableList.add(RANDOM_INDEX, RANDOM_VALUE);

        Assert.assertEquals(RANDOM_VALUE, undoableList.get(RANDOM_INDEX));
        Assert.assertEquals(undoableList.size(), strings.size() + 1);

        undoableList.undo();

        Assert.assertEquals(undoableList.size(), strings.size());
        Assert.assertFalse(undoableList.contains(RANDOM_VALUE));
        Assert.assertNotEquals(RANDOM_VALUE, undoableList.get(RANDOM_INDEX));
    }

    @Test
    public void testUndoClearAction() throws UndoableListException {
        Assert.assertTrue(undoableList.isEmpty());

        undoableList.addAll(strings);

        Assert.assertFalse(undoableList.isEmpty());
        Assert.assertEquals(undoableList.size(), strings.size());

        undoableList.clear();

        Assert.assertTrue(undoableList.isEmpty());

        undoableList.undo();

        Assert.assertFalse(undoableList.isEmpty());
        Assert.assertEquals(undoableList.size(), strings.size());
    }

    @Test
    public void testUndoRemoveAllAction() throws UndoableListException {
        Assert.assertTrue(undoableList.isEmpty());

        undoableList.addAll(strings);
        undoableList.add(RANDOM_VALUE);

        Assert.assertEquals(undoableList.size(), strings.size() + 1);

        undoableList.removeAll(strings);

        Assert.assertEquals(undoableList.size(), 1);
        Assert.assertFalse(undoableList.containsAll(strings));
        Assert.assertTrue(undoableList.contains(RANDOM_VALUE));

        undoableList.undo();

        Assert.assertEquals(undoableList.size(), strings.size() + 1);
        Assert.assertTrue(undoableList.containsAll(strings));

    }

    @Test
    public void testUndoRemoveByIndexAction() throws UndoableListException {
        Assert.assertTrue(undoableList.isEmpty());

        undoableList.addAll(strings);

        Assert.assertEquals(undoableList.get(RANDOM_INDEX), strings.get(RANDOM_INDEX));

        undoableList.remove(RANDOM_INDEX);

        Assert.assertNotEquals(undoableList.get(RANDOM_INDEX), strings.get(RANDOM_INDEX));

        undoableList.undo();

        Assert.assertEquals(undoableList.get(RANDOM_INDEX), strings.get(RANDOM_INDEX));
        Assert.assertEquals(undoableList.size(), strings.size());
    }

    @Test
    public void testUndoRemoveByValueAction() throws UndoableListException {
        Assert.assertTrue(undoableList.isEmpty());

        undoableList.addAll(strings);

        Assert.assertTrue(undoableList.contains(RANDOM_VALUE_FROM_STRINGS_LIST));

        undoableList.remove(RANDOM_VALUE_FROM_STRINGS_LIST);

        Assert.assertFalse(undoableList.contains(RANDOM_VALUE_FROM_STRINGS_LIST));

        undoableList.undo();

        Assert.assertTrue(undoableList.contains(RANDOM_VALUE_FROM_STRINGS_LIST));
    }

    @After
    public void tearDown() throws Exception {
        this.undoableList = null;
    }
}
