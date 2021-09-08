package lt.gybe.vu.mif;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTests {

    EmailValidator emailValidator;

    @BeforeEach
    void SetUp() {
        emailValidator = new EmailValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void TestEmailValidationEmptyString(String email) {
        assertFalse(emailValidator.ValidateEmail(email));
    }

    @Test
    void TestEmailValidationNullString() {
        String nullString = null;
        assertFalse(emailValidator.ValidateEmail(nullString));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email.xyz.com", "emailxyz.com", "email.com"})
    void TestEmailValidation_WithoutAt(String email) {
        assertFalse(emailValidator.ValidateEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"e(mail@xyz.com", "name@email@-xyz.com", "ema\"il@xyz.com"})
    void TestEmailValidation_WithInvalidSymbol(String email) {
        assertFalse(emailValidator.ValidateEmail(email));
    }

    @Test
    void TestEmailValidation_WithoutDomain() {
        String withoutDomain = "email@.com";
        assertFalse(emailValidator.ValidateEmail(withoutDomain));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email@xyz!a.com", "email@-xyz.com", "email@192.168.2.4.com"})
    void TestEmailValidation_IncorrectDomain(String email) {
        assertFalse(emailValidator.ValidateEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email@xyz", "email@xyzcom", "email@com"})
    void TestEmailValidation_WithoutTLD(String email) {
        assertFalse(emailValidator.ValidateEmail(email));
    }
}
