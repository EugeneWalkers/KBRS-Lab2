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

        // TODO: put you any needed values here
        // ...

        private RSAGenerator() {
            // do not put any code here
        }

        static RSA generateRSA() {

            // TODO: put you code here

            Pair<BigInteger, BigInteger> openRSA = null;
            Pair<BigInteger, BigInteger> closeRSA = null;

            return new RSA(openRSA, closeRSA);
        }
    }
}
