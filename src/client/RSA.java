package client;

import javafx.util.Pair;

import java.math.BigInteger;

final class RSA {

    private final Pair<BigInteger, BigInteger> openRSA;
    private final Pair<BigInteger, BigInteger> closeRSA;

    private RSA(final Pair<BigInteger, BigInteger> openRSA, final Pair<BigInteger, BigInteger> closeRSA) {
        this.openRSA = openRSA;
        this.closeRSA = closeRSA;
    }

    Pair<BigInteger, BigInteger> getOpenRSA() {
        return openRSA;
    }

    Pair<BigInteger, BigInteger> getCloseRSA() {
        return closeRSA;
    }

    static class RSAGenerator {

        private RSAGenerator() {
            // do not put any code here
        }

        static RSA generateRSA() {
            ProbablePrimeNumber p;
            ProbablePrimeNumber q;
            BigInteger r;
            BigInteger euler;
            BigInteger e; //public exponent
            BigInteger d;

            e = new BigInteger("257");
            do {
                p = new ProbablePrimeNumber();
                q = p.anotherOne();
                euler = p.getNumber().subtract(BigInteger.ONE).multiply(q.getNumber().subtract(BigInteger.ONE));
            } while (!ProbablePrimeNumber.areMutuallyPrime(e, euler));
            r = p.getNumber().multiply(q.getNumber());//n
            d = multInverse(euler, e);

            final Pair<BigInteger, BigInteger> openRSA = new Pair<>(e, r);
            final Pair<BigInteger, BigInteger> closeRSA = new Pair<>(d, r);

            return new RSA(openRSA, closeRSA);
        }

        private static BigInteger multInverse(BigInteger a, BigInteger b) {
            BigInteger d0 = new BigInteger(a.toString()), d1 = new BigInteger(b.toString());
            BigInteger x0 = BigInteger.ONE, x1 = BigInteger.ZERO;
            BigInteger y0 = BigInteger.ZERO, y1 = BigInteger.ONE;
            BigInteger q, d2, x2, y2;
            while (d1.compareTo(BigInteger.ONE) == 1) {
                q = d0.divide(d1);
                d2 = d0.mod(d1);
                x2 = x0.subtract(q.multiply(x1));
                y2 = y0.subtract(q.multiply(y1));
                d0 = d1;
                d1 = d2;
                x0 = x1;
                x1 = x2;
                y0 = y1;
                y1 = y2;
            }
            if (a.compareTo(b) > 0) {
                if (y1.compareTo(BigInteger.ZERO) < 0) {
                    y1 = y1.add(a);
                }
                return y1;
            } else {
                if (x1.compareTo(BigInteger.ZERO) < 0) {
                    x1 = x1.add(b);
                }
                return x1;
            }
        }
    }
}
