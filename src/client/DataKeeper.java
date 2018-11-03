package client;

import javafx.util.Pair;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.List;

class DataKeeper {

    private final File rsa;
    private final File dataToSend;
    private final File dataToReceive;
    private final File keyEncrypted;

    {
        rsa = new File("rsa.txt");

        try {
            rsa.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataToSend = new File("data.txt");

        try {
            dataToSend.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataToReceive = new File("dataEncrypted.txt");
        keyEncrypted = new File("keyEncrypted.txt");
    }

    String receiveEncryptedData(){
        return readTextFromFile(dataToReceive);
    }

    void sendText(final String text){
        writeTextToFile(text, dataToSend);
    }

    void sendRSA(final Pair<BigInteger, BigInteger> rsa) {
        final String rsaString = rsa.getKey().toString() + System.lineSeparator() + rsa.getValue().toString();
        writeTextToFile(rsaString, this.rsa);
    }

    private void writeTextToFile(final String text, final File file){
        try (final PrintWriter writer = new PrintWriter(file)) {
            writer.write(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    String receiveEncryptedKey() {
        return readTextFromFile(keyEncrypted);
    }

    private String readTextFromFile(final File file){
        try {
            final List<String> listOfKeys = Files.readAllLines(file.toPath());
            final StringBuilder builder = new StringBuilder();

            for (int i=0; i<listOfKeys.size(); i++){
                builder.append(listOfKeys.get(i) + "\n");
            }

            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
