package psp.uzduotis.antra;


public class EmailValidator {

    private EmailSpecialCharsValidator specialCharsValidator;
    private EmailNavigator emailNavigator;

    public EmailValidator() {
        specialCharsValidator = new EmailSpecialCharsValidator();
        emailNavigator = new EmailNavigator();
    }

    /**
     * Check if an email is valid by checking if it has @ symbol, contains only valid for an email symbols,
     * has valid domain, including TLD, and is correct length
     * @param email
     * @return
     */
    public boolean isEmailValid(String email) {
        var specialSymbolReq = specialSymbolRequirement(email);
        var supportedCharsReq = supportedCharactersRequirement(email);
        var domainReq = domainRequirement(email);
        var lengthReq = isEmailLengthCorrect(email);

        return specialSymbolReq && supportedCharsReq && domainReq && lengthReq;
    }

    /**
     * Checks whether email has @ symbol that is also not a part of local part of the email
     * @param email email
     * @return Returns true if email has @ symbol, false otherwise
     */
    public boolean specialSymbolRequirement(String email) {
        StringValidatorHelper.ifStringNullThrow(email, "Email");

        if (email.length() < 1) {
            return false;
        }

        if(emailNavigator.locateAtSymbol(email) == -1) {
            return false;
        }

        return true;
    }

    /**
     * Checks according to the rules of local part if whole email contains forbidden characters
     * @param email email
     * @return Return true if no such characters found, false otherwise
     */
    public boolean supportedCharactersRequirement(String email) {
        StringValidatorHelper.ifStringNullThrow(email, "Email");

        if (email.length() == 0) {
            return true;
        }

        int atSymbolPos = emailNavigator.locateAtSymbol(email);

        char temp;
        for (int i = 0; i < email.length(); i++) {
            temp = email.charAt(i);
            if (!Character.isDigit(temp) && !Character.isLetter(temp) && i != atSymbolPos) {
                int skip = specialCharsValidator.isAnyValidSymbol(email, i);
                if(skip == 0) {
                    return false;
                }
                i += (skip - 1);
            }
        }

        return true;
    }


    /**
     * Checks if whole domain part of an email is valid
     * @param email email
     * @return Returns true if domain, including TLD is valid, false otherwise
     */
    public boolean domainRequirement(String email) {
        StringValidatorHelper.ifStringNullThrow(email, "Email");

        if (email.length() < 1) {
            return false;
        }

        EmailModel divided = emailNavigator.divideEmail(email);
        String domain = divided.domain;
        String tld = divided.tld;

        return validateDomain(domain) && validateTld(tld);
    }


    private boolean validateDomain(String domain) {
        //if null or empty not valid
        if (domain == null || domain.trim().isEmpty()) {
            return false;
        }

        //if starts or ends with a dot - not valid
        if (domain.startsWith(".") || domain.endsWith(".")) {
            return false;
        }

        //valid domain symbols - letters, digits, hyphens and dots
        for (char ch : domain.toCharArray()) {
            if (!Character.isLetter(ch) && !Character.isDigit(ch) && ch != '-' && ch !='.') {
                return false;
            }
        }
        return true;
    }

    private boolean validateTld(String tld) {
        //if null or empty not valid
        if (tld == null || tld.trim().isEmpty()) {
            return false;
        }

        //valid domain symbols - letters, digits, hyphens and dots
        for (char ch : tld.toCharArray()) {
            if (!Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmailLengthCorrect(String email) {
        int length = email.length();
        return length > 0 && length <= 254;
    }

}


