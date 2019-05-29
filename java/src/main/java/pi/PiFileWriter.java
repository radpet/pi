package pi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class PiFileWriter {

    public static boolean writeToFile(BigDecimal pi, String path) {
        try (FileWriter fileWriter = new FileWriter(new File(path))) {
            fileWriter.write(pi.toPlainString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
