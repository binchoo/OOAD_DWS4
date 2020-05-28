import org.junit.Test;
import static org.junit.Assert.*;

public class MultiplierTest {

    @Test
    public void multiply() {
        Multiplier multiplier = new Multiplier();
        assertEquals(multiplier.Multiply(8, 9), 72);
    }

    @Test
    public void testMultiply() {
        Multiplier multiplier = new Multiplier();
        assertEquals(multiplier.Multiply(2, 3, 4), 24);
    }
}