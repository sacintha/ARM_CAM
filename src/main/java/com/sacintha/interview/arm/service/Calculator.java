package com.sacintha.interview.arm.service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public String calculate(String expressions) throws Exception {

        if (expressions == null || expressions.trim().isEmpty()) {
            return "";
        }
        String sum = expressions;
        String operatorSet = getOperatorSet(expressions);
        if(operatorSet.startsWith("//")) {
            sum = convertToExpression(expressions.replace(operatorSet,""),operatorSet);
        } else {
            sum = convertToExpression(expressions,",");
        }
        sum = calculateExpression(sum);
        return sum;
    }

    private String getOperatorSet(String expression) {
        String operatorSet = expression;
        Pattern pattern = Pattern.compile("\\D*");
        Matcher matcher = pattern.matcher(expression);
        while(matcher.find()) {
            operatorSet = matcher.group();
            if(!operatorSet.isEmpty()) {
                return operatorSet;
            }
        }

        return operatorSet;
    }

    private String convertToExpression(String inputExpression, String operatorSet) {
        String outPutExpression = inputExpression;
        if (inputExpression == null || operatorSet == null || inputExpression.trim().isEmpty() || operatorSet.isEmpty()) {
            return "";
        }
        if(operatorSet.startsWith("//")) {
            Pattern pattern = Pattern.compile("[^\n/\\[\\]]*");
            Matcher matcher = pattern.matcher(operatorSet);
            while (matcher.find()) {
                String matchString = matcher.group();
                if (!matchString.isEmpty()) {
                    outPutExpression = outPutExpression.replaceAll(matchString, "+");
                    outPutExpression = outPutExpression.replaceAll("\\n", "+");

                }
            }
        } else {
            outPutExpression = inputExpression.replaceAll(operatorSet,"+");
            outPutExpression= outPutExpression.replaceAll("\\n","+");
           }

        return outPutExpression;
    }


    private String calculateExpression(String expression) throws Exception {
        ScriptEngineManager seManager = new ScriptEngineManager();
        ScriptEngine engine = seManager.getEngineByName("js");
        Integer output = null;
        if(expression.contains("-")) {// not the best way to habdle but running out of time
            throw new Exception("Negative not allowed");
        }
        try {
            output = (Integer) engine.eval(expression);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
