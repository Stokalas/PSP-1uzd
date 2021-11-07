import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    private final String fileName;

    CsvWriter(String fileName) {
        this.fileName = fileName;
    }

    boolean writeEntries(List<AccountModel> accounts, boolean append) {
        try {
            FileWriter csvWriter = new FileWriter(fileName, append);
            for (var acc : accounts) {
                csvWriter.append(String.valueOf(acc.getId())).append(",");
                csvWriter.append(acc.getName()).append(",");
                csvWriter.append(acc.getSurname()).append(",");
                csvWriter.append(acc.getPhoneNumber()).append(",");
                csvWriter.append(acc.getEmail()).append(",");
                csvWriter.append(acc.getAddress().toString()).append(",");
                csvWriter.append(acc.getPassword()).append("\n");
            }

            csvWriter.close();
        } catch (IOException ex) {
            System.out.println("Failed to open the csv file!");
            return false;
        }
        return true;
    }
}
