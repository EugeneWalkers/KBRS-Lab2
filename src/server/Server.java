package server;

import java.io.IOException;

public class Server {

    private final DataKeeper dataKeeper;
    private final Encrypter encrypter;
    private final AES aes;
    private final StringBuilder rsa;

    {
        dataKeeper = new DataKeeper();
        encrypter = new Encrypter();
        aes = new AES();
        rsa = new StringBuilder();
    }

    public void generateAESKey(){
        aes.setAes(AES.generateAES(rsa.toString()));
    }

    public void sendDataResponse(){
        try {
            final String data = dataKeeper.receiveData();
            final String encryptedData = encrypter.encrypt(data);
            dataKeeper.sendEncryptedData(encryptedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveRSA(){
        rsa.setLength(0);
        rsa.append(dataKeeper.receiveRSA());
    }

    public void encryptKey(){
        encrypter.setKey(Encrypter.KeyEncrypter.cryptKey(rsa.toString()));
    }

    public void sendKeyResponse(){
        dataKeeper.sendEncryptedKey(encrypter.getKey());
    }

}
