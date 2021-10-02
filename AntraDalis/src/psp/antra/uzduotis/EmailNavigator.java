package psp.antra.uzduotis;

class EmailNavigator {

    EmailModel divideEmail(String email) {
        int atSymbolPos = locateAtSymbol(email);
        int lastDotPos = locateLastDot(email);
        int lastCharPos = email.length() - 1;
        EmailModel emailStruct = new EmailModel();

        //if @ not found or in last place - whole email is just local part
        if (atSymbolPos == -1 || atSymbolPos == lastCharPos) {
            emailStruct.localPart = email;
            return emailStruct;
        }

        emailStruct.localPart = email.substring(0, atSymbolPos);

        //if . is before @, or is in the last place, or is not found at all - all leftover is just domain
        if(lastDotPos == -1 || lastDotPos < atSymbolPos || lastDotPos == lastCharPos) {
            emailStruct.domain = email.substring(atSymbolPos + 1);
            return emailStruct;
        }

        emailStruct.domain = email.substring(atSymbolPos + 1, lastDotPos);
        emailStruct.tld = email.substring(lastDotPos + 1);

        return emailStruct;
    }

    int locateAtSymbol(String email) {
        for (int i = 1; i < email.length() - 1; i++) {
            if(email.charAt(i) == '@') {
                if(email.charAt(i-1) == '\"' && email.charAt(i+1) == '\"') {
                    continue;
                }
                return i;
            }
        }
        if (email.charAt(0) == '@') return 0;
        if (email.charAt(email.length()-1) == '@') return email.length()-1;

        return -1;
    }


    int locateLastDot(String email) {
        for (int i = email.length() - 1; i >= 0; i--) {
            if(email.charAt(i) == '.') {
                return i;
            }
        }
        return -1;
    }
}
