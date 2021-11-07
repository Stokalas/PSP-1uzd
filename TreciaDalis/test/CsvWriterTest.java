import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CsvWriterTest {

    private static final String TEST_FILE_NAME = "test.csv";

    private CsvWriter csvWriter;

    @BeforeEach
    void setUp() {
        csvWriter = new CsvWriter(TEST_FILE_NAME);
    }

    @Test
    void writeEntries_expectedFile_true() throws Exception {
        //Arrange
        AddressModel address1 = new AddressModel("LT", "Vilnius", "ggg");
        AccountModel account1 = new AccountModel(0, "AAA", "BBB", "37011",
                "aaa@a.lt", address1,"qwerty");
        AddressModel address2 = new AddressModel("LV", "Riga", "qqq");
        AccountModel account2 = new AccountModel(5, "DDD", "eee", "37099",
                "xyz@gmail.com", address2,"abcde");

        String expected1 = "0,AAA,BBB,37011,aaa@a.lt,LT,Vilnius,ggg,qwerty";
        String expected2 = "5,DDD,eee,37099,xyz@gmail.com,LV,Riga,qqq,abcde";

        List<AccountModel> l = new ArrayList<>();
        l.add(account1);
        l.add(account2);

        csvWriter.writeEntries(l, false);

        //Assert
        List<String> rows = TestUtil.readFile(TEST_FILE_NAME);
        assertEquals(2, rows.size());
        assertEquals(rows.get(0), expected1);
        assertEquals(rows.get(1), expected2);

    }
}
