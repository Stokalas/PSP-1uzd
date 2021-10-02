package psp.antra.uzduotis;

public class PhoneValidator {

    private final String COUNTRY_CODE_NOT_FOUND = "Provided country code is not found!";
    private final String PREFIX_CORRECT = "The phone prefix is correct";
    private final String PREFIX_INCORRECT = "The phone prefix does not match country code";
    private final String LENGTH_CORRECT = "The phone number length is correct";
    private final String LENGTH_TOO_SHORT = "The phone number is too short";
    private final String LENGTH_TOO_LONG =  "The phone number is too long";

    private PhoneValidationRule[] validationRules;

    public PhoneValidator() {
        setDefaultValidationRules();
    }

    /**
     * Checks whether the phone number is valid
     * @param countryCode country code
     * @param number number
     * @return Returns true if phone number is valid, false otherwise
     * @see PhoneValidator#numbersRequirement(String)
     * @see PhoneValidator#lengthRequirement(String, String)
     * @see PhoneValidator#prefixRequirement(String, String)
     */
    public boolean isNumberValid(String countryCode, String number) {
        var isNumbersReq = numbersRequirement(number);
        var isPrefixReq = prefixRequirement(countryCode, number) == PREFIX_CORRECT;
        var isLengthReq = lengthRequirement(countryCode, number) == LENGTH_CORRECT;

        return isNumbersReq && isPrefixReq && isLengthReq;
    }

    /**
     * Checkes whether String number only numbers (can start with a +)
     * @param number number
     * @return Returns true if contains only number, false otherwise
     */
    public boolean numbersRequirement(String number) {
        StringValidatorHelper.ifStringNullThrow(number, "Phone Number");
        if (number.length() < 1) {
            return false;
        }

        int startIndex = number.startsWith("+") ? 1 : 0;
        for (int i = startIndex; i < number.length(); i++) {
            char temp = number.charAt(i);
            if(!Character.isDigit(temp)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether the prefix matches validation rule of the country
     * @param country country code
     * @param number number
     * @return Returns true if prefix matches validation rule, false otherwise
     * @see PhoneValidator#addValidationRule(String, String, int)
     */
    public String prefixRequirement(String country, String number) {
        StringValidatorHelper.ifStringNullThrow(number, "Phone Number");
        StringValidatorHelper.ifStringNullThrow(country, "Country Code");

        String prefix = null;
        for (PhoneValidationRule val : validationRules) {
            if (val.countryCode.equals(country)) {
                prefix = val.prefix;
                break;
            }
        }

        if (prefix == null) {
            return COUNTRY_CODE_NOT_FOUND;
        }

        number = convertLocalToGlobal(number);

        if (number.startsWith(prefix)) {
            return PREFIX_CORRECT;
        }
        return PREFIX_INCORRECT;
    }

    /**
     * Checks whether the number length matches validation rule of the country
     * @param country country code
     * @param number number
     * @return Returns true if number length matches validation rule, false otherwise
     * @see PhoneValidator#addValidationRule(String, String, int)
     */
    public String lengthRequirement(String country, String number) {
        StringValidatorHelper.ifStringNullThrow(number, "Phone Number");
        StringValidatorHelper.ifStringNullThrow(country, "Country Code");

        int length = 0;
        for (PhoneValidationRule val : validationRules) {
            if (val.countryCode.equals(country)) {
                length = val.length + val.prefix.length();
                break;
            }
        }

        if (length < 1) {
            return COUNTRY_CODE_NOT_FOUND;
        }

        number = convertLocalToGlobal(number);
        if (number.length() == length) {
            return LENGTH_CORRECT;
        }
        else if (number.length() > length) {
            return LENGTH_TOO_LONG;
        }
        return LENGTH_TOO_SHORT;
    }

    /**
     * Adds validation rule to the list
     * @param countryCode country code; e.g. - "LT"
     * @param prefix prefix of the number; e.g. - "+370"
     * @param length length of the country phone number without prefix
     * @return Returns false if validation rule for the country already exists, true if the rule was added
     */
    public boolean addValidationRule(String countryCode, String prefix, int length) {
        if (countryCode == null || countryCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Country code cannot be empty or null");
        }

        if (prefix == null || prefix.trim().isEmpty()) {
            throw new IllegalArgumentException("Number prefix cannot be empty or null");
        }

        if (length < 1) {
            throw new IllegalArgumentException("Length cannot be less than 1");
        }

        for (PhoneValidationRule val : validationRules) {
            if (val.countryCode == countryCode) {
                return false;
            }
        }

        PhoneValidationRule temp = new PhoneValidationRule(countryCode, prefix, length);
        validationRules = addToArray(validationRules, temp);

        return true;
    }

    /**
     * Removes all existing validation rules
     */
    public void resetValidationRules() {
        validationRules = new PhoneValidationRule[] {};
    }

    /**
     * Returns array of validation rules
     * @return String array of validation rules
     */
    public String[] getExistingValidationRules() {
        int size = validationRules.length;
        if (size < 1) {
            return new String[0];
        }

        String[] rules = new String[size];
        for (int i = 0; i < size; i++) {
            rules[i] = validationRules[i].toString();
        }

        return rules;
    }

    private void setDefaultValidationRules() {
        PhoneValidationRule lithuania = new PhoneValidationRule("LT", "+370", 8);
        PhoneValidationRule poland = new PhoneValidationRule("PL", "+48", 9);

        validationRules = new PhoneValidationRule[] {lithuania, poland};
    }

    private PhoneValidationRule[] addToArray(PhoneValidationRule[] array, PhoneValidationRule item) {
        int oldLength = array.length;
        int i;
        PhoneValidationRule[] temp = new PhoneValidationRule[oldLength+1];

        for (i = 0; i < oldLength; i++) {
            temp[i] = array[i];
        }

        temp[i] = item;

        return temp;
    }

    private String convertLocalToGlobal(String number) {
        if(number.charAt(0) == '8') {
            return "+370" + number.substring(1);
        }
        return number;
    }

}
