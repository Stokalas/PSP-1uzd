import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountValidatorTest {

    private AccountValidator validator;
    private ValidationSettings settings;

//    @BeforeAll
//    void settingsSetUp() {
//
//    }

    @BeforeEach
    void setUp() {
        List<String> validDomains = new ArrayList<>();
        validDomains.add("gmail.com");
        validDomains.add("outlook.com");

        List<PhoneValidationSettings> phoneValidation = new ArrayList<>();
        PhoneValidationSettings ps1 =  new PhoneValidationSettings("LT", "370",
                11, "86");
        phoneValidation.add(ps1);
        settings = new ValidationSettings(6, "!@#", validDomains, phoneValidation);
        validator = new AccountValidator(settings);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "aaa.lt", "aaa@asd.lt", "@gmail.com", "aaa@gmail"})
    void validateAccount_invalidEmail_neg1(String email) {
        AddressModel address = new AddressModel("LT", "Vilnius", "AAA");
        AccountModel acc = new AccountModel(0, "AAA", "BBB", "37068111111",
                email, address, "!Qwerty");
        assertEquals(-1, validator.validateAccount(acc));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "123", "871123453", "3703123"})
    void validateAccount_invalidPhone_neg2(String phoneNum) {
        AddressModel address = new AddressModel("LT", "Vilnius", "AAA");
        AccountModel acc = new AccountModel(0, "AAA", "BBB", phoneNum,
                "aaa@gmail.com", address, "!Qwerty");
        assertEquals(-2, validator.validateAccount(acc));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "123", "qwerty", "!@asd"})
    void validateAccount_invalidPassword_neg3(String pass) {
        AddressModel address = new AddressModel("LT", "Vilnius", "AAA");
        AccountModel acc = new AccountModel(0, "AAA", "BBB", "37068112123",
                "aaa@gmail.com", address, pass);
        assertEquals(-3, validator.validateAccount(acc));
    }

    @Test
    void validateAccount_allValid_0() {
        AddressModel address = new AddressModel("LT", "Vilnius", "AAA");
        AccountModel acc = new AccountModel(0, "AAA", "BBB", "37068112123",
                "aaa@gmail.com", address, "!Qwerty");
        assertEquals(0, validator.validateAccount(acc));
    }
}
