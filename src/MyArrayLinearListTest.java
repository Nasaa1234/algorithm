import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.util.ArrayList;

class MyArrayLinearListTest {

    MyArrayLinearList list;

    @BeforeEach
    void setUp() {
        list = new MyArrayLinearList();
        list.add(0, 10);
        list.add(1, 5);
        list.add(2, 8);
        list.add(3, 3);
        list.add(4, 12);
    }

    @Test
    void testMax() {
        assertEquals(12, list.max());
    }

    @Test
    void testMin() {
        assertEquals(3, list.min());
    }

    @Test
    void testSum() {
        assertEquals(38, list.sum());
    }

    @Test
    void testAverage() {
        assertEquals(7.6f, list.average());
    }

    @Test
    void testRemoveOdd() {
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(10);
        expected.add(8);
        expected.add(12);
        assertEquals(expected, list.removeOdd());
    }

    @Test
    void testSort() {
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(5);
        expected.add(8);
        expected.add(10);
        expected.add(12);
        assertEquals(expected, list.sort());
    }

}