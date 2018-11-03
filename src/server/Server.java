package server;

import javafx.util.Pair;

import java.io.IOException;
import java.math.BigInteger;

public class Server {

    private final DataKeeper dataKeeper;
    private final Encrypter encrypter;
    private final AES aes;
    private Pair<BigInteger, BigInteger> rsa;

    {
        dataKeeper = new DataKeeper();
        encrypter = new Encrypter();
        aes = new AES();
        rsa = new Pair<>(null, null);
    }

    public void generateAESKey(){
        aes.setAes(AES.generateAES());
        System.out.println("AES key = " + aes.getAes());
        System.out.println();
    }

    public void sendDataResponse(){
        try {
            final String data = dataKeeper.receiveData();
            final String encryptedData = encrypter.encrypt(data);
            dataKeeper.sendEncryptedData(encryptedData);
            System.out.println("Response:");
            System.out.println(encryptedData);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveRSA(){
        final String[] rsaInString = dataKeeper.receiveRSA().split("\n");
        rsa = new Pair<>(new BigInteger(rsaInString[0]), new BigInteger(rsaInString[1]));
    }

    public void encryptKey(){
        encrypter.setEncryptedKey(Encrypter.KeyEncryptor.encryptKey(encrypter.getKey(), rsa));
        System.out.println("Encrypted key = " + encrypter.getEncryptedKey());
        System.out.println();
    }

    public void sendKeyResponse(){
        dataKeeper.sendEncryptedKey(encrypter.getEncryptedKey());
    }

}
