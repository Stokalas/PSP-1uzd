package lt.gybe.vu.mif;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailValidatorTests {

    EmailValidator emailValidator;

    @BeforeEach
    void SetUp() {
        emailValidator = new EmailValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void isEmailValid_EmptyString_False(String email) {
        assertFalse(emailValidator.isEmailValid(email));
    }

    @Test
    void isEmailValid_NullString_ThrowsException() {
        String nullString = null;
        assertThrows(IllegalArgumentException.class, () -> {
            emailValidator.isEmailValid(nullString);
        });
    }

    @Test
    void isEmailValid_WithoutRecipientName_False() {
        String email = "@xyz.com";
        assertFalse(emailValidator.isEmailValid(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email.xyz.com", "emailxyz.com", "email.com"})
    void isEmailValid_WithoutAt_False(String email) {
        assertFalse(emailValidator.isEmailValid(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"e(mail@xyz.com", "name@email@-xyz.com", "ema\"il@xyz.com", "_name@email.com"})
    void isEmailValid_WithInvalidSymbol_False(String email) {
        assertFalse(emailValidator.isEmailValid(email));
    }

    @Test
    void isEmailValid_WithoutDomain_False() {
        String withoutDomain = "email@.com";
        assertFalse(emailValidator.isEmailValid(withoutDomain));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email@xyz!a.com", "email@-xyz.com", "email@192.168.2.4.com"})
    void isEmailValid_IncorrectDomain_False(String email) {
        assertFalse(emailValidator.isEmailValid(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email@xyz", "email@xyzcom", "email@com"})
    void isEmailValid_WithoutTLD_False(String email) {
        assertFalse(emailValidator.isEmailValid(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"name@example.!", "email@xyz.domain.1", "john@gmail.c0m"})
    void isEmailValid_InvalidTLD_False(String email) {
        assertFalse(emailValidator.isEmailValid(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"name@example.com", "email@xyz.domain.com", "j43@domainsample.co.uk"})
    void isEmailValid_ValidEmail_True(String email) {
        assertTrue(emailValidator.isEmailValid(email));
    }
}
