import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CsvReaderTest {
    private static final String TEST_FILE_NAME = "test.csv";
    private CsvReader reader;

    @BeforeAll
    static void initSetUp() throws IOException {
        String output = "0,AAA,BBB,37011,aaa@a.lt,LT,Vilnius,ggg,qwerty\n" +
                "5,DDD,eee,37099,xyz@gmail.com,LV,Riga,qqq,abcde";
        FileWriter fWriter = new FileWriter(TEST_FILE_NAME, false);
        fWriter.write(output);
        fWriter.close();
    }

    @BeforeEach
    void setUp(){
        reader = new CsvReader(TEST_FILE_NAME);
    }

    @Test
    void readData_returnCorrectAccountModels() {
        //Arrange
        AddressModel address1 = new AddressModel("LT", "Vilnius", "ggg");
        AccountModel expected1 = new AccountModel(0, "AAA", "BBB", "37011",
                "aaa@a.lt", address1,"qwerty");
        AddressModel address2 = new AddressModel("LV", "Riga", "qqq");
        AccountModel expected2 = new AccountModel(5, "DDD", "eee", "37099",
                "xyz@gmail.com", address2,"abcde");
        List<AccountModel> expectedList = new ArrayList<>();
        expectedList.add(expected1);
        expectedList.add(expected2);

        //Act
        List<AccountModel> l = reader.readData();

        //Assert
        assertEquals(2, l.size());
        for(int i = 0; i < l.size(); i++) {
            AccountModel a = l.get(i);
            AccountModel expected = expectedList.get(i);
            //These all asserts could be changed by overriding equals method in AccountModel
            //But for the "roleplaying" sake, let's say that because of business
            //Equals method override only compares by ids
            assertEquals(a.getId(), expected.getId());
            assertEquals(a.getName(), expected.getName());
            assertEquals(a.getSurname(), expected.getSurname());
            assertEquals(a.getEmail(), expected.getEmail());
            assertEquals(a.getPhoneNumber(), expected.getPhoneNumber());
            assertEquals(a.getAddress(), expected.getAddress());
            assertEquals(a.getPassword(), expected.getPassword());
        }

    }

    @Test
    void calculateNextId_correct() {
        int nextId = reader.calculateNextId();
        assertEquals(6, nextId);
    }
}
