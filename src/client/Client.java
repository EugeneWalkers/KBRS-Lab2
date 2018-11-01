package client;

import java.io.IOException;

public class Client {

    private RSA rsa;
    private final DataKeeper dataKeeper;
    private final Decrypter decrypter;
    private final StringBuilder encryptedKey;

    {
        dataKeeper = new DataKeeper();
        decrypter = new Decrypter();
        encryptedKey = new StringBuilder();
    }

    public void generateRSA() {
        rsa = RSA.generateRSA();
    }

    public void sendRSA() {
        dataKeeper.sendRSA(rsa.getOpenRSA());
    }

    public void decryptKey() {
        decrypter.setKey(
                Decrypter.KeyDecrypter.decryptKey(
                        encryptedKey.toString(),
                        rsa.getOpenRSA()
                )
        );
    }

    public void sendDataRequest(final String data) {
        dataKeeper.sendText(data);
    }

    public void receiveEncryptedKey() {
        encryptedKey.setLength(0);
        encryptedKey.append(dataKeeper.receiveEncryptedKey());
    }

    public void receiveEncryptedText() {
        try {
            dataKeeper.receiveEncryptedText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
