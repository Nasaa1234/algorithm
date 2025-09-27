

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



public class MyChainTest {

    @Test
    public void testToArray() {
        MyChain c1 = new MyChain();
        c1.add(0, "A");
        c1.add(1, "B");

        Object[] arr = c1.toArray();

        assertArrayEquals(new Object[]{"A", "B"}, arr);
    }

    @Test
    public void testAddRange() {
        MyChain c1 = new MyChain();
        c1.addRange(new Object[]{"X", "Y", "Z"});

        assertArrayEquals(new Object[]{"X", "Y", "Z"}, c1.toArray());
    }

    @Test
    public void testUnion() {
        MyChain c1 = new MyChain();
        c1.add(0, "A");
        c1.add(1, "B");

        MyChain c2 = new MyChain();
        c2.add(0, "C");
        c2.add(1, "D");

        MyChain union = c1.union(c2);

        assertArrayEquals(new Object[]{"A", "B", "C", "D"}, union.toArray());
    }

    @Test
    public void testIntersection() {
        MyChain c1 = new MyChain();
        c1.add(0, "A");
        c1.add(1, "B");
        c1.add(2, "C");

        MyChain c2 = new MyChain();
        c2.add(0, "B");
        c2.add(1, "C");
        c2.add(2, "D");

        MyChain inter = c1.intersection(c2);

        assertArrayEquals(new Object[]{"B", "C"}, inter.toArray());
    }

    @Test
    public void testEmptyIntersection() {
        MyChain c1 = new MyChain();
        c1.addRange(new Object[]{"A", "B"});

        MyChain c2 = new MyChain();
        c2.addRange(new Object[]{"X", "Y"});

        MyChain inter = c1.intersection(c2);

        assertEquals(0, inter.size()); // no common elements
    }
}