package server;

class Encrypter {

//    private final static String AES_TYPE = "AES/CFB/NoPadding";
//
//    final String keyString = "keyykeyykeyykeyy";
//    final String IV = "blahblahblahblah";

    private String key;

    String getKey() {
        return key;
    }

    void setKey(String key) {
        this.key = key;
    }

    String encrypt(final String text) {
//        try {
//            final Cipher cipher = Cipher.getInstance(AES_TYPE);
//            final Key key = new SecretKeySpec(keyString.getBytes(StandardCharsets.UTF_8), "AES");
//            final AlgorithmParameterSpec init = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
//            cipher.init(Cipher.ENCRYPT_MODE, key, init);
//            final byte[] encrypted = cipher.doFinal(text.getBytes());
//            return new String(encrypted);
//        } catch (NoSuchAlgorithmException
//                | InvalidKeyException
//                | InvalidAlgorithmParameterException
//                | NoSuchPaddingException
//                | BadPaddingException
//                | IllegalBlockSizeException e) {
//            e.printStackTrace();
//            return null;
//        }

        if (key == null) {
            throw new NullPointerException("Key is not declared");
        }

        // TODO: write encryptor

        return null;
    }

    static class KeyEncrypter {
        static String cryptKey(final String rsa) {
            if (rsa == null) {
                throw new NullPointerException("RSA is null");
            }

            // TODO: write encrypter for key within rsa

            return null;
        }
    }

}

