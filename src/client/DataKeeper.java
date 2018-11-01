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

    void receiveEncryptedText() throws IOException {
        final List<String> textInList = Files.readAllLines(dataToReceive.toPath());
        final StringBuilder textBuilder = new StringBuilder();

        for (int i = 0; i < textInList.size(); i++) {
            textBuilder.append(textInList.get(i));
        }

        writeTextToFile(textBuilder.toString(), dataToReceive);
    }

    void sendText(final String text){
        writeTextToFile(text, dataToSend);
    }

    void sendRSA(final Pair<BigInteger, BigInteger> rsa) {
        final String rsaString = rsa.getKey().toString() + "\n" + rsa.getValue().toString();
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
        return readOneLineTextFromFile(keyEncrypted);
    }

    private String readOneLineTextFromFile(final File file){
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            final String key = reader.readLine();
            reader.close();

            return key;
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }


}
