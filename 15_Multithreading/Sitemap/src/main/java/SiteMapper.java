import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.RecursiveAction;

public class SiteMapper extends RecursiveAction {

    private final String url;
    private static final TreeSet<String> siteMap = new TreeSet();

    public SiteMapper(String url) {
        this.url = url;
        siteMap.add("");
    }

    public static TreeSet<String> getSiteMap() {
        return siteMap;
    }

    @Override
    protected void compute() {

        List<SiteMapper> taskList = new ArrayList<>();

        try {
            siteMap.forEach(link -> {
                if (!(url).equals(link)) {
                    siteMap.add(url);
                    System.out.println("Added " + url);
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    PageMapper pageMap = new PageMapper(url);
                    pageMap.getLinks().forEach(pageLink -> {
                        SiteMapper task = new SiteMapper(url + pageLink);
                        task.fork();
                        taskList.add(task);
                    });
                }
                System.out.println("Skipped " + url);
            });
            taskList.forEach(SiteMapper::join);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
