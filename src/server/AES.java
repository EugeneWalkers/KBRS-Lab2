package server;

class AES {

    private StringBuilder aes;

    {
        aes = new StringBuilder();
    }

    static String generateAES(final String rsa) {
        if (rsa == null) {
            throw new NullPointerException("RSA is not declared");
        }

        // TODO: write aes

        return null;
    }

    String getAes() {
        return aes.toString();
    }

    void setAes(final String aes) {
        this.aes.setLength(0);
        this.aes.append(aes);
    }

}
