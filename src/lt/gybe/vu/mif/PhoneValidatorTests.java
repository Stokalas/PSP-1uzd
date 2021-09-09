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
    void validatePhoneNumber_EmptyString_False(String phoneNumber) {
        assertFalse(phoneValidator.validatePhoneNumber(phoneNumber));
    }

    @Test
    void validatePhoneNumber_NullString_ThrowsException() {
        String phoneNumber = null;
        assertThrows(InvalidParameterException.class, () -> {
           phoneValidator.validatePhoneNumber(phoneNumber);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"86800000x", "+37060000000"})
    void validatePhoneNumber_WithOtherSymbols_False(String phoneNumber) {
        assertFalse(phoneValidator.validatePhoneNumber(phoneNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"8635", "37061234567890"})
    void validatePhoneNumber_InvalidLength_False(String phoneNumber) {
        assertFalse(phoneValidator.validatePhoneNumber(phoneNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"868000008", "37060000000"})
    void validatePhoneNumber_ValidNumber_True(String phoneNumber) {
        assertTrue(phoneValidator.validatePhoneNumber(phoneNumber));
    }

    @Test
    void validatePhoneNumber_AddCountryValidation_ValidNumber_True() {
        String title = "Latvia";
        int length = 8;
        String countryCode = "371";
        phoneValidator.addCountryValidation(title, length, countryCode);
        assertTrue(phoneValidator.validatePhoneNumber("37166789012"));
    }

}
