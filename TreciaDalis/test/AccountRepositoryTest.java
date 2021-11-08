import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class AccountRepositoryTest {
    private final String TEST_FILE_NAME = "test.csv";

    private AccountRepository repository;

    @BeforeEach
    void setUp() throws Exception{
        String output = "0,AAA,BBB,37011,aaa@a.lt,LT,Vilnius,ggg,qwerty\n" +
                "5,DDD,eee,37099,xyz@gmail.com,LV,Riga,qqq,abcde\n";
        FileWriter fWriter = new FileWriter(TEST_FILE_NAME, false);
        fWriter.write(output);
        fWriter.close();
        repository = new AccountRepository(TEST_FILE_NAME);
    }

    @Test
    void create_creates_AccountModel() throws Exception {
        AddressModel address = new AddressModel("DE", "Berlin", "a");
        AccountModel newAcc = new AccountModel("W", "E", "123",
                "a@a.com", address, "aa");

        var created = repository.create(newAcc);
        assertNotNull(created);
        assertEquals("W", created.getName());
        List<String> rows = TestUtil.readFile(TEST_FILE_NAME);
        assertEquals(3, rows.size());
    }

    @Test
    void edit_notExists_null() {
        AddressModel address = new AddressModel("DE", "Berlin", "a");
        AccountModel editingAcc = new AccountModel(3,"W", "E", "123",
                "a@a.com", address, "aa");

        AccountModel acc = repository.edit(editingAcc);
        assertNull(acc);
    }

    @Test
    void edit_Exists_changes_AccountModel() throws Exception{
        AddressModel address = new AddressModel("DE", "Berlin", "a");
        AccountModel editingAcc = new AccountModel(5,"W", "E", "123",
                "a@a.com", address, "aa");
        String expected = "5,W,E,123,a@a.com,DE,Berlin,a,aa";

        AccountModel acc = repository.edit(editingAcc);
        assertNotNull(acc);
        assertEquals(5, acc.getId());

        List<String> rows = TestUtil.readFile(TEST_FILE_NAME);
        assertEquals(2, rows.size());
        assertEquals(expected, rows.get(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 5})
    void findById_exists_AccountModel(int id) {
        AccountModel acc = repository.findById(id);

        assertNotNull(acc);
        assertEquals(id, acc.getId());
    }

    @Test
    void findById_notExists_null() {
        AccountModel acc = repository.findById(3);
        assertNull(acc);
    }

    @Test
    void delete_exists_deletes_true() throws Exception {
        String expected = "0,AAA,BBB,37011,aaa@a.lt,LT,Vilnius,ggg,qwerty";
        boolean deleted = repository.delete(5);
        assertTrue(deleted);

        List<String> rows = TestUtil.readFile(TEST_FILE_NAME);
        assertEquals(1, rows.size());
        assertEquals(expected, rows.get(0));
    }

    @Test
    void delete_notExists_unchanged_false() throws Exception {
        boolean deleted = repository.delete(3);
        assertFalse(deleted);

        List<String> rows = TestUtil.readFile(TEST_FILE_NAME);
        assertEquals(2, rows.size());
    }
}
