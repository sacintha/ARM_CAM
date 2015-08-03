package com.sacintha.interview.arm.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CalcuolatorTest {





    private String invokePrivateMethods (Calculator calulator,String methodName,String... expression) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        try {
            Class[] methodType = new Class[expression.length];
            for(int i =0 ;i<expression.length;i++) {
                methodType[i] =String.class;
            }
            Method privateCalculateExpressionMethod = Calculator.class.getDeclaredMethod(methodName, methodType);
            privateCalculateExpressionMethod.setAccessible(true);
            String returnValue = (String)
                    privateCalculateExpressionMethod.invoke(calulator, expression);
            return returnValue;
        } catch (NoSuchMethodException |InvocationTargetException |IllegalAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
