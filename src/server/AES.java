package server;

import java.math.BigInteger;
import java.util.Random;

class AES {

    private StringBuilder aes;

    {
        aes = new StringBuilder();
    }

    static String generateAES() {
        final BigInteger integer = new BigInteger(128, new Random());

        return integer.toString();
    }

    String getAes() {
        return aes.toString();
    }

    void setAes(final String aes) {
        this.aes.setLength(0);
        this.aes.append(aes);
    }

}
