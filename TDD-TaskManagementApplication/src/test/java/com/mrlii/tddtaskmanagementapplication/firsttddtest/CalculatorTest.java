package com.mrlii.tddtaskmanagementapplication.firsttddtest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void testDivideTwoPositiveNumbers(){
        Calculator calculator = new Calculator();

        double results = calculator.divide(6,2);

        assertEquals(3.0, results);
    }
}
