package com.alekseyld.domain;

import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.utils.ExpressionUtils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alekseyld on 24.09.2017.
 */

public class ExpressionUtilsTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertTrue(ExpressionUtils.calculateExpression("5 + 5").equals(10d));
        assertTrue(ExpressionUtils.calculateExpression("5+5+3").equals(13d));
        assertTrue(ExpressionUtils.calculateExpression("5+5+3/2").equals(11.5d));
        assertTrue(ExpressionUtils.calculateExpression("5+5+3/2").equals(11.5d));
        assertTrue(ExpressionUtils.calculateExpression("5-5").equals(0d));
        assertTrue(ExpressionUtils.calculateExpression("5+5+3/2-1+1.5-4+5/1*5").equals(33d));
        assertTrue(ExpressionUtils.calculateExpression("5+5+3/2-1+1.5-4+5/1*5").equals(33d));
        assertTrue(ExpressionUtils.calculateExpression("(5+5)/5").equals(2d));
        assertTrue(ExpressionUtils.calculateExpression("2^2").equals(4d));

        final Map<String, Double> vars = new HashMap<>();
        vars.put("a", 10d);
        vars.put("b", 2d);

        assertTrue(ExpressionUtils.calculateExpressionWithVar("2^a", vars).equals(1024d));
        assertTrue(ExpressionUtils.calculateExpressionWithVar("2^a*b+4^(1/2)", vars).equals(2050d));
        assertTrue(ExpressionUtils.calculateExpression("4^(1/2)").equals(2d));
        assertTrue(ExpressionUtils.calculateExpression("2^10*2+4^(1/2)").equals(2050d));

        assertTrue(ExpressionUtils.calculateExpression("2 log 2").equals(1d));
        assertTrue(ExpressionUtils.calculateExpression("1 log 2.71828183").equals(0d));
        assertTrue(ExpressionUtils.calculateExpression("(1) log 2.71828183").equals(0d));

        Formula formula = new Formula("");
        formula.setRawFormula("5 + lg(5) * x");
    }

}
