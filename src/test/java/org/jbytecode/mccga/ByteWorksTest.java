package org.jbytecode.mccga;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ByteWorksTest {

    @Test
    public void floattoint_pi() {
        int expected = 0x40490fdb;
        int observed = ByteWorks.floatToBitwiseInt(3.14159265f);
        assertEquals(expected, observed);
    }

    @Test
    public void floattoint_e() {
        int expected = 0x402df854;
        int observed = ByteWorks.floatToBitwiseInt(2.718281828459045f);
        assertEquals(expected, observed);
    }

    @Test
    public void bitsofpi() {
        int[] bits = ByteWorks.floatToBits(3.14159265f);
        int[] expected = new int[] { 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1,
                1, 0, 1, 1 };
        assertEquals(bits.length, expected.length);
        assertArrayEquals(expected, bits);
    }

    @Test
    public void bitstofloat() {
        float eps = 0.00001f;
        int[] bits = new int[] { 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1,
                1, 0, 1, 1 };
        float f = ByteWorks.bitsToFloat(bits);
        assertEquals(3.14159265f, f, eps);
    }

    @Test
    public void floatstobits() {
        int[] bits = new int[] { 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1,
                1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0,
                0 };
        float[] fs = new float[] { 3.14159265f, 2.718281828459045f };
        int[] observed = ByteWorks.floatsToBits(fs);
        assertArrayEquals(bits, observed);
    }

    @Test
    public void bitstofloats() {
        float eps = 0.00001f;
        int[] bits = new int[] { 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1,
                1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0,
                0 };
        float[] fs = ByteWorks.bitsToFloats(bits);
        assertTrue(fs.length == 2);
        assertEquals(3.14159265f, fs[0], eps);
        assertEquals(2.718281828459045f, fs[1], eps);
    }
}
