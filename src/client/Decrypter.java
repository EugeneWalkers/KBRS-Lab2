package client;

import javafx.util.Pair;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

class Decrypter {

    private final static String AES_TYPE = "AES/CFB/NoPadding";
    private final String IV = "blahblahblahblah";

    private String key;

    private Cipher cipher;

    String getKey() {
        return key;
    }

    void setKey(String key) {
        this.key = key;
    }

    private byte[] stringToBytes(final String string) {
        final String[] bytesInString = string.split("[\n,:]");
        final byte[] bytes = new byte[bytesInString.length];

        for (int i = 0; i < bytesInString.length; i++) {
            bytes[i] = Byte.valueOf(bytesInString[i]);
        }

        return bytes;
    }

    String decrypt(final String encryptedText) {

        if (key == null) {
            throw new NullPointerException("Key is not declared");
        }

        if (cipher == null){
            initCipher();
        }

        final byte[] bytes = stringToBytes(encryptedText);

        try {
            final byte[] decrypted = cipher.doFinal(bytes);

            return new String(decrypted);
        } catch (IllegalBlockSizeException
                | BadPaddingException e) {
            e.printStackTrace();

            return null;
        }
    }

    private void initCipher() {
        try {
            cipher = Cipher.getInstance(AES_TYPE);
            final Key key = new SecretKeySpec(this.key.getBytes(StandardCharsets.UTF_8), "AES");
            final AlgorithmParameterSpec init = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, key, init);

        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidAlgorithmParameterException
                | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    static class KeyDecrypter {
        static String decryptKey(final String encryptedKey, final Pair<BigInteger, BigInteger> rsa) {
            if (rsa == null) {
                throw new NullPointerException("RSA is not declared");
            }

            if (encryptedKey == null) {
                return null;
            }

            final String[] encryptedKeys = encryptedKey.split("\n");
            final StringBuilder decryptedKey = new StringBuilder();

            for (int i = 0; i < encryptedKeys.length; i++) {
                final BigInteger integer = new BigInteger(encryptedKeys[i]);
                final BigInteger newCode = integer.modPow(rsa.getKey(), rsa.getValue());
                decryptedKey.append((char) newCode.intValueExact());
            }

            return decryptedKey.toString();
        }
    }
}
