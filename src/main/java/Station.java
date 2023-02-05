import java.lang.reflect.Array;
import java.util.ArrayList;

public class Station {
    public final String name;
    public final String line;

    public final boolean hasConnection;



    private String date;
    private double depth = 1000;

    Station(String name, String line, boolean hasConnection){
        this.line = line;
        this.name = name;
        this.hasConnection = hasConnection;
    }

    @Override
    public String toString() {
        return name + " переход " + hasConnection + "\n Глубина " + getDepth() + "\n Дата:" + getDate() + "\n";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
}
