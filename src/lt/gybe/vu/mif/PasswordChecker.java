package lt.gybe.vu.mif;


public class PasswordChecker {

    private Character[] specialSymbols;

    public Character[] getSpecialSymbols() {
        return specialSymbols;
    }

    public PasswordChecker() {
        readAndSetSpecialSymbols();
    }

//    public void setSpecialSymbols(Character[] specialSymbols) {
//        this.specialSymbols = specialSymbols;
//    }

    public boolean validatePassword(String password) {
        return false;
    }

    private void readAndSetSpecialSymbols() {
        //read from appSettings, not sure of equivalent in Java :)
        this.specialSymbols = new Character[] {'!', '@'};
    }

    //other private validation methods
}
