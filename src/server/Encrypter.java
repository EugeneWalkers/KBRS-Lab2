package server;

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

class Encrypter {

    private final static String AES_TYPE = "AES/CFB/NoPadding";

    private String iv;

    private String key;
    private String encryptedKey;

    Encrypter() {
        this("keyykeyykeyykeyy", "blahblahblahblah");
    }

    Encrypter(final String key) {
        this(key, "");
    }

    Encrypter(final String key, final String iv) {
        this.key = key;
        this.iv = iv;
    }

    public String getEncryptedKey() {
        return encryptedKey;
    }

    public void setEncryptedKey(String encryptedKey) {
        this.encryptedKey = encryptedKey;
    }

    String getKey() {
        return key;
    }

    void setKey(String key) {
        this.key = key;
    }

    String encrypt(final String text) {
        if (key == null) {
            throw new NullPointerException("Key is not declared");
        }

        try {
            final Cipher cipher = Cipher.getInstance(AES_TYPE);
            final Key key = new SecretKeySpec(this.key.getBytes(StandardCharsets.UTF_8), "AES");
            final AlgorithmParameterSpec init = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, key, init);
            final byte[] encrypted = cipher.doFinal(text.getBytes());

            final StringBuilder bytesAsIntBuilder = new StringBuilder();
            for (int i = 0; i < encrypted.length; i++) {
                byte someByte = encrypted[i];
                bytesAsIntBuilder.append((int) someByte + (i == encrypted.length - 1? "": ":"));
            }

            return bytesAsIntBuilder.toString();
        } catch (NoSuchAlgorithmException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | NoSuchPaddingException
                | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    static class KeyEncryptor {
        static String encryptKey(final String key, final Pair<BigInteger, BigInteger> rsa) {
            if (rsa == null) {
                throw new NullPointerException("RSA is null");
            }

            final StringBuilder encryptedText = new StringBuilder();

            for (int i = 0; i < key.length(); i++) {
                int curCode = Character.codePointAt(key, i);
                final BigInteger a = new BigInteger(String.valueOf(curCode));
                final BigInteger newCode = a.modPow(rsa.getKey(), rsa.getValue());
                encryptedText.append(newCode.toString()).append(i == key.length() - 1 ? "" : System.lineSeparator());
            }

            return encryptedText.toString();
        }
    }

}

