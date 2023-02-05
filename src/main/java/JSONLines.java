import java.util.ArrayList;
import java.util.HashMap;

public class JSONLines {
    public HashMap<String, ArrayList<String>> stations;
    public ArrayList<Line> lines;

    JSONLines(ArrayList<Line> lines) {
        this.lines = new ArrayList<>();
        this.stations = new HashMap<>();
        this.lines = lines;
        lines.forEach(line -> {
            ArrayList<String> stationsOfLine = new ArrayList<>();
            line.stations.forEach(station -> stationsOfLine.add(station.name));
            this.stations.put(line.number,stationsOfLine);
        });
    }
}

