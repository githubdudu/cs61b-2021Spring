import org.junit.Test;

import static org.junit.Assert.*;

public class TestIdenticalObjects {
    @Test
    public void testIdenticalDogs() {
        Dog d1 = new Dog(10, 20, "white");
        Dog d2 = new Dog(10, 20, "white");
        Dog d3 = new Dog(10, 20, "black");
        assertEquals(d1, d2); // This line should throw error
        assertNotEquals(d1, d3);
    }

}