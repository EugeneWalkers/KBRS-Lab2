import client.Client;
import server.Server;

import java.util.Arrays;

public final class Main {

    private final static String TEXT_TO_ENCRYPT = "Some data to encrypt";

    public static void main(final String[] args){
        final Client client = new Client();
        final Server server = new Server();

        System.out.println("Client generates RSA...");

        client.generateRSA();
        System.out.println("Client sends open RSA to the server.");
        client.sendRSA();

        System.out.println("Receiving RSA by server.");
        server.receiveRSA();
        System.out.println("Server generates AES key within RSA...");
        server.generateAESKey();
        System.out.println("Server encrypts it's key for the client...");
        server.encryptKey();
        System.out.println("Server sends encrypted RSA to the client.");
        server.sendKeyResponse();

        System.out.println("Client receives encrypted key.");
        client.receiveEncryptedKey();
        System.out.println("Client decrypts it");
        client.decryptKey();

        System.out.println("Client sends data to the server to encrypt.");
        System.out.println("Data to encrypt:");
        System.out.println(TEXT_TO_ENCRYPT);
        System.out.println();
        client.sendDataRequest(TEXT_TO_ENCRYPT);

        System.out.println("Server sends response.");
        server.sendDataResponse();

        System.out.println("Client receives encrypted data.");
        client.receiveEncryptedData();

        System.out.println("Client decrypts it.");
        System.out.println("Decrypted data:");
        System.out.println(client.decryptData());
    }

}
