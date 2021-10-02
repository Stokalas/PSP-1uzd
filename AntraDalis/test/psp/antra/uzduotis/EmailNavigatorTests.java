package psp.antra.uzduotis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class EmailNavigatorTests {

    EmailNavigator emailNavigator;

    @BeforeEach
    void setUp() {
        emailNavigator = new EmailNavigator();
    }

    @Test
    void locateAtSymbol_AtExists() {
        assertEquals(3, emailNavigator.locateAtSymbol("abc@gmail.com"));
    }

    @Test
    void locateAtSymbol_NoAt() {
        assertEquals(-1, emailNavigator.locateAtSymbol("abcgmail.com"));
    }

    @Test
    void locateAtSymbol_FakeAt() {
        assertEquals(-1, emailNavigator.locateAtSymbol("abc\"@\"gmail.com"));
    }

    @Test
    void locateLastDot_Exists() {
        assertEquals(5, emailNavigator.locateLastDot("abc@g.com"));
    }

    @Test
    void locateLastDot_NotExists() {
        assertEquals(-1, emailNavigator.locateLastDot("abc@gcom"));
    }

    @Test
    void divideEmail_Equals() {
        EmailModel email = emailNavigator.divideEmail("abc@gmail.com");

        assertEquals("abc", email.localPart);
        assertEquals("gmail", email.domain);
        assertEquals("com", email.tld);
    }

    @Test
    void divideEmail_NullTld() {
        EmailModel email = emailNavigator.divideEmail("abc@gmailcom");

        assertEquals("abc", email.localPart);
        assertEquals("gmailcom", email.domain);
        assertEquals(null, email.tld);
    }

    @Test
    void divideEmail_NullDomain() {
        EmailModel email = emailNavigator.divideEmail("abcgmailcom");

        assertEquals("abcgmailcom", email.localPart);
        assertEquals(null, email.domain);
        assertEquals(null, email.tld);
    }

    @Test
    void divideEmail_Empty() {
        EmailModel email = emailNavigator.divideEmail("");

        assertEquals(null, email.localPart);
        assertEquals(null, email.domain);
        assertEquals(null, email.tld);
    }

}
