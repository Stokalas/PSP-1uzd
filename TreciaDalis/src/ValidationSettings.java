import java.util.List;

public class ValidationSettings {

    private int passwordMinLen;
    private String passwordSpecialChars;

    private List<String> validDomains;

    private List<PhoneValidationSettings> phoneValidationSettings;

    public ValidationSettings(int passwordMinLen, String passSpecChars, List<String> validDomains,
                              List<PhoneValidationSettings> phoneValidationSettings) {
        if (passwordMinLen < 1) {
            throw new IllegalArgumentException("Minimum password length cannot be < 1!");
        }
        this.passwordMinLen = passwordMinLen;
        this.passwordSpecialChars = passSpecChars;
        this.validDomains = validDomains;
        this.phoneValidationSettings = phoneValidationSettings;
    }

    public int getPasswordMinLen() {
        return this.passwordMinLen;
    }

    public String getPasswordSpecialChars() {
        return this.passwordSpecialChars;
    }

    public List<String> getValidDomains() {
        return this.validDomains;
    }

    public List<PhoneValidationSettings> getPhoneValidationSettings() {
        return this.phoneValidationSettings;
    }
}
