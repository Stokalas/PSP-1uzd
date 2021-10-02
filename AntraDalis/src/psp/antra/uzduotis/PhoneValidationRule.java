package psp.antra.uzduotis;

class PhoneValidationRule {
    String countryCode;
    String prefix;
    int length;

    public PhoneValidationRule(String countryCode, String prefix, int length) {
        this.countryCode = countryCode;
        this.prefix = prefix;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Country Code - " + countryCode + " prefix - " + prefix + " length without prefix - " + length;
    }
}
