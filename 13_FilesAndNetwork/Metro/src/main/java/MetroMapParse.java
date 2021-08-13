import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class MetroMapParse {
    private Document doc;

    public MetroMapParse(String url) {
        saveJsonFile(parseHtmlMapToJson(url));
    }

    private String parseHtmlMapToJson(String url) {
        JSONObject jsonMetroMap = new JSONObject();
        JSONObject jsonLines = new JSONObject();
        JSONArray jsonLinesNames = new JSONArray();
//        JSONArray jsonConnections = new JSONArray();

        try {
            doc = Jsoup.connect(url).maxBodySize(0).get();
            Elements lines;

            // stations in lines
            lines = doc.select("div.js-metro-stations");
            lines.forEach(el -> {
                JSONArray jsonStations = new JSONArray();
                Elements stations = el.select("span.name");
                stations.forEach(s -> jsonStations.add(s.text()));
                jsonLines.put(el.attr("data-line"), jsonStations);
            });
            jsonMetroMap.put("stations", jsonLines);

            // lines number and name
            lines = doc.select("span.js-metro-line");
            lines.forEach(el -> {
                String number = el.attr("data-line");
                String name = el.text();
                JSONObject jsonLine = new JSONObject();
                jsonLine.put("number", number);
                jsonLine.put("name", name);
                jsonLinesNames.add(jsonLine);
            });
            jsonMetroMap.put("lines", jsonLinesNames);

//            // connections
//            List<String> linesSet = new ArrayList<>();
//            List<String> linesMatch = new ArrayList<>();
//            lines = doc.select("div.js-metro-stations");
//            lines.forEach(el -> {
//                Elements connections = el.select("span.t-icon-metroln");
//
//                connections.forEach(c -> {
//                    String connectMainStation;
//                    String connectStations;
//                    connectMainStation = el.attr("data-line") + ":" + el.selectFirst("span.name").text();
//                    connectStations = c.className().replaceAll(".[^\\d]", "") + ':' +
//                            c.attr("title").split(" ")[3].replaceAll("[«»]", "");
//                    linesSet.add(connectMainStation + "_" + connectStations);
//                });
//
//
////                connections.forEach(c -> {
////                    String connectLine;
////                    connectLine = c.attr("title").replaceAll("(^«.»)", "");
////                    jsonConnections.add(connectLine);
//                });
//            linesSet.forEach(System.out::println);
////            jsonMetroMap.put("connections", jsonConnections);

            }
        catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonMetroMap);
    }

    private void saveJsonFile(String jsonMetroMap) {

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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
