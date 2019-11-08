import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MathTest {

    @Test
    public void testSum() {
        assertEquals(10, Math.addExact(7, 3));

    }
}
