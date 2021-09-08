package lt.gybe.vu.mif;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PasswordCheckerTests {

    PasswordChecker passwordChecker;
    Character[] specialCharacters;

    @BeforeEach
    void SetUp() {
        passwordChecker = new PasswordChecker();
        specialCharacters = new Character[] {'!', '@', '#', '$'};
    }

    /*Password must be at least 8 characters long*/
    @Test
    void TestPasswordValidationTooShortPassword() {
        String shortPassword = "1234567";
        assertFalse(passwordChecker.ValidatePassword(shortPassword));
    }

    @Test
    void TestPasswordValidationEmptyString() {
        String emptyPassword = "";
        assertFalse(passwordChecker.ValidatePassword(emptyPassword));
    }

    @Test
    void TestPasswordValidationNullString() {
        String nullPassword = null;
        assertFalse(passwordChecker.ValidatePassword(nullPassword));
    }

    @Test
    void TestPasswordValidationStringWithSpaces() {
        String withSpaces = "space password";
        assertFalse(passwordChecker.ValidatePassword(withSpaces));
    }

    @Test
    void TestPasswordValidationNoUppercaseChars() {
        String noUppercase = "uppercasenotfound"; //length >= 8
        assertFalse(passwordChecker.ValidatePassword(noUppercase));
    }

    @Test
    void TestPasswordValidationNoSpecialChars() {
        String noSpecial = "Aaaaaaaa"; //length >= 8 + Uppercase letter
        assertFalse(passwordChecker.ValidatePassword(noSpecial));
    }

    @Test
    void TestPasswordValidationValidPassword() {
        String validPassword = "Password!";
        assertTrue(passwordChecker.ValidatePassword(validPassword));
    }


}
