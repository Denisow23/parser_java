import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVPapser {
    public static void parse(String path, ArrayList<Line> lines) {
        List<String> linesOfFile = new ArrayList<>();
        try {
            linesOfFile = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String firstString = null;
        for (String lineOfFile : linesOfFile) {
            if (firstString == null) {
                firstString = lineOfFile;
                continue;
            }
            String[] lineOfFileArray = lineOfFile.split(",");
            lineOfFileArray[0] = lineOfFileArray[0].replaceAll("ё", "е");
            for (Line line : lines) {
                for (Station station1 : line.stations) {
                    if (station1.name.equalsIgnoreCase(lineOfFileArray[0]) &&
                            (station1.getDate() == null || !station1.getDate().equals(lineOfFileArray[1]))) {
                        station1.setDate(lineOfFileArray[1]);
                        lineOfFileArray[1] = "";
                        lineOfFileArray[0] = "";
                    } else continue;
                }
            }
        }
    }
}
