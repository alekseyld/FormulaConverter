package com.alekseyld.domain;

import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.utils.ExpressionUtils;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alekseyld on 24.09.2017.
 */

public class ExpressionUtilsTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertTrue(ExpressionUtils.calculateExpression("5 + 5").equals(new BigDecimal(10)));
        assertTrue(ExpressionUtils.calculateExpression("5+5+3").equals(new BigDecimal(13)));
        assertTrue(ExpressionUtils.calculateExpression("5+5+3/2").equals(new BigDecimal(11.5)));
        assertTrue(ExpressionUtils.calculateExpression("5+5+3/2").equals(new BigDecimal(11.5)));
        assertTrue(ExpressionUtils.calculateExpression("5-5").equals(new BigDecimal(0)));
        assertTrue(ExpressionUtils.calculateExpression("5+5+3/2-1+1.5-4+5/1*5").toString().equals("33.0"));
        assertTrue(ExpressionUtils.calculateExpression("5+5+3/2-1+1.5-4+5/1*5").toString().equals("33.0"));
        assertTrue(ExpressionUtils.calculateExpression("(5+5)/5").toString().equals("2"));
        assertTrue(ExpressionUtils.calculateExpression("2^2").toString().equals("4"));

        final Map<String, BigDecimal> vars = new HashMap<>();
        vars.put("a", new BigDecimal(10));
        vars.put("b", new BigDecimal(2));

        assertTrue(ExpressionUtils.calculateExpressionWithVar("2^a", vars).toString().equals("1024"));
        assertTrue(ExpressionUtils.calculateExpressionWithVar("2^a*b+4^(1/2)", vars).toString().equals("2050.000000"));
        assertTrue(ExpressionUtils.calculateExpression("4^(1/2)").toString().equals("2.000000"));
        assertTrue(ExpressionUtils.calculateExpression("2^10*2+4^(1/2)").toString().equals("2050.000000"));

        assertTrue(ExpressionUtils.calculateExpression("2 log 2").toString().equals("1"));
        assertTrue(ExpressionUtils.calculateExpression("1 log 2.71").toString().equals("0"));
        assertTrue(ExpressionUtils.calculateExpression("(1) log 2.71").toString().equals("0"));

        Formula formula = new Formula("");
        formula.setRawFormula("5 + lg(5) * x");
    }

}
