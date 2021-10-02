package psp.antra.uzduotis;

public class PasswordChecker {

    private char[] specialCharacters = {'-', '!', '@', '#', '$', '%', '^', '&'};

    /**
     * Sets list of characters to be used when checking
     * if password contains a special character
     * @param specialCharacters array of special characters
     */
    public void setSpecialCharacters(char [] specialCharacters) {
        this.specialCharacters = specialCharacters;
    }

    /**
     * Checks whether string is correct length
     * @param password password
     * @param length minimum length of a password
     * @return Returns true if password is >= length, false otherwise
     */
    public boolean lenghtRequirement(String password, int length) {
        StringValidatorHelper.ifStringNullThrow(password, "Password");

        if (length <= 0) {
            throw new IllegalArgumentException("The Password length cannot be 0 or less");
        }

        return password.length() >= length;
    }

    /**
     * Checks whether string contains uppercase character
     * @param password password
     * @return Returns true if password contains at least 1 uppercase character, false otherwise
     */
    public boolean uppercaseCharactersRequirement(String password) {
        StringValidatorHelper.ifStringNullThrow(password, "Password");

        char temp;

        for(int i=0; i < password.length(); i++) {
            temp = password.charAt(i);
            if (Character.isUpperCase(temp)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether string contains special character
     * @see PasswordChecker#setSpecialCharacters(char[])
     * @param password password
     * @return Returns true if password contains at least 1 of the special characters, false otherwise
     */
    public boolean specialCharacterRequirement(String password) {
        StringValidatorHelper.ifStringNullThrow(password, "Password");

        if (specialCharacters.length < 1) {
            return true;
        }

        for (int i = 0; i < password.length(); i++) {
            if (arrayContains(specialCharacters, password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean arrayContains(char[] charArray, char ch) {
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ch) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether password is valid
     * @param password password
     * @param length minimum length of the password
     * @return Returns true if the password is valid, false otherwise
     * @see PasswordChecker#lenghtRequirement(String, int)
     * @see PasswordChecker#specialCharacterRequirement(String)
     * @see PasswordChecker#uppercaseCharactersRequirement(String)
     */
    public boolean isPasswordValid(String password, int length) {
        var lengthReq = lenghtRequirement(password, length);
        var upperCaseReq = uppercaseCharactersRequirement(password);
        var specialCharReq = specialCharacterRequirement(password);

        return lengthReq && upperCaseReq && specialCharReq;
    }

}
