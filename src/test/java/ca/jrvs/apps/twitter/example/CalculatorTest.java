package ca.jrvs.apps.twitter.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator;
    Integer sum;

    @Before
    public void setup(){
        calculator = new Calculator();
    }

    @Test
    public void evaluatenum() {
        sum = calculator.evaluate("1+3");
        assertNotNull(sum);
        assertTrue(4 == sum);
    }

    @Test
    public void evaluatealpha(){
        try {
            sum = calculator.evaluate(("a+b"));
            fail("Error: letters cannot be added");
        } catch (NumberFormatException e){
            e.printStackTrace();
        }


    }

    @Test
    public void evaluate() {
    }
}