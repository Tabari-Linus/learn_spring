package com.mrlii.tddtaskmanagementapplication.firsttddtest;

public class Calculator {

    public double divide(int number1, int number2) {
        if (number2 == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        if (number2 < 0) {
            throw new ArithmeticException("Cannot divide by negative number");
        }
        return (double) number1 / number2;
    }
}
