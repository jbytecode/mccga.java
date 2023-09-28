package org.jbytecode.mccga;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SamplerTest {

    @Test
    public void testsampler1() {
        double[] probvector = new double[] { 1.0, 1.0, 0.0, 0.0 };
        int[] bits = Sampler.sample(probvector);
        assertTrue(bits[0] == 1);
        assertTrue(bits[1] == 1);
        assertTrue(bits[2] == 0);
        assertTrue(bits[3] == 0);
    }

    @Test
    public void testsampler2() {
        double[] probvector = new double[] { 0.5, 0.5, 0.5, 0.5 };
        int[] bits = Sampler.sample(probvector);
        assertTrue(bits[0] >= 0);
        assertTrue(bits[1] >= 0);
        assertTrue(bits[2] >= 0);
        assertTrue(bits[3] >= 0);
        assertTrue(bits[0] <= 1);
        assertTrue(bits[1] <= 1);
        assertTrue(bits[2] <= 1);
        assertTrue(bits[3] <= 1);
    }

    @Test 
    public void generateProbabilityVector1(){
        double[] mins = new double[]{100.0, 100.0};
        double[] maxs = new double[]{1000.0, 1000.0};
        double[] bits = Sampler.generateProbabilityVector(mins, maxs, 100000);
        assertTrue(bits.length == 32 * 2);
        // Just because the values are positive
        // the first bits of values are zero
        assertTrue(bits[0] < 0.1);
        assertTrue(bits[32] < 0.1);
    }

    @Test 
    public void generateProbabilityVector2(){
        double[] mins = new double[]{-1000.0, -1000.0};
        double[] maxs = new double[]{-100.0, -100.0};
        double[] bits = Sampler.generateProbabilityVector(mins, maxs, 100000);
        assertTrue(bits.length == 32 * 2);
        // Just because the values are negative
        // the first bits of values are one
        assertTrue(bits[0] > 0.9);
        assertTrue(bits[32] > 0.9);
    }
}
