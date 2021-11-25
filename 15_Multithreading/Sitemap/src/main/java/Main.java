import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

//        PageMapper lenta = new PageMapper("https://lenta.ru");
        SiteMapper lenta = new SiteMapper("https://lenta.ru/");
        new ForkJoinPool().invoke(lenta);
        saveToFile("map.txt", lenta.getSiteMap());
        System.out.println("Completed!");
    }

    public static void saveToFile(String path, TreeSet<String> links) {
        try {
            FileWriter writer = new FileWriter(path);
            links.forEach(link -> {
                try {
                    writer.write(formatLinkView(link) + '\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String formatLinkView(String link) {
        long slashCount = (link.chars().filter(ch -> ch == '/').count()) - 3;
        if (slashCount > 0) {
            link = String.join("", Collections.nCopies((int) slashCount, "\t")) + link;
        }
        return link;
    }
}