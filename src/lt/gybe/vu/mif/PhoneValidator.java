package lt.gybe.vu.mif;

public class PhoneValidator {

    public boolean isPhoneNumberValid(String phoneNumber, String country) {
        return false;
    }

    /**
     * This method is used to add new country entry, so that phone number format
     * of this country would pass the validation.
     * @param title Country name or code;
     * @param lengths Lengths of the phone number with local prefix
     * @param localPrefix Local prefix of the phone number
     * @param internationalPrefix International prefix, to be used if phone number with local one provided
     */
    public void addCountryValidation(String title, int[] lengths, String localPrefix, String internationalPrefix) {
        return;
    }

}
