import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.RecursiveTask;

public class SiteMapper extends RecursiveTask<TreeSet<String>> {

    private String url;
    private static TreeSet<String> siteMap = new TreeSet();

    public SiteMapper(String url) {
        this.url = url;
        siteMap.add("");
    }

    @Override
    protected TreeSet compute() {

        List<SiteMapper> taskList = new ArrayList<>();

        try {
            siteMap.forEach(link -> {
                if (url != link) {
                    siteMap.add(url);
                    System.out.println("Added " + url);
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    PageMapper pageMap = new PageMapper(url);
                    pageMap.getLinks().forEach(pageLink -> {
                        SiteMapper task = new SiteMapper(pageLink);
                        task.fork();
                        taskList.add(task);
                    });
                    taskList.forEach(SiteMapper::join);
                }
                System.out.println("Skipped " + url);
            });
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return siteMap;
    }
}
