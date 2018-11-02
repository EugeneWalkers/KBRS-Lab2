package encryption;

import java.math.BigInteger;

public class RSAEncryptor {

    private RSAEncryptor() {
    }

    public static String encryptRSA(final String originalText, final String keyRSA, final String moduleRSA) {
        StringBuilder encryptedText = new StringBuilder();
        originalText.toCharArray();
        BigInteger exponent = new BigInteger(keyRSA);
        BigInteger module = new BigInteger(moduleRSA);
        for (int i = 0; i < originalText.length(); i++) {
            Integer curCode = Character.codePointAt(originalText, i);
            BigInteger a = new BigInteger(curCode.toString());
            BigInteger newCode = a.modPow(exponent, module);
            encryptedText.append(Character.toChars(newCode.intValueExact()));
        }
        return encryptedText.toString();
    }

}
