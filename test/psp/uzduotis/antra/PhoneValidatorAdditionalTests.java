package psp.uzduotis.antra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.*;

public class PhoneValidatorAdditionalTests {

    PhoneValidator phoneValidator;

    @BeforeEach
    void setUp() {
        phoneValidator = new PhoneValidator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void addValidationRule_NullEmpty_countryCode_Throws(String countryCode) {
        assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.addValidationRule(countryCode, "+370", 8);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void addValidationRule_NullEmpty_Prefix_Throws(String prefix) {
        assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.addValidationRule("LT", prefix, 8);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -79})
    void addValidationRule_NonPositive_Length_Throws(int length) {
        assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.addValidationRule("LT", "+370", length);
        });
    }

    @Test
    void addValidationRule_Duplicate_False() {
        String countryCode = "IMG";
        String prefix = "+00";
        int length = 6;

        assertTrue(phoneValidator.addValidationRule(countryCode, prefix, length));
        assertFalse(phoneValidator.addValidationRule(countryCode, prefix, length));
    }

    @Test
    void isNumberValid_AddedRule_PassesValidation() {
        //Arrange
        phoneValidator.addValidationRule("RNG", "+55", 7);

        //Act/Assert
        assertTrue(phoneValidator.isNumberValid("RNG", "+551234567"));
    }

    @Test
    void isNumberValid_LTStartsWith8_PassesValidation() {
        assertTrue(phoneValidator.isNumberValid("LT", "868154358"));
    }

    @Test
    void getExistingValidationRules_CorrectRules() {
        //Arrange
        phoneValidator.resetValidationRules();
        phoneValidator.addValidationRule("IMG", "+00", 7);

        //Act
        String[] rules = phoneValidator.getExistingValidationRules();

        //Assert
        assertEquals(1, rules.length);
        assertEquals("Country Code - IMG prefix - +00 length without prefix - 7", rules[0]);
    }


}
