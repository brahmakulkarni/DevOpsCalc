import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CalculatorTest {
    @Test
    public void sqrtTestTP() {
        double ret = Calculator.squareRoot(4);
        assertEquals(2,ret, 0.0f);
    }

    @Test
    public void sqrtTestTN() {
        double ret = Calculator.squareRoot(16);
        assertNotEquals(3,ret, 0.0f);
    }

    @Test
    public void factorialTestTP() {
        int ret = Calculator.factorial(4);
        assertEquals(24,ret);
    }

    @Test
    public void factorialTestTN() {
        int ret = Calculator.factorial(3);
        assertNotEquals(7,ret);
    }

    @Test
    public void nLogTestTP() {
        double ret = Calculator.nLog(2.718);
        assertEquals(1,ret, 0.2f);
    }

    @Test
    public void nLogTestTN() {
        double ret = Calculator.nLog(2.718);
        assertNotEquals(2,ret, 0.2f);
    }

    @Test
    public void powerTestTP() {
        double ret = Calculator.power(2, 2);
        assertEquals(4,ret, 0.0f);
    }

    @Test
    public void powerTestTN() {
        double ret = Calculator.power(2, 2);
        assertNotEquals(5,ret, 0.0f);
    }
}
