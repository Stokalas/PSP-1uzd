package psp.antra.uzduotis;

class EmailSpecialCharsValidator {

    private final char[] validSymbols = new char[] {33, 35, 36, 37, 38, 39, 42, 43, 45, 47, 61, 63, 94, 95, 96,
            123, 124, 125, 126};

    private final char[] validSymbolsWithRestrictions = new char[] {40, 41, 44, 58, 59, 60, 62, 64, 91, 93};
    private final char[] validSymbolsWithExtraRest = new char[] {32, 34, 92};

    public EmailSpecialCharsValidator() {

    }

    int isAnyValidSymbol(String email, int pos) {
        char ch = email.charAt(pos);
        if(ch == '"') {
            pos += 1;

            if (isValidRestrictedSymbol(email, pos)) {
                return 3;
            }

            if (isValidExtraRestrictedSymbol(email, pos + 1)) {
                return 4;
            }

            return 0;
        }

        if (isValidSpecialSymbol(ch) || isValidDot(email, pos)){
            return 1;
        }

        return 0;
    }

    private boolean isValidSpecialSymbol(char ch) {
        return StringValidatorHelper.arrayContains(validSymbols, ch);
    }

    private boolean isValidRestrictedSymbol(String email, int position) {
        if (position == email.length() - 1) {
            return false;
        }

        char ch = email.charAt(position);

        char prev = email.charAt(position - 1);
        char next = email.charAt(position + 1);

        return StringValidatorHelper.arrayContains(validSymbolsWithRestrictions, ch) && prev == '\"' && next == '\"';
    }

    private boolean isValidDot(String email, int position) {
        if (position == 0 || position == email.length() - 1) {
            return false;
        }
        char ch = email.charAt(position);

        if (ch == '.' && email.charAt(position + 1) != '.') {
            return true;
        }

        return false;
    }

    private boolean isValidExtraRestrictedSymbol(String email, int position) {
        if (position < 2 || position == email.length() - 1) {
            return false;
        }

        char ch = email.charAt(position);

        char prev = email.charAt(position - 1);
        char prev2 = email.charAt(position - 2);
        char next = email.charAt(position + 1);

        return StringValidatorHelper.arrayContains(validSymbolsWithExtraRest, ch) && prev2 == '\"' && next == '\"' && prev == '\\';
    }
}
