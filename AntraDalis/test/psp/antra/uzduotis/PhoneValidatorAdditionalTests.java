package psp.antra.uzduotis;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PhoneValidatorAdditionalTests {

    PhoneValidator phoneValidator;

    @BeforeEach
    void setUp() {
        phoneValidator = new PhoneValidator();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void addValidationRule_NullEmpty_countryCode_Throws(String countryCode) {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.addValidationRule(countryCode, "+370", 8);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void addValidationRule_NullEmpty_Prefix_Throws(String prefix) {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.addValidationRule("LT", prefix, 8);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -79})
    void addValidationRule_NonPositive_Length_Throws(int length) {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            phoneValidator.addValidationRule("LT", "+370", length);
        });
    }

    @Test
    void addValidationRule_Duplicate_False() {
        String countryCode = "IMG";
        String prefix = "+00";
        int length = 6;

        Assert.assertTrue(phoneValidator.addValidationRule(countryCode, prefix, length));
        Assert.assertFalse(phoneValidator.addValidationRule(countryCode, prefix, length));
    }

    @Test
    void isNumberValid_AddedRule_PassesValidation() {
        //Arrange
        phoneValidator.addValidationRule("RNG", "+55", 7);

        //Act/Assert
        Assert.assertTrue(phoneValidator.isNumberValid("RNG", "+551234567"));
    }

    @Test
    void isNumberValid_LTStartsWith8_PassesValidation() {
        Assert.assertTrue(phoneValidator.isNumberValid("LT", "868154358"));
    }

    @Test
    void getExistingValidationRules_CorrectRules() {
        //Arrange
        phoneValidator.resetValidationRules();
        phoneValidator.addValidationRule("IMG", "+00", 7);

        //Act
        String[] rules = phoneValidator.getExistingValidationRules();

        //Assert
        Assert.assertEquals(1, rules.length);
        Assert.assertEquals("Country Code - IMG prefix - +00 length without prefix - 7", rules[0]);
    }


}
