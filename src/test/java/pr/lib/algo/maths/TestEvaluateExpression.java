package pr.lib.algo.maths;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class TestEvaluateExpression {
    @Test
    public void testSimpleExpressions() {
        EvaluateExpression sol = new EvaluateExpression();
        assertEquals(sol.eval("10 + 2 * 6"), 22);
        assertEquals(sol.eval("100 * 2 + 12"), 212);
        assertEquals(sol.eval("100 * ( 2 + 12 )"), 1400);
        assertEquals(sol.eval("100 * ( 2 + 12 ) / 14"), 100);
    }
}
