package lt.gybe.vu.mif;


public class PasswordChecker {

    private Character[] specialSymbols;

    public Character[] getSpecialSymbols() {
        return specialSymbols;
    }

    public PasswordChecker() {
        setDefaultSpecialSymbols();
    }

    public void setSpecialSymbols(Character[] specialSymbols) {
        this.specialSymbols = specialSymbols;
    }

    public boolean isPasswordValid(String password) {
        return false;
    }

    private void setDefaultSpecialSymbols() {
        //read from appSettings, not sure of equivalent in Java :)
        this.specialSymbols = new Character[] {'!', '@'};
    }

    //other private validation methods
}
