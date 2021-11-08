import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private final CsvWriter csvWriter;
    private final CsvReader csvReader;

    public AccountRepository(String connectionString) {
        this.csvWriter = new CsvWriter(connectionString);
        this.csvReader = new CsvReader(connectionString);
    }

    public AccountModel create(AccountModel accountModel) {
        List<AccountModel> l = new ArrayList<>();
        accountModel.setId(csvReader.calculateNextId()); //needed only because storing in csv
        l.add(accountModel);
        if (csvWriter.writeEntries(l, true)) {
            return accountModel;
        }

        return null;
    }

    public AccountModel findById(int id) {
        List<AccountModel> accounts = csvReader.readData();
        for (var acc : accounts) {
            if (acc.getId() == id) {
                return acc;
            }
        }
        return null;
    }

    public AccountModel edit(AccountModel accountModel) {
        boolean writeSuccessful = false;
        List<AccountModel> accounts = csvReader.readData();
        int id = accountModel.getId();
        for (int i = 0; i < accounts.size(); i++) {
            var temp = accounts.get(i);
            if (temp.getId() == id) {
                accounts.set(i, accountModel);
                writeSuccessful = csvWriter.writeEntries(accounts, false);
                break;
            }
        }
        if (writeSuccessful) {
            return accountModel;
        }
        return null;
    }

    public boolean delete(int id) {
        boolean writeSuccessful = false;
        List<AccountModel> accounts = csvReader.readData();
        for (int i = 0; i < accounts.size(); i++) {
            var temp = accounts.get(i);
            if (temp.getId() == id) {
                accounts.remove(i);
                writeSuccessful = csvWriter.writeEntries(accounts, false);
                break;
            }
        }

        return writeSuccessful;
    }
}
