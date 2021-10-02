package psp.antra.uzduotis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class PasswordCheckerTest {

    PasswordChecker passwordChecker;

    @BeforeEach
    void setUp() {
        passwordChecker = new PasswordChecker();
    }

    //Test checks the length of the password
    @Test
    void PasswordChecker_PasswordTooShort(){
        assertFalse(passwordChecker.lenghtRequirement("asdfgh", 9));
    }

    @Test
    void PasswordChecker_PasswordLengthIsCorrect(){
        assertTrue(passwordChecker.lenghtRequirement("qwertyuio", 9));
    }

    @Test
    void PasswordChecker_PasswordNotEntered(){
        assertFalse(passwordChecker.lenghtRequirement("", 9));
    }

    //Test checks for uppercase characters
    @Test
    void PasswordChecker_PasswordContainsUppercaseCharacters(){
        assertTrue(passwordChecker.uppercaseCharactersRequirement("Adminpass"));
    }

    @Test
    void PasswordChecker_PasswordNotContainsUppercaseCharacters(){
        assertFalse(passwordChecker.uppercaseCharactersRequirement("adminpass"));
    }

    //Test checks for a special character
    @Test
    void PasswordChecker_PasswordContainsSpecialCharacter(){
        assertTrue(passwordChecker.specialCharacterRequirement("admin-123"));
    }

    @Test
    void PasswordChecker_PasswordNotContainsSpecialCharacter(){
        assertFalse(passwordChecker.specialCharacterRequirement("adminn123"));
    }
}