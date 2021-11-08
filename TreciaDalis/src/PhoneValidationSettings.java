public class PhoneValidationSettings {
    private String country;
    private String prefix;
    private int length;
    private String prefixToChange;

    public PhoneValidationSettings(String country, String prefix, int length, String prefixToChange) {
        this.country = country;
        this.prefix = prefix;
        this.length = length;
        this.prefixToChange = prefixToChange;
    }

    public PhoneValidationSettings(String country, String prefix, int length) {
        this(country, prefix, length, null);
    }

    public String getCountry() {
        return country;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getLength() {
        return length;
    }

    public String getPrefixToChange() {
        return prefixToChange;
    }
}
