package ca.jrvs.apps.twitter.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    private Calculator calculator;
    Integer sum;

    public CalculatorTest() {
    }

    @Before
    public void setup() {
        this.calculator = new Calculator();
    }

    @Test
    public void evaluatenum() {
        this.sum = this.calculator.evaluate("1+3");
        Assert.assertNotNull(this.sum);
        Assert.assertTrue(4 == this.sum);
    }

    @Test
    public void evaluatealpha() {
        try {
            this.sum = this.calculator.evaluate("a+b");
            Assert.fail("Error: letters cannot be added");
        } catch (NumberFormatException var2) {
            var2.printStackTrace();
        }

    }


}
