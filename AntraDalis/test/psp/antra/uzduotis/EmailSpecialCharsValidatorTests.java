package psp.antra.uzduotis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.*;

class EmailSpecialCharsValidatorTests {

    EmailSpecialCharsValidator specialCharsValidator;

    @BeforeEach
    void setUp() {
        specialCharsValidator = new EmailSpecialCharsValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a!bc@gmail.com", "a?bc@gmail.com"})
    void isAnyValidSymbol_SpecialSymbol_1(String email) {
        assertEquals(1, specialCharsValidator.isAnyValidSymbol(email,1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a\"@\"bc@gmail.com", "a\":\"bc@gmail.com"})
    void isAnyValidSymbol_RestrictedSymbol_3(String email) {
        assertEquals(3, specialCharsValidator.isAnyValidSymbol(email,1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a\"\\ \"bc@gmail.com", "a\"\\\"\"bc@gmail.com"})
    void isAnyValidSymbol_ExtraRestrictedSymbol_4(String email) {
        assertEquals(4, specialCharsValidator.isAnyValidSymbol(email,1));
    }

    @Test
    void isAnyValidSymbol_Dot_1() {
        assertEquals(1, specialCharsValidator.isAnyValidSymbol("a.bc@gmail.com",1));
    }

    @Test
    void isAnyValidSymbol_InvalidDot_0() {
        assertEquals(0, specialCharsValidator.isAnyValidSymbol(".abc@gmail.com",0));
    }

    @Test
    void isAnyValidSymbol_TwoDots_0() {
        assertEquals(0, specialCharsValidator.isAnyValidSymbol("a..bc@gmail.com",1));
    }


}
