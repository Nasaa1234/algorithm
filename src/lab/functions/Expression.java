package lab.functions;

import java.util.*;
import java.io.*;

class LinkedBinaryTree<T> {
    static class Node<T> {
        T value;
        Node<T> left, right;

        Node(T value) {
            this.value = value;
            left = right = null;
        }
    }

    protected Node<T> root;

    public LinkedBinaryTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Node<T> getRoot() {
        return root;
    }
}

public class Expression extends LinkedBinaryTree<String> {

    private static final Set<String> OPERATORS = Set.of("+", "-", "*", "/", "^");

    public void buildFromPostfix(String[] postfix) {
        Stack<Node<String>> stack = new Stack<>();

        for (String token : postfix) {
            if (!OPERATORS.contains(token)) {
                stack.push(new Node<>(token));
            } else {
                Node<String> right = stack.pop();
                Node<String> left = stack.pop();
                Node<String> node = new Node<>(token);
                node.left = left;
                node.right = right;
                stack.push(node);
            }
        }

        root = stack.pop();
    }


    public void buildFromPrefix(String[] prefix) {
        Stack<Node<String>> stack = new Stack<>();
        for (int i = prefix.length - 1; i >= 0; i--) {
            String token = prefix[i];
            if (!OPERATORS.contains(token)) {
                stack.push(new Node<>(token));
            } else {
                Node<String> left = stack.pop();
                Node<String> right = stack.pop();
                Node<String> node = new Node<>(token);
                node.left = left;
                node.right = right;
                stack.push(node);
            }
        }
        root = stack.pop();
    }

    public void buildFromInfix(String[] infix) {
        Stack<String> ops = new Stack<>();
        List<String> postfix = new ArrayList<>();

        for (String token : infix) {
            if (!OPERATORS.contains(token) && !token.equals("(") && !token.equals(")")) {
                postfix.add(token);
            } else if (token.equals("(")) {
                ops.push(token);
            } else if (token.equals(")")) {
                while (!ops.isEmpty() && !ops.peek().equals("(")) {
                    postfix.add(ops.pop());
                }
                ops.pop();
            } else {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(token)) {
                    postfix.add(ops.pop());
                }
                ops.push(token);
            }
        }

        while (!ops.isEmpty()) postfix.add(ops.pop());
        buildFromPostfix(postfix.toArray(new String[0]));
    }

    private int precedence(String op) {
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }

    public void printInfix() {
        System.out.print("Infix: ");
        printInfix(root);
        System.out.println();
    }

    private void printInfix(Node<String> node) {
        if (node == null) return;
        if (OPERATORS.contains(node.value)) System.out.print("(");
        printInfix(node.left);
        System.out.print(node.value);
        printInfix(node.right);
        if (OPERATORS.contains(node.value)) System.out.print(")");
    }

    public void printPrefix() {
        System.out.print("Prefix: ");
        printPrefix(root);
        System.out.println();
    }

    private void printPrefix(Node<String> node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        printPrefix(node.left);
        printPrefix(node.right);
    }

    public void printPostfix() {
        System.out.print("Postfix: ");
        printPostfix(root);
        System.out.println();
    }

    private void printPostfix(Node<String> node) {
        if (node == null) return;
        printPostfix(node.left);
        printPostfix(node.right);
        System.out.print(node.value + " ");
    }

    public double evaluate() {
        Scanner sc = new Scanner(System.in);
        return evaluate(root, sc);
    }

    private double evaluate(Node<String> node, Scanner sc) {
        if (node == null) return 0;

        if (!OPERATORS.contains(node.value)) {
            try {
                return Double.parseDouble(node.value);
            } catch (NumberFormatException e) {
                System.out.print("Enter value for " + node.value + ": ");
                return sc.nextDouble();
            }
        }

        double leftVal = evaluate(node.left, sc);
        double rightVal = evaluate(node.right, sc);

        return switch (node.value) {
            case "+" -> leftVal + rightVal;
            case "-" -> leftVal - rightVal;
            case "*" -> leftVal * rightVal;
            case "/" -> leftVal / rightVal;
            case "^" -> Math.pow(leftVal, rightVal);
            default -> 0;
        };
    }

    public static void main(String[] args) {
        Expression exp = new Expression();

        String[] postfix = {"a", "b", "c", "*", "+"};
        exp.buildFromPostfix(postfix);
        exp.printInfix();
        exp.printPrefix();
        exp.printPostfix();
        System.out.println("Result: " + exp.evaluate());
    }
}
