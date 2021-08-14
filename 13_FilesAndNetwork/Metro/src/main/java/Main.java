
public class Main {
    public static void main(String[] args) {
        MetroMapParse.mapParse("https://www.moscowmap.ru/metro.html#lines");
        MetroMapParse.readJsonFile("src/main/resources/map.json");
    }
}
