package org.jbytecode.mccga;

public class Mccga {
    public static void main(String[] args) {
        System.out.println("MCCGA - Machine-coded Compact Genetic Algorithm");
    }

    private static double binarycost(final OptimizationFunction fcost, final int[] candidate) {
        double[] doubles = ByteWorks.floatsToDoubles(ByteWorks.bitsToFloats(candidate));
        final double result = fcost.f(doubles);
        return result;
    }

    public static void update(double[] probvector, final int[] winner, final int[] loser, double mutrate) {
        for (int i = 0; i < probvector.length; i++) {
            if (winner[i] != loser[i]) {
                if (winner[i] == 0) {
                    probvector[i] = Math.max(probvector[i] - mutrate, 0.0);
                } else {
                    probvector[i] = Math.min(probvector[i] + mutrate, 1.0);
                }
            }
        }
    }

    public static void mccga_singleiter(double[] probvect, final OptimizationFunction fcost, double mutrate) {
        int[] candidate1 = Sampler.sample(probvect);
        int[] candidate2 = Sampler.sample(probvect);
        double cost1 = binarycost(fcost, candidate1);
        double cost2 = binarycost(fcost, candidate2);
        int[] winner = candidate1;
        int[] loser = candidate2;
        if (cost2 < cost1) {
            winner = candidate2;
            loser = candidate1;
        }
        update(probvect, winner, loser, mutrate);
    }

    public static boolean isequal(double x, double other, double mutrate) {
        return Math.abs(x - other) <= mutrate;
    }

    public static boolean isconverged(final double[] probvector, double mutrate) {
        int n = probvector.length;
        int satisfied = 0;
        for (int i = 0; i < n; i++) {
            double val = probvector[i];
            if (isequal(val, 0.0, mutrate) || isequal(val, 1.0, mutrate)) {
                satisfied += 1;
            } else {
                break;
            }
        }
        return satisfied == n;
    }

    public static double[] mccga(
            final OptimizationFunction fcost,
            final double[] mins,
            final double[] maxs,
            double mutrate,
            int maxiter) {
        
        double[] probvect = Sampler.generateProbabilityVector(mins, maxs, 500000);

        int iter = 0;

        while (iter < maxiter) {
            mccga_singleiter(probvect, fcost, mutrate);

            if (isconverged(probvect, mutrate)) {
                break;
            }

            iter = iter + 1;
        }

        double[] firstresult = ByteWorks.floatsToDoubles(ByteWorks.bitsToFloats(Sampler.sample(probvect)));

        double[] seconresult = HookeJeeves.hj(fcost, firstresult, 1000, 5.0, 0.00001);

        return seconresult;
    }
}
