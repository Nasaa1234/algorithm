package lab.tests;

import lab.functions.Expression;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;

public class ExpressionTest {

    private Expression exp;

    @BeforeEach
    void setUp() {
        exp = new Expression();
    }

    @Test
    @DisplayName("Test building from postfix")
    void testBuildFromPostfix() {
        String[] postfix = {"a", "b", "c", "*", "+"};
        exp.buildFromPostfix(postfix);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        exp.printInfix();
        exp.printPrefix();
        exp.printPostfix();

        String result = out.toString();

        assertTrue(result.contains("(a+(b*c))"));
        assertTrue(result.contains("+ a * b c"));
        assertTrue(result.contains("a b c * +"));
    }

    @Test
    @DisplayName("Test building from prefix")
    void testBuildFromPrefix() {
        String[] prefix = {"+", "a", "*", "b", "c"};
        exp.buildFromPrefix(prefix);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        exp.printInfix();
        exp.printPrefix();
        exp.printPostfix();

        String result = out.toString();

        assertTrue(result.contains("(a+(b*c))"));
        assertTrue(result.contains("+ a * b c"));
        assertTrue(result.contains("a b c * +"));
    }

    @Test
    @DisplayName("Test building from infix")
    void testBuildFromInfix() {
        String[] infix = {"(", "a", "+", "(", "b", "*", "c", ")", ")"};
        exp.buildFromInfix(infix);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        exp.printInfix();
        exp.printPrefix();
        exp.printPostfix();

        String result = out.toString();

        assertTrue(result.contains("(a+(b*c))"));
        assertTrue(result.contains("+ a * b c"));
        assertTrue(result.contains("a b c * +"));
    }

    @Test
    @DisplayName("Test evaluate numeric expression")
    void testEvaluateNumeric() {
        String[] postfix = {"3", "4", "5", "*", "+"};
        exp.buildFromPostfix(postfix);

        double result = exp.evaluate();
        assertEquals(23.0, result, 0.0001);
    }

    @Test
    @DisplayName("Test evaluate with variables (simulated input)")
    void testEvaluateWithVariables() {
        String[] postfix = {"a", "b", "c", "*", "+"};
        exp.buildFromPostfix(postfix);

        String input = "2\n3\n4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        double result = exp.evaluate();
        assertEquals(14.0, result, 0.0001);
    }
}
