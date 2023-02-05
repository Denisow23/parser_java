import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.nodes.Document;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {

        Document doc = HTMLParser.connection("https://skillbox-java.github.io/");
        ArrayList<Line> lines = HTMLParser.parseLines(doc);
        HTMLParser.parseStations(lines, doc);

        ArrayList<String> JSONPathes = new ArrayList<>();
        ArrayList<String> CSVPathes = new ArrayList<>();
        Objects.requireNonNull(Recursive.list("data")).forEach(path -> {
            if (path.toPath().toString().regionMatches(path.toPath().toString().lastIndexOf('.'),
                    ".json", 0, 5)) {JSONPathes.add(path.toPath().toString());}
            else CSVPathes.add(path.toPath().toString());//иначе
        });

        JSONPathes.forEach(path -> {
            try {
                JSONParser.parse(path, lines);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        CSVPathes.forEach(path -> {
            CSVPapser.parse(path, lines);
        });

        JSONLines jsonLines = new JSONLines(lines);

        ObjectMapper mapper = new ObjectMapper();
        File output = new File("src/main/resources/export.json");
        mapper.writerWithDefaultPrettyPrinter().writeValue(output, jsonLines);

        JSONStations.writeFile(lines);
    }
}
