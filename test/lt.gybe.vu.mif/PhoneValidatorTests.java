package lt.gybe.vu.mif;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneValidatorTests {

    PhoneValidator phoneValidator;
    @BeforeEach
    void SetUp() {
        phoneValidator = new PhoneValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void isPhoneNumberValid_EmptyString_False(String phoneNumber) {
        assertFalse(phoneValidator.isPhoneNumberValid(phoneNumber));
    }

    @Test
    void isPhoneNumberValid_NullString_ThrowsException() {
        String phoneNumber = null;
        assertThrows(InvalidParameterException.class, () -> {
           phoneValidator.isPhoneNumberValid(phoneNumber);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"86800000x", "+37060000000", "868 000 123"})
    void isPhoneNumberValid_WithOtherSymbols_False(String phoneNumber) {
        assertFalse(phoneValidator.isPhoneNumberValid(phoneNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"8635", "37061234567890"})
    void isPhoneNumberValid_InvalidLength_False(String phoneNumber) {
        assertFalse(phoneValidator.isPhoneNumberValid(phoneNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"868000008", "37060000000"})
    void isPhoneNumberValid_ValidNumber_True(String phoneNumber) {
        assertTrue(phoneValidator.isPhoneNumberValid(phoneNumber));
    }

    @Test
    void isPhoneNumberValid_AddCountryValidation_ValidNumber_True() {
        //Arrange
        String title = "Latvia";
        int lengths[] = {9};
        String localPrefix = "8";
        String intPrefix = "371";
        phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);

        //Act/Assert
        assertTrue(phoneValidator.isPhoneNumberValid("37166789012"));
    }

}
