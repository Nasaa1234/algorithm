import dataStructures.Stack;

public class MyStack implements Stack { //last in first out LIFO

    private Object[] stack;
    private int top;
    private int capacity;

    public MyStack(int size) {
        capacity = size;
        stack = new Object[capacity];
        top = -1;
    }

    public boolean empty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public int size() {
        return top + 1;

    }
    public Object pop() {
        if (empty()) {
            System.out.println("Алдаа: Стек хоосон!");
            return null;
        }
        return stack[top--];
    }

    public Object peek() {
        if (empty()) {
            System.out.println("Алдаа: Стек хоосон!");
            return null;
        }
        return stack[top];
    }

    public void push(Object item) {
        if (isFull()) {
            System.out.println("Алдаа: Стек дүүрсэн!");
            return;
        }
        stack[++top] = item;
    }

    public MyStack[] splitStack() {
        int mid = (size() + 1) / 2;
        MyStack firstSplit = new MyStack(mid);
        MyStack secondSplit = new MyStack(size() - mid);

        for (int i = 0; i <= top; i++) {
            if (i < mid) firstSplit.push(stack[i]);
            else secondSplit.push(stack[i]);
        }
        return new MyStack[]{firstSplit, secondSplit};
    }

    public void inputStack(Object item) {
        if (isFull()) {
            System.out.println("Алдаа: Стек дүүрсэн!");
            return;
        }
        stack[++top] = item;
    }

    public void printStack() {
        for (int i = 0; i < this.size(); i++) {
            System.out.print(stack[i] + " ");
        }
    }

    public MyStack combineStack(MyStack other) {
        MyStack combined = new MyStack(this.size() + other.size() );
        for (int i = this.size() - 1; i >= 0; i--) {
            combined.push(this.stack[i]);
        }
        for (int i = other.size() - 1; i >= 0; i--) {
            combined.push(other.stack[i]);
        }
        return combined;
    }
    public static void main(String[] args) {
        MyStack stack1 = new MyStack(10);
        MyStack stack2 = new MyStack(10);

        stack1.push(1);
        stack1.push(2);
        stack1.push(3);

        stack2.push("A");
        stack2.push("B");
        stack2.push("C");

        System.out.print("Stack1: ");
        stack1.printStack();
        System.out.println();

        System.out.print("Stack2: ");
        stack2.printStack();
        System.out.println();

        System.out.println("Top of Stack1: " + stack1.peek());

        System.out.println("Popped from Stack1: " + stack1.pop());

        MyStack[] parts = stack1.splitStack();
        System.out.print("First half of Stack1: ");
        parts[0].printStack();
        System.out.println();
        System.out.print("Second half of Stack1: ");
        parts[1].printStack();
        System.out.println();

        MyStack combined = stack1.combineStack(stack2);
        System.out.print("Combined Stack: ");
        combined.printStack();
    }






}

//- size() –Стекийн хэмжээг тодорхойлох
//- inputStack() – Стекийг оруулах
//- printStack() – Стекийг хэвлэх/дэлгэцлэх(Стекийн утгуудыг string төрөлд
//        хувиргах хэрэг гарна)
//- splitStack) – Стекийг хоёр болгож хуваах(элементүүдийн дараалал
//өөрчлөгдөхгүй)
//- combineStack()– Эхний стекийн орой дээр хоёр дахь стекийн
//элементүүдийг нэмэх замаар шинэ стек үүсгэх(элементүүдийн дараалал
//өөрчлөгдөхгүй).