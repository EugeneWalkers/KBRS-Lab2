package client;

final class RSA {

    private StringBuilder openRSA;
    private StringBuilder closeRSA;

    {
        openRSA = new StringBuilder();
        closeRSA = new StringBuilder();
    }

    private RSA(final String openKey, final String closeKey){
        this.openRSA.append(openKey);
        this.closeRSA.append(closeKey);
    }

    static RSA generateRSA(){

        final String openRSA = generateOpenRSA();
        final String closeRSA = generateCloseRSA();

        return new RSA(openRSA, closeRSA);
    }

    private static String generateOpenRSA() {

        // TODO: write generator of open RSA

        return null;
    }

    private static String generateCloseRSA() {

        // TODO: write generator of closed RSA

        return null;
    }

    String getOpenRSA() {
        return openRSA.toString();
    }

    String getCloseRSA() {
        return closeRSA.toString();
    }
}
