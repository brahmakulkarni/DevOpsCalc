import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void sqrtTest() {
        double ret = Calculator.squareRoot(4);
        assertEquals(2,ret, 0.0f);
    }
}
