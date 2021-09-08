package lt.gybe.vu.mif;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneValidatorTests {

    PhoneValidator phoneValidator;
    @BeforeEach
    void SetUp() {
        phoneValidator = new PhoneValidator();
    }
}
