# mccga.java
Machine-coded compact genetic algorithm in Java.

## In-short

The package implements the Machine-coded compact genetic algorithm defined in 

Satman, M. H. & Akadal, E. (2020). Machine Coded Compact Genetic Algorithms for Real Parameter Optimization Problems . Alphanumeric Journal , 8 (1) , 43-58 . DOI: 10.17093/alphanumeric.576919 [Link](https://dergipark.org.tr/en/pub/alphanumeric/issue/55603/576919)

## Usage 

Suppose the optimization problem is 

$$
\min f(x, y) = \text{abs}(x - 3.14159265) + \text{abs}(y - \exp{1})
$$

then the MCCGA searches for the minimum using 

```java
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
```
