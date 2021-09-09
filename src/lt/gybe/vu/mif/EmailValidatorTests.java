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
    void validateEmail_EmptyString_False(String email) {
        assertFalse(emailValidator.validateEmail(email));
    }

    @Test
    void validateEmail_NullString_ThrowsException() {
        String nullString = null;
        assertThrows(IllegalArgumentException.class, () -> {
            emailValidator.validateEmail(nullString);
        });
    }

    @Test
    void validateEmail_WithoutRecipientName_False() {
        String email = "@xyz.com";
        assertFalse(emailValidator.validateEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email.xyz.com", "emailxyz.com", "email.com"})
    void validateEmail_WithoutAt_False(String email) {
        assertFalse(emailValidator.validateEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"e(mail@xyz.com", "name@email@-xyz.com", "ema\"il@xyz.com", "_name@email.com"})
    void validateEmail_WithInvalidSymbol_False(String email) {
        assertFalse(emailValidator.validateEmail(email));
    }

    @Test
    void validateEmail_WithoutDomain_False() {
        String withoutDomain = "email@.com";
        assertFalse(emailValidator.validateEmail(withoutDomain));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email@xyz!a.com", "email@-xyz.com", "email@192.168.2.4.com"})
    void validateEmail_IncorrectDomain_False(String email) {
        assertFalse(emailValidator.validateEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email@xyz", "email@xyzcom", "email@com"})
    void validateEmail_WithoutTLD_False(String email) {
        assertFalse(emailValidator.validateEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"name@example.!", "email@xyz.domain.1", "john@gmail.c0m"})
    void validateEmail_InvalidTLD_False(String email) {
        assertFalse(emailValidator.validateEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"name@example.com", "email@xyz.domain.com", "john@gmail.com"})
    void validateEmail_ValidEmail_True(String email) {
        assertTrue(emailValidator.validateEmail(email));
    }
}
