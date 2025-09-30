import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    @Test
    void emptyAndPushPop() {
        MyStack stack = new MyStack(3);
        assertTrue(stack.empty(), "Stack should be empty at start");

        stack.push(10);
        assertFalse(stack.empty(), "Stack should not be empty after push");

        Object popped = stack.pop();
        assertEquals(10, popped, "Pop should return the pushed element");
        assertTrue(stack.empty(), "Stack should be empty again after pop");
    }

    @Test
    void isFull() {
        MyStack stack = new MyStack(2);
        assertFalse(stack.isFull());

        stack.push(1);
        stack.push(2);
        assertTrue(stack.isFull(), "Stack should be full after pushing capacity elements");
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
    void splitStack() {
        MyStack stack = new MyStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        MyStack[] parts = stack.splitStack();
        assertEquals(3, parts[0].size(), "First split should have 3 elements");
        assertEquals(2, parts[1].size(), "Second split should have 2 elements");
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
