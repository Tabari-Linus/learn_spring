package com.mrlii.tddtaskmanagementapplication.firsttddtest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    @Test
    void testDivideTwoPositiveNumbers(){
        Calculator calculator = new Calculator();

        double results = calculator.divide(6,2);

        assertEquals(3.0, results);
    }

    @Test
    void testDivideByZero(){
        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class, () -> calculator.divide(6,0));
    }

    @Test
    void testDivideByNegativeNumber(){
        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class, () -> calculator.divide(6,-2));
    }
}
