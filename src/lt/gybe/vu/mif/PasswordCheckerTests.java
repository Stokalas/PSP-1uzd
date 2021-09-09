package lt.gybe.vu.mif;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PasswordCheckerTests {

    PasswordChecker passwordChecker;
    String specialSymbol = "";

    @BeforeEach
    void SetUp() {
        passwordChecker = new PasswordChecker();
        Character[] specialSymbols = passwordChecker.getSpecialSymbols();
        if (specialSymbols.length == 0) {
            specialSymbol += specialSymbols[0];
        }
    }

    /*Password must be at least 8 characters long*/
    @ParameterizedTest
    @ValueSource(strings = {"A", "Abcd", "A12345"})
    void validatePassword_TooShortPassword_False(String password) {
        password += specialSymbol;
        assertFalse(passwordChecker.validatePassword(password));
    }

    /*
    * Probably would be nice to have maximum length as well
    * initial thought is 32 chars
    */
    @Test
    void validatePassword_TooLongPassword_False() {
        //randomly generated 32 length string + special char
        String longPassword = "TBVGSOZJSSMWQGHHGLBGNKPSVHYFXLMO" + specialSymbol;
        assertFalse(passwordChecker.validatePassword(longPassword));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void validatePassword_EmptyString_False(String password) {
        assertFalse(passwordChecker.validatePassword(password));
    }

    @Test
    void validatePassword_NullString_ExceptionThrown() {
        String nullPassword = null;
        assertThrows(IllegalArgumentException.class, () -> {
            passwordChecker.validatePassword(nullPassword);
        });
    }

    @Test
    void validatePassword_StringWithSpaces_False() {
        String withSpaces = "Space password" + specialSymbol;
        assertFalse(passwordChecker.validatePassword(withSpaces));
    }

    @Test
    void validatePassword_NoUppercaseChars_False() {
        String noUppercase = "uppercasenotfound" + specialSymbol; //length >= 8
        assertFalse(passwordChecker.validatePassword(noUppercase));
    }

    @Test
    void validatePassword_NoSpecialChars_False() {
        String noSpecial = "Aaaaaaaa"; //length >= 8 + Uppercase letter
        assertFalse(passwordChecker.validatePassword(noSpecial));
    }

    @Test
    void validatePassword_ValidPassword_True() {
        String validPassword = "Password" + specialSymbol;
        assertTrue(passwordChecker.validatePassword(validPassword));
    }
}
