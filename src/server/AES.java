package server;

import validators.RSAValidator;

class AES {

    private StringBuilder aes;

    {
        aes = new StringBuilder();
    }

    static String generateAES(final String rsa) {
        if (rsa == null) {
            throw new NullPointerException("RSA is not declared");
        }

        if (!RSAValidator.isRSAValid(rsa)){
            throw new IllegalArgumentException("RSA is not valid");
        }

        // TODO: write aes

        return null;
    }

    public String getAes() {
        return aes.toString();
    }

    public void setAes(final String aes) {
        this.aes.setLength(0);
        this.aes.append(aes);
    }

}
