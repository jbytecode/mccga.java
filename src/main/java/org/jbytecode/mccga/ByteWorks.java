package org.jbytecode.mccga;

public class ByteWorks {

    public static int floatToBitwiseInt(float x) {
        return Float.floatToRawIntBits(x);
    }

    public static int[] floatToBits(float x) {
        int intval = floatToBitwiseInt(x);
        int[] bits = new int[32];
        int m = 0;
        for (int i = 31; i >= 0; i--) {
            bits[m] = (intval >> i) & 1;
            m++;
        }
        return bits;
    }

    public static int[] reverseBits(int[] bits) {
        int[] newbits = new int[bits.length];
        int m = 0;
        for (int i = 31; i >= 0; i--) {
            newbits[m] = bits[i];
            m++;
        }
        return newbits;
    }

    public static float bitsToFloat(int[] bits) {
        int u32value = 0;
        int m = 0;
        for (int i = 31; i >= 0; i--) {
            u32value = u32value + (bits[i] * (int) Math.pow(2.0, m));
            m++;
        }
        return Float.intBitsToFloat(u32value);
    }

    public static int[] floatsToBits(float[] fs) {
        int parlen = fs.length;
        int bitlen = parlen * 32;
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

    public static float[] bitsToFloats(int[] v) {
        int bitsize = v.length;
        int floatssize = (int) (bitsize / 32);
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