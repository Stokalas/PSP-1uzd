package lt.gybe.vu.mif;


import java.util.ArrayList;

public class PasswordChecker {

    private ArrayList<Character> specialSymbols;

    public ArrayList<Character> getSpecialSymbols() {
        return specialSymbols;
    }

    public PasswordChecker() {
        setDefaultSpecialSymbols();
    }

    public void setSpecialSymbols(ArrayList<Character> specialSymbols) {
        this.specialSymbols = specialSymbols;
    }

    public void setSpecialSymbols(char[] specialSymbols) {
        return;
    }

    public boolean isPasswordValid(String password) {
        return false;
    }

    public void addSpecialSymbol(char symbol) {
        return;
    }

    public void removeSpecialSymbol(char symbol) {
        return;
    }

    private void setDefaultSpecialSymbols() {
        return;
    }
}
