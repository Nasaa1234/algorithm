package lab.tests;

import lab.functions.MyStack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    @Test
    void emptyAndPushPop() {
        MyStack stack = new MyStack(3);
        assertTrue(stack.empty(), "Хоосон байх естой");

        stack.push(10);
        assertFalse(stack.empty(), "Хоосон байх есгүй");

        Object popped = stack.pop();
        assertEquals(10, popped, "10 буцаана");
        assertTrue(stack.empty(), "Хоосон байх естой");
    }

    @Test
    void isFull() {
        MyStack stack = new MyStack(2);
        assertFalse(stack.isFull());

        stack.push(1);
        stack.push(2);
        assertTrue(stack.isFull());
    }

    @Test
    void size() {
        MyStack stack = new MyStack(5);
        assertEquals(0, stack.size());

        stack.push("A");
        stack.push("B");
        assertEquals(2, stack.size());
    }

    @Test
    void peek() {
        MyStack stack = new MyStack(3);
        stack.push("X");
        stack.push("Y");
        assertEquals("Y", stack.peek(), "Peek should return the top element");
        assertEquals(2, stack.size(), "Peek should not remove element");
    }



    @Test
    void testSplitStack() {
        MyStack test = new MyStack(10);

        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);

        MyStack[] tested = test.splitStack();

        assertEquals(3, tested[0].size(), "Urt  n 3 bh ystoi");
        assertEquals(2, tested[1].size(), "Urt  n 2 bh ystoi");

    }
    @Test
    void inputStack() {
        MyStack stack = new MyStack(3);
        stack.inputStack("Hello");
        stack.inputStack("World");

        assertEquals(2, stack.size());
        assertEquals("World", stack.peek());
    }

    @Test
    void combineStack() {
        MyStack stack1 = new MyStack(3);
        stack1.push(1);
        stack1.push(2);

        MyStack stack2 = new MyStack(3);
        stack2.push("A");
        stack2.push("B");

        MyStack combined = stack1.combineStack(stack2);

        assertEquals(4, combined.size());
        assertEquals("A", combined.pop());
        assertEquals("B", combined.pop());
        assertEquals(1, combined.pop());
        assertEquals(2, combined.pop());
    }
}
