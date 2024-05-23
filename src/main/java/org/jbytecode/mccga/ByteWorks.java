package org.jbytecode.mccga;

public class ByteWorks {

    public static int floatToBitwiseInt(float x) {
        return Float.floatToRawIntBits(x);
    }

    public static int[] floatToBits(final float x) {
        final int intval = floatToBitwiseInt(x);
        int[] bits = new int[32];
        int m = 0;
        for (int i = 31; i >= 0; i--) {
            bits[m] = (intval >> i) & 1;
            m++;
        }
        return bits;
    }

    public static double[] floatsToDoubles(final float[] fs){
        final int len = fs.length;
        double[] doubles = new double[len];
        for (int i = 0; i < len; i++){
            doubles[i] = fs[i];
        }
        return doubles;
    }

    public static int[] reverseBits(final int[] bits) {
        int[] newbits = new int[bits.length];
        int m = 0;
        for (int i = 31; i >= 0; i--) {
            newbits[m] = bits[i];
            m++;
        }
        return newbits;
    }

    public static float bitsToFloat(final int[] bits) {
        int u32value = 0;
        int m = 0;
        for (int i = 31; i >= 0; i--) {
            u32value = u32value + (bits[i] * (int) Math.pow(2.0, m));
            m++;
        }
        return Float.intBitsToFloat(u32value);
    }

    public static int[] floatsToBits(final float[] fs) {
        final int parlen = fs.length;
        final int bitlen = parlen * 32;
        int[] bits = new int[bitlen];
        int m = 0;
        for (int j = 0; j < parlen; j++) {
            int[] currentbits = ByteWorks.floatToBits(fs[j]);
            for (int i = 0; i < 32; i++) {
                bits[m] = currentbits[i];
                m++;
            }
        }
        return bits;
    }

    public static float[] bitsToFloats(final int[] v) {
        final int bitsize = v.length;
        final int floatssize = (int) (bitsize / 32);
        float[] floatvector = new float[floatssize];
        int index = 0;
        int findex = 0;
        while (index + 32 <= bitsize) {
            int[] part = new int[32];
            System.arraycopy(v, index, part, 0, 32);
            floatvector[findex] = ByteWorks.bitsToFloat(part);
            index = index + 32;
            findex += 1;
        }
        return floatvector;
    }
}
