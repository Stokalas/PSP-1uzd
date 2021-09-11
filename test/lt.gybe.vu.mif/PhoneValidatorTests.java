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

    @Test
    void addCountryValidation_NullTitle_ThrowsException() {
        String title = null;
        int lengths[] = {9};
        String localPrefix = "8";
        String intPrefix = "371";

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @Test
    void addCountryValidation_NullLengths_ThrowsException() {
        String title = "Country";
        String localPrefix = "8";
        String intPrefix = "371";
        int[] lengths = null;

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @Test
    void addCountryValidation_EmptyLengths_ThrowsException() {
        String title = "Country";
        String localPrefix = "8";
        String intPrefix = "371";
        int[] lengths = {9};

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @Test
    void addCountryValidation_NullInternationalPrefix_ThrowsException() {
        //Arrange
        String title = "Country";
        int[] lengths = {9};
        String localPrefix = "8";
        String intPrefix = null;

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void addCountryValidation_EmptyInternationalPrefix_ThrowsException(String intPrefix) {
        //Arrange
        String title = "Country";
        int[] lengths = {9};
        String localPrefix = "8";

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @Test
    void addCountryValidation_NullLocalPrefix_ThrowsException() {
        //Arrange
        String title = "Country";
        int[] lengths = {9};
        String intPrefix = "371";
        String localPrefix = null;

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void addCountryValidation_EmptyLocalPrefix_ThrowsException(String localPrefix) {
        //Arrange
        String title = "Country";
        int[] lengths = {9};
        String intPrefix = "371";

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @Test
    void addCountryValidation_DuplicateTitle_ThrowsException() {
        //Arrange
        String title = "Country";
        int[] lengths = {9};
        String localPrefix = "8";
        String intPrefix = "371";

        phoneValidator.addCountryValidation(title, new int[] {10}, "7", "123");

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @Test
    void addCountryValidation_DuplicateLocalPrefix_ThrowsException() {
        //Arrange
        String title = "Country";
        int[] lengths = {9};
        String localPrefix = "8";
        String intPrefix = "371";

        phoneValidator.addCountryValidation("Existing", new int[] {10}, "8", "123");

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @Test
    void addCountryValidation_DuplicateInternationalPrefix_ThrowsException() {
        //Arrange
        String title = "Country";
        int[] lengths = {9};
        String localPrefix = "8";
        String intPrefix = "371";

        phoneValidator.addCountryValidation("Existing", new int[] {10}, "7", intPrefix);

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }


    @ParameterizedTest
    @ValueSource(strings = {"123", "12"})
    void addCountryValidation_LocalPrefixLongerEqualToLength_ThrowsException(String localPrefix) {
        //Arrange
        String title = "Imaginary";
        int lengths[] = {2};
        String intPrefix = "8";

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
           phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12"})
    void addCountryValidation_InternationalPrefixLongerEqualToLength_ThrowsException(String intPrefix) {
        //Arrange
        String title = "Imaginary";
        int lengths[] = {2};
        String localPrefix = "8";

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"+123", "1a2", "_"})
    void addCountryValidation_LocalPrefixNotOnlyNumbers_ThrowsException(String localPrefix) {
        //Arrange
        String title = "Imaginary";
        int lengths[] = {8};
        String intPrefix = "8";

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"+123", "1a2", "_"})
    void addCountryValidation_InternationalPrefixNotOnlyNumbers_ThrowsException(String intPrefix) {
        //Arrange
        String title = "Imaginary";
        int lengths[] = {8};
        String localPrefix = "8";

        //Act/Assert
        assertThrows(InvalidParameterException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

}
