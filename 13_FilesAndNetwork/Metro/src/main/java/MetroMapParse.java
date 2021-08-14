import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class MetroMapParse {

    public static void mapParse(String url) {
        saveJsonFile(parseHtmlMapToJson(url));
    }

    private static String parseHtmlMapToJson(String url) {
        JSONObject jsonMetroMap = new JSONObject();
        JSONObject jsonStationsInLines = new JSONObject();
        JSONArray jsonLinesNames = new JSONArray();
        JSONArray jsonAllConnections = new JSONArray();
        Set<String> lineController = new TreeSet<>(); // control line for avoid connections duplicates

        try {
            Document doc = Jsoup.connect(url).maxBodySize(0).get();
            Elements lines;

            lines = doc.select("div.js-toggle-depend");
            lines.forEach(el -> {
                JSONArray jsonStations = new JSONArray();
                String lineNumber = el.child(0).attr("data-line");
                String lineName = el.select("span.js-metro-line").text();
                lineController.add(lineNumber); // current line added

                Elements stations = el.nextElementSibling().select("span.name");
                stations.forEach(s -> {
                    // add stations
                    jsonStations.add(s.text());

                    //add connections
                    Element nextElement = s.nextElementSibling();
                    if (nextElement == null) return;
                    JSONArray jsonConnections = new JSONArray();
                    JSONObject jsonMainConnect = new JSONObject();
                    String stationName = s.text();
                    jsonMainConnect.put("line", lineNumber);
                    jsonMainConnect.put("station", stationName);
                    jsonConnections.add(jsonMainConnect);
                    while (nextElement != null) {
                        JSONObject jsonConnect = new JSONObject();
                        String connectStationLine = nextElement.className().split(" ")[1].replaceAll("\\w+-", "");

                        for (String l : lineController)
                            if (connectStationLine.equals(l)) return; // all connections already exists in previous line

                        String connectStationName = nextElement.attr("title").split("«")[1].split("»")[0];
                        jsonConnect.put("line", connectStationLine);
                        jsonConnect.put("station", connectStationName);
                        jsonConnections.add(jsonConnect);
                        nextElement = nextElement.nextElementSibling();
                    }
                    jsonAllConnections.add(jsonConnections);
                });

                // add lines
                jsonStationsInLines.put(el.nextElementSibling().child(1).attr("data-line"), jsonStations);
                JSONObject jsonLine = new JSONObject();
                jsonLine.put("number", lineNumber);
                jsonLine.put("name", lineName);
                jsonLinesNames.add(jsonLine);
            });

            jsonMetroMap.put("stations", jsonStationsInLines);
            jsonMetroMap.put("lines", jsonLinesNames);
            jsonMetroMap.put("connections", jsonAllConnections);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonMetroMap);
    }

    private static void saveJsonFile(String jsonMetroMap) {
        try {
            FileWriter writer = new FileWriter("src/main/resources/map.json", false);
            writer.write(jsonMetroMap);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readJsonFile(String path) {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader(path);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONObject stationsObject = (JSONObject) jsonObject.get("stations");
            AtomicInteger stationsCount = new AtomicInteger();
            stationsObject.keySet().forEach(line -> {
                JSONArray stationsArray = (JSONArray) stationsObject.get(line);
                System.out.println("Станций метро на линии " + line + ": " + stationsArray.size());
                stationsCount.addAndGet(stationsArray.size());
            });
            System.out.println("\nОбщее количество станций: " + stationsCount);

            JSONArray connectionsArray = (JSONArray) jsonObject.get("connections");
            System.out.println("Количество переходов: " + connectionsArray.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
