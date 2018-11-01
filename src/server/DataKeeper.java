package server;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

class DataKeeper {

    private final File rsa;
    private final File dataToSend;
    private final File dataToReceive;
    private final File keyEncrypted;

    {
        rsa = new File("rsa.txt");

        dataToSend = new File("dataEncrypted.txt");

        try {
            dataToSend.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataToReceive = new File("data.txt");

        keyEncrypted = new File("keyEncrypted.txt");

        try {
            keyEncrypted.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendEncryptedData(final String encryptedText) {
        sendDataToFile(encryptedText, dataToSend);
    }

    void sendEncryptedKey(final String encryptedKey) {
        sendDataToFile(encryptedKey, rsa);
    }

    private void sendDataToFile(final String data, final File file){
        try (final PrintWriter writer = new PrintWriter(file)) {
            writer.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    String receiveData() throws IOException {
        final List<String>  dataInList = Files.readAllLines(dataToReceive.toPath());
        final StringBuilder dataBuilder = new StringBuilder();

        for (int i=0; i<dataInList.size(); i++){
            dataBuilder.append(dataInList.get(i));
        }

        return dataBuilder.toString();
    }

    String receiveRSA() {
        try (final BufferedReader reader = new BufferedReader(new FileReader(rsa))) {
            final String RSA = reader.readLine();
            reader.close();

            return RSA;
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

}
