package psp.antra.uzduotis;

public class StringValidatorHelper {

    public static void ifStringNullThrow(String str, String title) {
        if (str == null) {
            throw new IllegalArgumentException("The " + title + " cannot be null.");
        }
    }

    public static boolean arrayContains(char[] charArray, char ch) {
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ch) {
                return true;
            }
        }
        return false;
    }
}
