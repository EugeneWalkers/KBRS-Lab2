package client;

class Decrypter {

//    private final static String AES_TYPE = "AES/CFB/NoPadding";
//    final String IV = "blahblahblahblah";
//    private Cipher cipher;

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    String decrypt(final String decryptedText) throws Exception {

        if (key == null) {
            throw new Exception("Key is not declared");
        }

//        try {
//            final Cipher cipher = Cipher.getInstance(AES_TYPE);
//            final Key key = new SecretKeySpec(this.key.getBytes(StandardCharsets.UTF_8), "AES");
//            final AlgorithmParameterSpec init = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
//            cipher.init(Cipher.DECRYPT_MODE, key, init);
//
//            final byte[] decrypted = cipher.doFinal(decryptedText.getBytes());
//
//            return new String(decrypted);
//        } catch (NoSuchAlgorithmException
//                | NoSuchPaddingException
//                | InvalidAlgorithmParameterException
//                | InvalidKeyException
//                | IllegalBlockSizeException
//                | BadPaddingException e) {
//            e.printStackTrace();
//
//            return null;
//        }

        return null;
    }

//    void initCipher(){
//        try {
//            cipher = Cipher.getInstance(AES_TYPE);
//            final Key key = new SecretKeySpec(this.key.getBytes(StandardCharsets.UTF_8), "AES");
//            final AlgorithmParameterSpec init = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
//            cipher.init(Cipher.DECRYPT_MODE, key, init);
//
//        } catch (NoSuchAlgorithmException
//                | NoSuchPaddingException
//                | InvalidAlgorithmParameterException
//                | InvalidKeyException e) {
//            e.printStackTrace();
//        }
//    }

    static class KeyDecrypter {
        static String decryptKey(final String encryptedKey, final String rsa) {
            if (rsa == null){
                throw new NullPointerException("RSA is not declared");
            }

            if (encryptedKey == null){
                return null;
            }

            return null;
        }
    }
}
