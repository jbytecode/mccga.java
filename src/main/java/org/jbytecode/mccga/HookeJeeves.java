package org.jbytecode.mccga;

public class HookeJeeves {

    private static double[] mutate(double[] par, int p, double d) {
        double[] newpar = par.clone();
        newpar[p - 1] += d;
        return newpar;
    }

    public static double[] hj(
            OptimizationFunction f,
            double[] parv,
            int maxiter,
            double startstep,
            double endstep) {
        int p = parv.length;
        double currentstep = startstep;
        int iter = 0;
        double[] par = parv.clone();
        while (iter < maxiter) {
            double fold = f.f(par);
            double fnow = fold;
            for (int currentp = 1; currentp <= p; currentp++) {
                double[] mutateleft = mutate(par, currentp, -currentstep);
                double fleft = f.f(mutateleft);
                double[] mutateright = mutate(par, currentp, currentstep);
                double fright = f.f(mutateright);
                if (fleft < fold) {
                    par = mutateleft;
                    fnow = fleft;
                } else if (fright < fold) {
                    par = mutateright;
                    fnow = fright;
                }
            }

            if (fold <= fnow) {
                currentstep = currentstep / 2.0;
            }

            if (currentstep < endstep) {
                break;
            }

            iter += 1;
        } // end of while

        return par;
    }

}
