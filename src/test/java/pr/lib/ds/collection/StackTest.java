package pr.lib.ds.collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    public void testDijkstraTwoStackArithmeticExpressionEvaluationWithParentheses() {
        String expression = "( 1 ( ( 2 3 + ) ( 4 5 * ) * ) + )";
        Stack<Double> operandStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        String[] split = expression.split(" ");
        for (String s : split) {
            switch (s) {
                case "(":
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    operatorStack.push(s);
                    break;
                case ")":
                    double x = operandStack.pop();
                    double y = operandStack.pop();
                    operandStack.push(apply(x, y, operatorStack.pop()));
                    break;
                default:
                    operandStack.push(Double.parseDouble(s));
                    break;
            }
        }

        assertEquals((double)operandStack.pop(), 101.0);
    }

    @Test
    public void testStackIteration() {
        String expression = "( 1 ( ( 2 3 + ) ( 4 5 * ) * ) + )";
        Stack<String> stringStack = new Stack<>();
        String[] split = expression.split(" ");
        for (String s : split) {
            stringStack.push(s);
        }

        // Prints in LIFO order (unlike java standard library)
        // ) + ) * ) * 5 4 ( ) + 3 2 ( ( 1 (
        for (String s : stringStack) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    private double apply(double x, double y, String operator) {
        switch(operator) {
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                return x / y;
            default:
                return 0.0;
        }
    }

    @Test
    public void testStack() {
        Stack<Short> stack = new Stack<>();
        stack.push((short) 2);
        stack.push((short) 3);

        assertEquals((short)stack.peek(), 3);
    }
}