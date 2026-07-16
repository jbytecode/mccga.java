package org.jbytecode.mccga;

public class HookeJeeves {

    private static double[] mutate(final double[] par, int p, double d) {
        double[] newpar = par.clone();
        newpar[p] += d;
        return newpar;
    }

    private static double[] exploratorySearch(
            final OptimizationFunction f,
            final double[] start,
            final double step) {
        double[] point = start.clone();
        double best = f.f(point);

        for (int p = 0; p < point.length; p++) {
            double[] left = mutate(point, p, -step);
            double fleft = f.f(left);
            if (fleft < best) {
                point = left;
                best = fleft;
                continue;
            }

            double[] right = mutate(point, p, step);
            double fright = f.f(right);
            if (fright < best) {
                point = right;
                best = fright;
            }
        }

        return point;
    }

    public static double[] hj(
            final OptimizationFunction f,
            final double[] parv,
            final int maxiter,
            final double startstep,
            final double endstep) {
        double currentstep = startstep;
        int iter = 0;
        double[] base = parv.clone();

        while (iter < maxiter) {
            double[] explored = exploratorySearch(f, base, currentstep);
            double fbase = f.f(base);
            double fexplored = f.f(explored);

            if (fexplored < fbase) {
                double[] pattern = new double[base.length];
                for (int i = 0; i < pattern.length; i++) {
                    pattern[i] = explored[i] + (explored[i] - base[i]);
                }

                double[] patternExplored = exploratorySearch(f, pattern, currentstep);
                if (f.f(patternExplored) < fexplored) {
                    base = patternExplored;
                } else {
                    base = explored;
                }
            } else {
                currentstep = currentstep / 2.0;
            }

            if (currentstep < endstep) {
                break;
            }

            iter += 1;
        } // end of while

        return base;
    }

}
