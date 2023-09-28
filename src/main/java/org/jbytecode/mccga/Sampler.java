package org.jbytecode.mccga;

public class Sampler {

    public static int[] sample(double[] probvector) {
        int len = probvector.length;
        int[] bits = new int[len];
        for (int i = 0; i < len; i++) {
            if (Math.random() < probvector[i]) {
                bits[i] = 1;
            } else {
                bits[i] = 0;
            }
        }
        return bits;
    }

    public static float[] getrandomvectorbetween(double[] mins, double[] maxs) {
        int n = mins.length;
        float[] result = new float[n];
        for (int i = 0; i < n; i++) {
            result[i] = (float) (mins[i] + (Math.random() * (maxs[i] - mins[i])));
        }
        return result;
    }

    public static double[] generateProbabilityVector(double[] mins, double[] maxs, int ntries) {
        int nbits = mins.length * 32;
        double mutrate = 1.0 / (double) ntries;
        double[] probvector = new double[nbits];
        for (int i = 0; i < ntries; i++) {
            float[] floats = getrandomvectorbetween(mins, maxs);
            int[] floatbits = ByteWorks.floatsToBits(floats);
            for (int k = 0; k < nbits; k++) {
                if (floatbits[k] == 1) {
                    probvector[k] = probvector[k] + mutrate;
                }
            }
        }
        return probvector;
    }

}
