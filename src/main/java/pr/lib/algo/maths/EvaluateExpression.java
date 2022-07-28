package pr.lib.algo.maths;

import java.util.Stack;

public class EvaluateExpression {
    public int eval(String s) {
        if (s == null || s.length() == 0) return 0;

        Stack<Integer> valStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();

        char[] cs = s.toCharArray();

        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == ' ') continue;

            // if digit push to stack
            if (isDigit(cs[i])) {
                int num = 0;
                while (i < cs.length && isDigit(cs[i])) {
                    num = num * 10 + cs[i] - '0';
                    i++;
                }
                valStack.push(num);
                i--; // Reset i that passes 1 more
            } else if (cs[i] == '(') {
                opStack.push(cs[i]);
            }
            else if(cs[i] == ')') {
                while (opStack.peek() != '(') {
                    valStack.push(applyOp(opStack.pop(), valStack.pop(), valStack.pop()));
                }
                opStack.pop();  // remove (
            } else {  // operator: +, -, *, /
                while (!opStack.isEmpty() && hasHigherPrecedence(cs[i], opStack.peek())) {
                    valStack.push(applyOp(opStack.pop(), valStack.pop(), valStack.pop()));
                }
                opStack.push(cs[i]);  // push current operator
            }
        }

        // act on items remaining in stack
        while (!opStack.isEmpty()) {
            valStack.push(applyOp(opStack.pop(), valStack.pop(), valStack.pop()));
        }

        return valStack.pop();
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    // check if op2 has higher precedence than op1
    private boolean hasHigherPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == '(') {
            return false;
        } else if ((op1 == '*' || op1 == '/') && (op2 == '-' || op2 == '+')) {
            return false;
        }

        return true;
    }
    private int applyOp(char op, int val2, int val1) {
        if (op == '*') return val1 * val2;
        else if (op == '-') return val1 - val2;
        else if (op == '+') return val1 + val2;
        else if (op == '/') return val1 / val2;
        else return 0;
    }
}
