import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    private final String fileName;


    CsvReader(String fileName) {
        this.fileName = fileName;
    }

    List<AccountModel> readData() {
        List<AccountModel> l = new ArrayList<>();
        String row;
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
            while ((row = csvReader.readLine()) != null) {
                String[] r = row.split(",");
                AddressModel address = new AddressModel(r[5], r[6], r[7]);
                AccountModel model = new AccountModel(Integer.parseInt(r[0]), r[1], r[2], r[3], r[4], address, r[8]);
                l.add(model);
            }
            csvReader.close();
        } catch (IOException ex) {
            System.out.println("Error when reading the csv file!");
        } catch (NumberFormatException ex) {
            System.out.println("Error when reading id of account!");
        }
        return l;
    }

    int calculateNextId() {
        List<AccountModel> l = readData();
        int max = -1;
        for (AccountModel x : l) {
            int id = x.getId();
            if (id > max) {
                max = id;
            }
        }

        return max + 1;
    }
}
