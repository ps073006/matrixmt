/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.rng.sampling.distribution;

import org.apache.commons.rng.UniformRandomProvider;

/**
 * Utility class implementing Cheng's algorithms for beta distribution sampling.
 *
 * <blockquote>
 * <pre>
 * R. C. H. Cheng,
 * "Generating beta variates with nonintegral shape parameters",
 * Communications of the ACM, 21, 317-322, 1978.
 * </pre>
 * </blockquote>
 *
 * <p>Sampling uses {@link UniformRandomProvider#nextDouble()}.</p>
 *
 * @since 1.0
 */
public class ChengBetaSampler
    extends SamplerBase
    implements SharedStateContinuousSampler {
    /** 1/2. */
    private static final double ONE_HALF = 1d / 2;
    /** 1/4. */
    private static final double ONE_QUARTER = 1d / 4;

    /** First shape parameter. */
    private final double alphaShape;
    /** Second shape parameter. */
    private final double betaShape;
    /** Underlying source of randomness. */
    private final UniformRandomProvider rng;

    /**
     * Creates a sampler instance.
     *
     * @param rng Generator of uniformly distributed random numbers.
     * @param alpha Distribution first shape parameter.
     * @param beta Distribution second shape parameter.
     * @throws IllegalArgumentException if {@code alpha <= 0} or {@code beta <= 0}
     */
    public ChengBetaSampler(UniformRandomProvider rng,
                            double alpha,
                            double beta) {
        super(null);
        if (alpha <= 0) {
            throw new IllegalArgumentException("alpha is not strictly positive: " + alpha);
        }
        if (beta <= 0) {
            throw new IllegalArgumentException("beta is not strictly positive: " + beta);
        }
        this.rng = rng;
        alphaShape = alpha;
        betaShape = beta;
    }

    /**
     * @param rng Generator of uniformly distributed random numbers.
     * @param source Source to copy.
     */
    private ChengBetaSampler(UniformRandomProvider rng,
                             ChengBetaSampler source) {
        super(null);
        this.rng = rng;
        alphaShape = source.alphaShape;
        betaShape = source.betaShape;
    }

    /** {@inheritDoc} */
    @Override
    public double sample() {
        final double a = Math.min(alphaShape, betaShape);
        final double b = Math.max(alphaShape, betaShape);

        if (a > 1) {
            return algorithmBB(a, b);
        }
        // This method declares parameters (a, b).
        // The algorithm is deliberately invoked with reversed parameters.
        return algorithmBC(b, a);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Cheng Beta deviate [" + rng.toString() + "]";
    }

    /** {@inheritDoc} */
    @Override
    public SharedStateContinuousSampler withUniformRandomProvider(UniformRandomProvider rng) {
        return new ChengBetaSampler(rng, this);
    }

    /**
     * Computes one sample using Cheng's BB algorithm, when \( \alpha \) and
     * \( \beta \) are both larger than 1.
     *
     * @param a \( \min(\alpha, \beta) \).
     * @param b \( \max(\alpha, \beta) \).
     * @return a random sample.
     */
    private double algorithmBB(double a,
                               double b) {
        final double alpha = a + b;
        final double beta = Math.sqrt((alpha - 2) / (2 * a * b - alpha));
        final double gamma = a + 1 / beta;

        double r;
        double w;
        double t;
        do {
            final double u1 = rng.nextDouble();
            final double u2 = rng.nextDouble();
            final double v = beta * (Math.log(u1) - Math.log1p(-u1));
            w = a * Math.exp(v);
            final double z = u1 * u1 * u2;
            r = gamma * v - 1.3862944;
            final double s = a + r - w;
            if (s + 2.609438 >= 5 * z) {
                break;
            }

            t = Math.log(z);
            if (s >= t) {
                break;
            }
        } while (r + alpha * (Math.log(alpha) - Math.log(b + w)) < t);

        w = Math.min(w, Double.MAX_VALUE);

        return equals(a, alphaShape) ? w / (b + w) : b / (b + w);
    }

    /**
     * Computes one sample using Cheng's BB algorithm, when at least one of
     * \( \alpha \) or \( \beta \) is smaller than 1.
     *
     * @param a \( \min(\alpha, \beta) \).
     * @param b \( \max(\alpha, \beta) \).
     * @return a random sample.
     */
    private double algorithmBC(double a,
                               double b) {
        final double alpha = a + b;
        final double beta = 1 / b;
        final double delta = 1 + a - b;
        final double k1 = delta * (0.0138889 + 0.0416667 * b) / (a * beta - 0.777778);
        final double k2 = 0.25 + (0.5 + 0.25 / delta) * b;

        double w;
        while (true) {
            final double u1 = rng.nextDouble();
            final double u2 = rng.nextDouble();
            final double y = u1 * u2;
            final double z = u1 * y;
            if (u1 < ONE_HALF) {
                if (0.25 * u2 + z - y >= k1) {
                    continue;
                }
            } else {
                if (z <= ONE_QUARTER) {
                    final double v = beta * (Math.log(u1) - Math.log1p(-u1));
                    w = a * Math.exp(v);
                    break;
                }

                if (z >= k2) {
                    continue;
                }
            }

            final double v = beta * (Math.log(u1) - Math.log1p(-u1));
            w = a * Math.exp(v);
            if (alpha * (Math.log(alpha) - Math.log(b + w) + v) - 1.3862944 >= Math.log(z)) {
                break;
            }
        }

        w = Math.min(w, Double.MAX_VALUE);

        return equals(a, alphaShape) ? w / (b + w) : b / (b + w);
    }

    /**
     * @param a Value.
     * @param b Value.
     * @return {@code true} if {@code a} is equal to {@code b}.
     */
    private boolean equals(double a,
                           double b) {
        return Math.abs(a - b) <= Double.MIN_VALUE;
    }

    /**
     * Creates a new Beta distribution sampler.
     *
     * @param rng Generator of uniformly distributed random numbers.
     * @param alpha Distribution first shape parameter.
     * @param beta Distribution second shape parameter.
     * @return the sampler
     * @throws IllegalArgumentException if {@code alpha <= 0} or {@code beta <= 0}
     */
    public static SharedStateContinuousSampler of(UniformRandomProvider rng,
                                                  double alpha,
                                                  double beta) {
        return new ChengBetaSampler(rng, alpha, beta);
    }
}
