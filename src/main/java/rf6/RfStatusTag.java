package rf6;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author cp_liu
 */
public class RfStatusTag {

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "StatusTag:"+name;
    }
    
    private String name;
    
    public static List<RfStatusTag> fromCsv(String path) throws IOException {
        List<RfStatusTag> list = new ArrayList<>();
        try (CSVReader rdr = new CSVReader(new InputStreamReader(new FileInputStream(path)))) {
            boolean statusFound = false;
            for (String[] line : rdr.readAll()) {
                if (line.length>5) {
Logger.getLogger(RfStatusTag.class.getName()).log(Level.FINE, "{0}:{1}:{2}", new Object[]{
    line[0].trim().length()==0 ? "null" : line[0], line[1], statusFound
});
                    if (statusFound) {
                        RfStatusTag t = new RfStatusTag();
                        t.name = line[1];
                        list.add(t);
                    }
                    else {
                        statusFound = line[0].startsWith("Status");
                    }
                }
                else {
                    if (statusFound) break;
                }
            }
        }
        catch (IOException | CsvException ex) {
            Logger.getLogger(RfStatusTag.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException(ex);
        }
        return list;
    }
}
