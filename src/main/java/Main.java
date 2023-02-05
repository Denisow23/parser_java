import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.nodes.Document;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {

        Document doc = HTMLParser.connection("https://skillbox-java.github.io/");//код сайта
        ArrayList<Line> lines = HTMLParser.parseLines(doc);
        HTMLParser.parseStations(lines, doc);//информация о названиях станций и линий, номера линий, информация о переходах

        ArrayList<String> JSONPathes = new ArrayList<>();
        ArrayList<String> CSVPathes = new ArrayList<>();
        Objects.requireNonNull(Recursive.list("data")).forEach(path -> {//рекурсивный обход папок
            if (path.toPath().toString().regionMatches(path.toPath().toString().lastIndexOf('.'),
                    ".json", 0, 5)) {JSONPathes.add(path.toPath().toString());}//если файл заканчивается на .json
            else CSVPathes.add(path.toPath().toString());//иначе
        });

        JSONPathes.forEach(path -> {//лист файлов .json
            try {
                JSONParser.parse(path, lines);//парсинг файлов
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        CSVPathes.forEach(path -> {
            CSVPapser.parse(path, lines);//парсинг файлов .csv из листа с адресами файлов
        });

        JSONLines jsonLines = new JSONLines(lines);//класс для создания 1-го .json файла, похожего на map.json

        ObjectMapper mapper = new ObjectMapper();
        File output = new File("src/main/resources/export.json");
        mapper.writerWithDefaultPrettyPrinter().writeValue(output, jsonLines);//запись 1-го файла

        JSONStations.writeFile(lines);//создание и запись 2-го файла
    }
}
