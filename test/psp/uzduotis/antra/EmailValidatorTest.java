package psp.uzduotis.antra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {
    EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
    }

    //Test checks if the email has special symbol
    @Test
    void EmailValidator_EmailHasSpecialSymbol(){
        assertTrue(emailValidator.specialSymbolRequirement("admin@gmail.com"));
    }

    //Test checks for invalid characters
    @Test
    void EmailValidator_NoUnsupportedCharacters(){
        assertTrue(emailValidator.supportedCharactersRequirement("admin@gmail.com"));
    }

    @Test
    void EmailValidator_HasUnsupportedCharacters(){
        assertFalse(emailValidator.supportedCharactersRequirement("a¿dmin@gmail.com"));
    }

    //Test verifies that the mail has the correct domain and TLD
    @Test
    void EmailValidator_CorrectDomain(){
        assertTrue(emailValidator.domainRequirement("admin@gmail.com"));
    }

    @Test
    void EmailValidator_WrongDomain(){
        assertFalse(emailValidator.domainRequirement("admin@email.1"));
    }

    @Test
    void EmailValidator_WrongTLD(){
        assertFalse(emailValidator.domainRequirement("admin@.lt"));
    }

}