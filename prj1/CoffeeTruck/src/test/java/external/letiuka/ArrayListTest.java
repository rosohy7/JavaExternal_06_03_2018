package external.letiuka;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import external.letiuka.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ArrayListTest extends Assert {
    private final ArrayList list = new ArrayList();

    @Before
    public void prepareList() {
        list.reset();
    }

    @Test
    public void savesValues() {
        list.add(new Integer(8));
        list.set(0, new Integer(4));
        
        list.add(new Integer(5));
        assertEquals(4, list.get(0));
        assertEquals(5, list.get(1));
    }

    @Test
    public void sortsComparableAscend() {
        list.add(new Integer(5));
        list.add(new Integer(2));
        list.add(new Integer(3));
        list.add(new Integer(1));
        list.sort();
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(5, list.get(3));
    }

    @Test
    public void returnsNullOutOfBounds() {
        list.add(new Integer(5));
        list.add(new Integer(4));
        list.reset();
        list.add(new Integer(5));
        list.set(1, new Integer(1));
        assertEquals(null, list.get(1));

    }

    @Test
    public void removesObjects() {
        list.add(new Integer(5));
        list.add(new Integer(4));
        list.remove(0);
        assertEquals(4, list.get(0));

    }

    @Test
    public void changesSize() {
        for (int i = 0; i < 130; ++i) list.add(new Integer(i));
        assertEquals(129, list.get(129));
    }
}
