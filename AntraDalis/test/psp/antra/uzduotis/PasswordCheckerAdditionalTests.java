package psp.antra.uzduotis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerAdditionalTests {

    PasswordChecker passwordChecker;
    @BeforeEach
    void setUp() {
        passwordChecker = new PasswordChecker();
    }

    //null + bad length
    @ParameterizedTest
    @NullSource
    void lenghtRequirement_Null(String password) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           passwordChecker.lenghtRequirement(password, 8);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    void lenghtRequirement_BadLength(int length) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            passwordChecker.lenghtRequirement("password", length);
        });
    }

    @ParameterizedTest
    @EmptySource
    void uppercaseCharactersRequirement_Empty(String password){
        assertFalse(passwordChecker.uppercaseCharactersRequirement(password));
    }

    @ParameterizedTest
    @NullSource
    void uppercaseCharactersRequirement_Null(String password) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            passwordChecker.uppercaseCharactersRequirement(password);
        });
    }

    @ParameterizedTest
    @EmptySource
    void specialCharacterRequirement_Empty(String password){
        assertFalse(passwordChecker.specialCharacterRequirement(password));
    }

    @ParameterizedTest
    @NullSource
    void specialCharacterRequirement_Null(String password) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            passwordChecker.specialCharacterRequirement(password);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"Password!", "!passWord", "pass!worD"})
    void isPasswordValid_Valid(String password) {
        //Arrange
        var length = 8;
        char[] specialChars = {'!'};
        passwordChecker.setSpecialCharacters(specialChars);

        //Act/Assert
        assertTrue(passwordChecker.isPasswordValid(password, length));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Abc!", "password!", "Password", ""})
    void isPasswordValid_Invalid(String password) {
        //Arrange
        var length = 8;
        char[] specialChars = {'!'};
        passwordChecker.setSpecialCharacters(specialChars);

        //Act/Assert
        assertFalse(passwordChecker.isPasswordValid(password, length));
    }

    @ParameterizedTest
    @NullSource
    void isPasswordValid_Null(String password) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            passwordChecker.isPasswordValid(password, 8);
        });
    }

}
