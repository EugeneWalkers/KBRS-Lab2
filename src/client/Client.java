package client;

public class Client {

    private final DataKeeper dataKeeper;
    private final Decrypter decrypter;
    private final StringBuilder encryptedKey;
    private RSA rsa;

    {
        dataKeeper = new DataKeeper();
        decrypter = new Decrypter();
        encryptedKey = new StringBuilder();
    }

    public void generateRSA() {
        rsa = RSA.RSAGenerator.generateRSA();
        System.out.println("Generated open RSA from client = " + rsa.getOpenRSA());
        System.out.println("Generated close RSA from client = " + rsa.getCloseRSA());
        System.out.println();
    }

    public void sendRSA() {
        dataKeeper.sendRSA(rsa.getOpenRSA());
    }

    public void decryptKey() {
        decrypter.setKey(
                Decrypter.KeyDecrypter.decryptKey(
                        encryptedKey.toString(),
                        rsa.getCloseRSA()
                )
        );
        System.out.println("Decrypted key = " + decrypter.getKey());
        System.out.println();
    }

    public void sendDataRequest(final String data) {
        dataKeeper.sendText(data);
    }

    public void receiveEncryptedKey() {
        encryptedKey.setLength(0);
        encryptedKey.append(dataKeeper.receiveEncryptedKey());
    }

    public void receiveEncryptedData() {
        dataKeeper.receiveEncryptedData();
    }

    public String decryptData() {
        return decrypter.decrypt(dataKeeper.receiveEncryptedData());
    }

}
