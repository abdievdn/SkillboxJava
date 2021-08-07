import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageDownloader {

    private final static Pattern IMG_REGEX = Pattern.compile("/\\w+\\.\\w{3,4}$");

    private final String doc;
    private final List<String> imagesSrc;

    public ImageDownloader(String doc) {
        this.doc = doc;
        imagesSrc = new ArrayList<>();
        downloadImages();
    }

    private void downloadImages() {
        System.out.println("Downloading images...");
        try {
            Document doc = Jsoup.connect(this.doc).get();
            Elements imageLinks = doc.select("img");
            imageLinks.forEach(el -> {
                String src = el.attr("abs:src");
                Matcher matcher = IMG_REGEX.matcher(src);
                if (!matcher.find()) return;
                String fileName = src.substring(matcher.start() + 1, matcher.end());
                try {
                    FileUtils.copyURLToFile(new URL(src), new File("src/images/" + fileName));
                    imagesSrc.add(fileName);
                } catch (IOException e) {
                    System.out.println("Download failed!");
                    e.printStackTrace();
                }
            });
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void printImageList() {
        imagesSrc.forEach(System.out::println);
    }
}
