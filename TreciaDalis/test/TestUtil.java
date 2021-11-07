import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {
     static List<String> readFile(String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<String> rows = new ArrayList<>();
        String row;
        while ((row = reader.readLine()) != null) {
            rows.add(row);
        }

        return rows;
    }
}
