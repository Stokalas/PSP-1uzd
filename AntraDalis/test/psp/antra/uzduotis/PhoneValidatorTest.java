package psp.antra.uzduotis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class PhoneValidatorTest {

    PhoneValidator phoneValidator;

    @BeforeEach
    void setUp() {
        phoneValidator = new PhoneValidator();
    }

    //Test checks for characters other than numbers
    @Test
    void PhoneValidator_NoCharactersOtherThanNumbers(){
        assertTrue(phoneValidator.numbersRequirement("+37061234567"));
    }

    //Test checks for new validations
    @Test
    void PhoneValidator_OtherCountryPhoneNumberPrefixCorrect(){
        assertEquals("The phone prefix is correct", phoneValidator.prefixRequirement("LT", "+37061234578"));
    }

    @Test
    void PhoneValidator_OtherCountryPhoneNumberLengthCorrect(){
        assertEquals("The phone number length is correct", phoneValidator.lengthRequirement("LT", "+37061234578"));
    }

    @Test
    void PhoneValidator_OtherCountryPhoneNumberPrefixWrong(){
        assertEquals("The phone prefix does not match country code", phoneValidator.prefixRequirement("PL", "+37061234578"));
    }

    @Test
    void PhoneValidator_OtherCountryPhoneNumberLengthWrong(){
        assertEquals("The phone number is too long", phoneValidator.lengthRequirement("PL", "+370612345787878787"));
    }
}