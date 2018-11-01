import client.Client;
import server.Server;

public final class Main {

    public static void main(final String[] args){
        final Client client = new Client();
        final Server server = new Server();

        client.generateRSA();
        client.sendRSA();

        server.receiveRSA();
        server.generateAESKey();
        server.encryptKey();
        server.sendKeyResponse();

        client.receiveEncryptedKey();
        client.decryptKey();

        client.sendDataRequest("Some data to encrypt");

        server.sendDataResponse();

        client.receiveEncryptedText();
    }

}
