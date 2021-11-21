import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class PageMapper {

    private final String url;
    private final TreeSet<String> links = new TreeSet<>();

    public PageMapper(String url) {
        this.url = url;
        this.pageMapping();
    }

    //Collecting Links from page
    private void pageMapping() {
        try {
            Document doc = Jsoup.connect(url).maxBodySize(0).get();
            Elements urls;

            urls = doc.select("a");
            urls.forEach(el -> {
                String link = el.attr("href");
                if (!link.contains(".pdf")
                        && !link.contains(".jpg")
                        && !link.contains(".png")
                        && !link.contains("?")) {
                    if (link.contains(url))
                        links.add(link);
                    if (link.matches("/.*") && !url.contains(link))
                        links.add(url + link.substring(1));
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }

    public TreeSet<String> getLinks() {
        return links;
    }
}
