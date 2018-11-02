package client;

import java.math.BigInteger;
import java.util.Random;

class ProbablePrimeNumber {
    private BigInteger number;
    private Random rand = new Random();

    ProbablePrimeNumber() {
        number = new BigInteger(1024, rand);
        if (number.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            number = number.subtract(BigInteger.ONE);
        }
        while (!this.isProbablePrime()) {
            number = number.add(BigInteger.valueOf(2));
            int length = number.toString().length();
            System.out.println(number);
            System.out.println(length);
            System.out.println("answer = " + this.isProbablePrime());
        }

    }

    BigInteger getNumber() {
        return number;
    }

    public static boolean areMutuallyPrime(BigInteger a, BigInteger b) {
        return euklid(a,b).equals(BigInteger.ONE);
    }

    private static BigInteger euklid(BigInteger a, BigInteger b) {
        BigInteger A = new BigInteger(a.toString());
        BigInteger B = new BigInteger(b.toString());
        BigInteger tmp;
        while (!B.equals(BigInteger.ZERO)) {
            tmp = A.mod(B);
            A = B;
            B = tmp;
        }
        return A;
    }

    private boolean isProbablePrime() {
        double k = Math.log(number.doubleValue());
        long s = 0;
        BigInteger number_1 = number.subtract(BigInteger.ONE);
        BigInteger t = new BigInteger(number_1.toString());
        while (t.mod(BigInteger.valueOf(2L)).equals(BigInteger.ZERO)) {
            t = t.divide(BigInteger.valueOf(2));
            s++;
        }

        boolean continue_i = false;
        for (double i = 0; i < k; i++) {
            rand.setSeed(2);
            BigInteger a = new BigInteger(1024, rand);//свидетель простоты для текущего раунда
            if (a.compareTo(number_1) >= 0) {
                a = a.subtract(number_1).add(BigInteger.valueOf(2));
            }

            BigInteger x = a.modPow(t, number);
            if (x.equals(BigInteger.ONE) || x.equals(number_1)) {
                continue;
            }

            for (long j = 0; j < s - 1; j++) {
                x = x.modPow(BigInteger.valueOf(2), number);
                if (x.equals(BigInteger.ONE)) {
                    return false;
                } else if (x.equals(number_1)) {
                    continue_i = true;
                    break;
                }
            }
            if (continue_i) {
                continue_i = false;
                continue;
            }
            return false;
        }
        return true;
    }

    ProbablePrimeNumber anotherOne() {
        ProbablePrimeNumber another = new ProbablePrimeNumber();
        while (number.equals(another.number)) {
            System.out.println("EQUALS!");
            another = new ProbablePrimeNumber();
        }
        return another;
    }

    public String toString() {
        return number.toString();
    }
}
