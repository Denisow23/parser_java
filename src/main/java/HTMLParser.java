import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HTMLParser {

    public static Document connection(String path){
        try {
            return Jsoup.connect(path).get();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Line> parseLines(Document document){
        Elements lines = document.select("#metrodata > div > div.js-toggle-depend.s-depend-control-single> span");
        ArrayList<Line> lines1 = new ArrayList<>();
        for(Element line : lines){
            String name = line.text();
            lines1.add(new Line(name, line.attr("data-line")));
        };
        return lines1;
    }

    public static void parseStations(ArrayList<Line> lines, Document document){
        int number = 1;
        Elements lineStations = document.select("#metrodata > div > div.js-depend > div.js-metro-stations.t-metrostation-list-table");
        for(Element element : lineStations){
            Elements stations = element.select("p");
            for (Element element1 : stations) {
                boolean hasConnection = element1.select("span.t-icon-metroln").hasAttr("title");
                String name = element1.text();
                name = name.substring(name.indexOf('.') + 1);
                name = name.trim();
                name = name.replaceAll("ё", "е");

                lines.get(--number).stations.add(new Station(name,
                        lines.get(number).name, hasConnection));
                number++;
            }
            number++;
        }
    }
}
