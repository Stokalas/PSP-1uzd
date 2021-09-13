package lt.gybe.vu.mif;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PhoneValidatorTests {

    String country = "Lithuania";
    String validPhoneNumber = "868000008";

    PhoneValidator phoneValidator;
    @BeforeEach
    void SetUp() {
        phoneValidator = new PhoneValidator();
        phoneValidator.addCountryValidation(country, new int[] {8}, "8", "370");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void isPhoneNumberValid_EmptyStringNumber_False(String phoneNumber) {
        assertFalse(phoneValidator.isPhoneNumberValid(phoneNumber, country));
    }

    @Test
    void isPhoneNumberValid_NullStringNumber_ThrowsException() {
        String phoneNumber = null;
        assertThrows(IllegalArgumentException.class, () -> {
           phoneValidator.isPhoneNumberValid(phoneNumber, country);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"86800000x", "+37060000000", "868 000 123"})
    void isPhoneNumberValid_WithOtherSymbolsNumber_False(String phoneNumber) {
        assertFalse(phoneValidator.isPhoneNumberValid(phoneNumber, country));
    }

    @ParameterizedTest
    @ValueSource(strings = {"8635", "37061234567890"})
    void isPhoneNumberValid_InvalidLengthNumber_False(String phoneNumber) {
        assertFalse(phoneValidator.isPhoneNumberValid(phoneNumber, country));
    }

    @ParameterizedTest
    @ValueSource(strings = {"lithuania", "LITHUANIA", "LiThUaNiA"})
    void isPhoneNumberValid_DifferentCapitalizationCountry_True(String country) {
        assertTrue(phoneValidator.isPhoneNumberValid(validPhoneNumber, country));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Estonia", "Lithunimia"})
    void isPhoneNumberValid_NotExistingCountry_ThrowsException(String notAddedCountry) {
        assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.isPhoneNumberValid(validPhoneNumber, notAddedCountry);
        });
    }

    @Test
    void isPhoneNumberValid_NullCountry_ThrowsException() {
        String nullCountry = null;
        assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.isPhoneNumberValid(validPhoneNumber, nullCountry);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"868000008", "37060000000"})
    void isPhoneNumberValid_ValidNumberCountry_True(String phoneNumber) {
        assertTrue(phoneValidator.isPhoneNumberValid(phoneNumber, country));
    }

    @Test
    void isPhoneNumberValid_AddCountryValidation_ValidNumberCountry_True() {
        //Arrange
        String title = "Latvia";
        int lengths[] = {9};
        String localPrefix = "8";
        String intPrefix = "371";
        phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);

        //Act/Assert
        assertTrue(phoneValidator.isPhoneNumberValid("37166789012", country));
    }

    @Test
    void addCountryValidation_NullTitle_ThrowsException() {
        String title = null;
        int lengths[] = {9};
        String localPrefix = "8";
        String intPrefix = "371";

        //Act/Assert
        assertThrows(IllegalArgumentException.class, () -> {
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
        assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @Test
    void addCountryValidation_EmptyLengths_ThrowsException() {
        String title = "Country";
        String localPrefix = "8";
        String intPrefix = "371";
        int[] lengths = {};

        //Act/Assert
        assertThrows(IllegalArgumentException.class, () -> {
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
        assertThrows(IllegalArgumentException.class, () -> {
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
        assertThrows(IllegalArgumentException.class, () -> {
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
        assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void addCountryValidation_EmptyLocalPrefix_DoesNotThrowException(String localPrefix) {
        //Arrange
        String title = "Country";
        int[] lengths = {9};
        String intPrefix = "371";

        //Act/Assert
        assertDoesNotThrow( () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void addCountryValidation_EmptyTitle_ThrowsException(String title) {
        //Arrange
        int[] lengths = {9};
        String localPrefix = "8";
        String intPrefix = "371";

        phoneValidator.addCountryValidation(title, new int[] {10}, "7", "123");

        //Act/Assert
        assertThrows(IllegalArgumentException.class, () -> {
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
        assertThrows(IllegalArgumentException.class, () -> {
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
        assertThrows(IllegalArgumentException.class, () -> {
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
        assertThrows(IllegalArgumentException.class, () -> {
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
        assertThrows(IllegalArgumentException.class, () -> {
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
        assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.addCountryValidation(title, lengths, localPrefix, intPrefix);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"lithuania", "LITHUANIA", "LiThUaNiA"})
    void addCountryValidation_ExistingTitle_OverwritesPrevious_True(String country) {
        //Arrange
        phoneValidator.addCountryValidation(country, new int[] {8}, "7", "370");

        //Act/Assert
        assertTrue(phoneValidator.isPhoneNumberValid("768000008", country));
    }

}
