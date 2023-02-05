import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class JSONParser {
    public static void parse(String path, ArrayList<Line> lines) throws Exception{
        String jsonFile = Files.readString(
                Paths.get(path)
        );

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonData = objectMapper.readTree(jsonFile);

        for (JsonNode station : jsonData){
            ObjectNode stationNode = (ObjectNode) station;
            String objectName = stationNode.get("station_name").asText();
            objectName = objectName.replaceAll("ё","е");
            String depthString = stationNode.get("depth").asText();
            depthString = depthString.replaceAll(",", ".");
            double depthDouble = 1000;
            if (!depthString.equals("?")){
            depthDouble = Double.parseDouble(depthString);
            }
            for(Line line : lines) {
                for (Station station1 : line.stations){
                    if (station1.name.equalsIgnoreCase(objectName) && (station1.getDepth() > depthDouble)){
                        station1.setDepth(depthDouble);
                    }
                }
                ;
            };
        }




    }
}
