package com.sacintha.interview.arm.service;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalculatorTest {


    private Calculator calculator = new Calculator();
    @Test
    public void setJunitTest() {assertTrue(true);}

    @Test
    public void testEmptyString () {
        try {
            assertTrue(calculator.calculate("").isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOneNumber() {
        try {
            assertEquals("2", calculator.calculate("2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testtwoSimpleNumber() {
        try {
            assertEquals("5",invokePrivateMethods(calculator, "calculateExpression", "2+3"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConverttoExpression_ivalid() {
        try {
            assertTrue(invokePrivateMethods(calculator, "convertToExpression", "sfsdf", "").isEmpty());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConverttoExpression() {
        try {
            assertEquals("7+8", invokePrivateMethods(calculator, "convertToExpression", "7,8", ","));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSimpleTwoNumbers() {
        try {
            assertEquals("6", calculator.calculate("2,4"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSimpleThreewoNumbers() {
        try {
            assertEquals("14", calculator.calculate("2,4,8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSimpleThreewNumberswithNewLine() {
        try {
            assertEquals("14", calculator.calculate("2,4\n8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testOperatorSet() {
        try {
            assertEquals("//,,\n", invokePrivateMethods(calculator, "getOperatorSet", "//,,\n7879,,89"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testBitmultipleexpression() {
        try {
            assertEquals("15", calculator.calculate("//,,\n7,,8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(expected = Exception.class)
    public void testNegativeNumber() throws Exception {
        assertEquals("15", calculator.calculate("//,,\n7,,-8"));

    }

    @Test
    public void testmultiple() throws Exception {
        assertEquals("20", calculator.calculate("//[,,][£££]\n7,,8£££5"));

    }


    private String invokePrivateMethods (Calculator calulator,String methodName,String... expression) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
            Class[] methodType = new Class[expression.length];
            for(int i =0 ;i<expression.length;i++) {
                methodType[i] =String.class;
            }
            Method privateCalculateExpressionMethod = Calculator.class.getDeclaredMethod(methodName, methodType);
            privateCalculateExpressionMethod.setAccessible(true);
            String returnValue = (String)
                    privateCalculateExpressionMethod.invoke(calulator, expression);
            return returnValue;
    }
}
