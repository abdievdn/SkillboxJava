import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.RecursiveAction;

public class SiteMapper extends RecursiveAction {

    private final String url;
    private String mapLink = "";
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
                String fullLink = url + mapLink;
                if (!(fullLink).equals(link)) {
                    siteMap.add(fullLink);
                    System.out.println("Added " + fullLink);
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    PageMapper pageMap = new PageMapper(fullLink);
                    pageMap.getLinks().forEach(pageLink -> {
                        mapLink = pageLink;
                        SiteMapper task = new SiteMapper(url + pageLink);
                        task.fork();
                        taskList.add(task);
                    });
                }
                System.out.println("Skipped " + fullLink);
            });
            taskList.forEach(SiteMapper::join);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
