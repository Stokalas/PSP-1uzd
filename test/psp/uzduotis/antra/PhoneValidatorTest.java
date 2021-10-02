package psp.uzduotis.antra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

//    @Test
//    void PhoneValidator_OtherCountryPhoneNumberLengthCorrect(){
//        assertEquals("The phone number length is correct", phoneValidator.prefixRequirement("LT", "+37061234578"));
//    }

    @Test
    void PhoneValidator_OtherCountryPhoneNumberPrefixWrong(){
        assertEquals("The phone prefix does not match country code", phoneValidator.prefixRequirement("PL", "+37061234578"));
    }

//    @Test
//    void PhoneValidator_OtherCountryPhoneNumberLengthWrong(){
//        assertEquals("The phone number is too long", phoneValidator.prefixRequirement("PL", "+370612345787878787"));
//    }
}