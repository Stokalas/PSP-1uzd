import com.vu.psp.evaldasg.validators.EmailValidator;
import com.vu.psp.evaldasg.validators.PasswordChecker;
import com.vu.psp.evaldasg.validators.PhoneValidator;

public class AccountValidator {

    private final EmailValidator emailValidator;
    private final PhoneValidator phoneValidator;
    private final PasswordChecker passwordChecker;

    private final ValidationSettings validationSettings;

    public AccountValidator(ValidationSettings validationSettings) {
        this.validationSettings = validationSettings;
        this.emailValidator = new EmailValidator();
        this.phoneValidator = new PhoneValidator();
        this.passwordChecker = new PasswordChecker(validationSettings.getPasswordMinLen());

        setUpEmailValidator();
        setUpPhoneValidator();
        setUpPasswordChecker();
    }

    public int validateAccount(AccountModel accountModel) {
        if (!validateEmail(accountModel.getEmail())) {
            return -1;
        }
        String country = accountModel.getAddress().getCountry();
        if(!validatePhoneNumber(accountModel.getPhoneNumber(), country)) {
            return -2;
        }

        if(!validatePassword(accountModel.getPassword())) {
            return -3;
        }

        return accountModel.getId();
    }

    private boolean validateEmail(String email) {
        return emailValidator.validate(email);
    }

    private boolean validatePhoneNumber(String phoneNum, String country) {
        return phoneValidator.validate(phoneNum, country);
    }

    private boolean validatePassword(String password) {
        return passwordChecker.validate(password);
    }

    private void setUpEmailValidator() {
        for (var domain : validationSettings.getValidDomains()) {
            emailValidator.setСorrectDomain(domain);
        }
    }

    private void setUpPhoneValidator() {
        for (var v : validationSettings.getPhoneValidationSettings()) {
            phoneValidator.setPrefix(v.getCountry(), v.getPrefix(), v.getLength());

            String prefixToChange = v.getPrefixToChange();
            if (prefixToChange != null) {
                phoneValidator.setPrefixToChange(v.getCountry(), prefixToChange);
            }
        }
    }

    private void setUpPasswordChecker() {
        passwordChecker.setCustomCharacters(validationSettings.getPasswordSpecialChars());
    }
}
