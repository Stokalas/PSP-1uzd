package lt.gybe.vu.mif;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordCheckerTests {

    PasswordChecker passwordChecker;
    String specialSymbol = "";

    @BeforeEach
    void SetUp() {
        passwordChecker = new PasswordChecker();
        ArrayList<Character> specialSymbols = passwordChecker.getSpecialSymbols();
        specialSymbol += specialSymbols.get(0);
    }

    /*Password must be at least 8 characters long*/
    @ParameterizedTest
    @ValueSource(strings = {"A", "Abcd", "A12345"})
    void isPasswordValid_TooShortPassword_False(String password) {
        password += specialSymbol;
        assertFalse(passwordChecker.isPasswordValid(password));
    }

    /*
    * Probably would be nice to have maximum length as well
    * initial thought is 32 chars
    */
    @Test
    void isPasswordValid_TooLongPassword_False() {
        //randomly generated 32 length string + special char
        String longPassword = "TBVGSOZJSSMWQGHHGLBGNKPSVHYFXLMO" + specialSymbol;
        assertFalse(passwordChecker.isPasswordValid(longPassword));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void isPasswordValid_EmptyString_False(String password) {
        assertFalse(passwordChecker.isPasswordValid(password));
    }

    @Test
    void isPasswordValid_NullString_ExceptionThrown() {
        String nullPassword = null;
        assertThrows(IllegalArgumentException.class, () -> {
            passwordChecker.isPasswordValid(nullPassword);
        });
    }

    @Test
    void isPasswordValid_NoUppercaseChars_False() {
        String noUppercase = "uppercasenotfound" + specialSymbol; //length >= 8
        assertFalse(passwordChecker.isPasswordValid(noUppercase));
    }

    @Test
    void isPasswordValid_NoSpecialChars_False() {
        String noSpecial = "Aaaaaaaa"; //length >= 8 + Uppercase letter
        assertFalse(passwordChecker.isPasswordValid(noSpecial));
    }

    @Test
    void isPasswordValid_ValidPassword_True() {
        String validPassword = "Password" + specialSymbol;
        assertTrue(passwordChecker.isPasswordValid(validPassword));
    }

    @Test
    void addSpecialSymbol_DuplicateSymbol_DoesNotThrow() {
        //Arrange
        char specialSymbol = '%';
        passwordChecker.addSpecialSymbol(specialSymbol);

        //Act/Assert
        assertDoesNotThrow( () -> {
            passwordChecker.addSpecialSymbol(specialSymbol);
        });
    }

    @Test
    void addSpecialSymbol_DuplicateSymbol_DoesNotChangeArrayListLength() {
        //Arrange
        char specialSymbol = '%';
        passwordChecker.addSpecialSymbol(specialSymbol);
        int amountOfChars = passwordChecker.getSpecialSymbols().size();

        //Act
        passwordChecker.addSpecialSymbol(specialSymbol);

        //Assert
        assertEquals(amountOfChars, passwordChecker.getSpecialSymbols().size());
    }

    @ParameterizedTest
    @ValueSource(chars = {'!', '@', '#'})
    void removeSpecialSymbol_ExistingChar_RemovesRightOne(Character charToBeRemoved) {
        //Arrange
        ArrayList<Character> localCharArray = new ArrayList<Character>(){
            {
                add('!');
                add('@');
                add('#');
            }
        };
        passwordChecker.setSpecialSymbols(localCharArray);
        localCharArray.remove(charToBeRemoved);

        //Act
        passwordChecker.removeSpecialSymbol(charToBeRemoved);

        //Assert
        assertTrue(localCharArray.equals(passwordChecker.getSpecialSymbols()));

    }

    @Test
    void setSpecialSymbol_CharArray_SetsRightSymbols() {
        //Arrange
        char[] charArray = {'!', '@'};
        ArrayList<Character> expectedArrayList = new ArrayList<Character>() {
            {
                add('!');
                add('@');
            }
        };

        //Act
        passwordChecker.setSpecialSymbols(charArray);

        //Assert
        assertTrue(expectedArrayList.equals(passwordChecker.getSpecialSymbols()));
    }
}
