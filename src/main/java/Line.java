import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Line {
    public final String name;
    public final String number;
    @JsonIgnore
    public ArrayList<Station> stations;

    Line(String name, String number){
        this.name = name;
        this.number = number;
        this.stations = new ArrayList<>();
    }

    public void add(Station station){
        stations.add(station);
    }

    @Override
    public String toString() {
        return "Линия " + name + " с номером " + number + " проходит через следующие станции " + stations;
    }
}
