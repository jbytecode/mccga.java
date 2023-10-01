package org.jbytecode.mccga;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MccgaTest {

    @Test
    public void twoVariableFunctionTest() {

        OptimizationFunction of = new OptimizationFunction() {
            @Override
            public double f(double[] x) {
                return Math.pow(x[0] - 3.14159265, 2.0) + Math.pow(x[1] - 2.71828, 2.0);
            }
        };

        double eps = 0.001;
        double[] exp = new double[] { 3.14159265, 2.718282 };

        double[] mins = new double[] { -100.0, -100.0 };
        double[] maxs = new double[] { 100.0, 100.0 };

        double mutrate = 0.001;
        int maxiter = 10000;

        double[] result = Mccga.mccga(of, mins, maxs, mutrate, maxiter);

        assertArrayEquals(exp, result, eps);

    }

    @Test
    public void fiveVariableFunctionTest() {

        final double[] output = new double[] { 3.0, 30.0, 300.0, 3000.0, 30000.0 };

        OptimizationFunction of = new OptimizationFunction() {
            @Override
            public double f(double[] x) {
                double result = 0.0;
                for (int i = 0; i < output.length; i++) {
                    result += Math.pow(x[i] - output[i], 2.0);
                }
                return result;
            }
        };

        double eps = 0.001;

        double[] mins = new double[] { -100000.0, -100000.0, -100000.0, -100000.0, -100000.0 };
        double[] maxs = new double[] { 100000.0, 100000.0, 100000.0, 100000.0, 100000.0 };

        double mutrate = 0.001;
        int maxiter = 100000;

        double[] result = Mccga.mccga(of, mins, maxs, mutrate, maxiter);

        assertArrayEquals(output, result, eps);

    }
}
