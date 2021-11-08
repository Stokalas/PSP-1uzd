import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountModelTest {

    @Test
    void equals_sameId_true() {
        //Arrange
        AddressModel address1 = new AddressModel("LT", "Vilnius", "ggg");
        AccountModel account1 = new AccountModel(0, "AAA", "BBB", "37011",
                "aaa@a.lt", address1,"qwerty");
        AddressModel address2 = new AddressModel("LV", "Riga", "qqq");
        AccountModel account2 = new AccountModel(0, "DDD", "eee", "37099",
                "xyz@gmail.com", address2,"abcde");

        //Act
        boolean equals = account1.equals(account2);

        //Assert
        assertTrue(equals);
    }

    @Test
    void equals_differentId_false() {
        //Arrange
        AddressModel address1 = new AddressModel("LT", "Vilnius", "ggg");
        AccountModel account1 = new AccountModel(0, "AAA", "BBB", "37011",
                "aaa@a.lt", address1,"qwerty");
        AccountModel account2 = new AccountModel(1, "AAA", "BBB", "37011",
                "aaa@a.lt", address1,"qwerty");

        //Act
        boolean equals = account1.equals(account2);

        //Assert
        assertFalse(equals);
    }
}
