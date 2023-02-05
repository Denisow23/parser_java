import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONStations {

    public static void writeFile(ArrayList<Line> lines) throws IOException {
        ObjectMapper mapper1 = new ObjectMapper();
        File output1 = new File("export1.json");
        ArrayList<Station> stations = new ArrayList<>();
        lines.forEach(line -> {
            stations.addAll(line.stations);
        });
        mapper1.writerWithDefaultPrettyPrinter().writeValue(output1, stations);
        String jsonFile = Files.readString(
                Paths.get("export1.json")
        );

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonData = objectMapper.readTree(jsonFile);

        for (JsonNode station : jsonData){
            ObjectNode stationNode = (ObjectNode) station;
            if(stationNode.get("depth").asDouble() == 1000){
                stationNode.remove("depth");
            }
            if (stationNode.get("date").isNull()){
                stationNode.remove("date");
            }
        }
        ObjectMapper mapper2 = new ObjectMapper();
        File output2 = new File("src/main/resources/export1.json");
        mapper2.writerWithDefaultPrettyPrinter().writeValue(output2, jsonData);
    }
}
