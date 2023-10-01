package org.jbytecode.mccga;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class HookeJeevesTest {
    
    @Test
    public void twoVariableFunctionTest(){

        OptimizationFunction of = new OptimizationFunction() {
            @Override
            public double f(double[] x) {
                return Math.pow(x[0] - 3.14159265, 2.0) + Math.pow(x[1] - 2.71828, 2.0);
            }
        };

        double eps = 0.001;
        double[] exp = new double[]{3.14159265, 2.718282};
        double[] parv = new double[]{0.0, 0.0};
        double[] result = HookeJeeves.hj(of, parv, 100000, 5000, 0.000001);
        assertArrayEquals(exp, result, eps);
    }
}
