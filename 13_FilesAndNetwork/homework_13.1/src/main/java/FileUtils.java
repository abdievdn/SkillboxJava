import java.io.File;
import java.nio.file.Paths;

public class FileUtils {

    public static long calculateFolderSize(String path) {
        long size = 0;
        try {
            File files = new File(path);
            if (files.isDirectory()) {
                for (File file : files.listFiles()) {
                    if (file.isFile()) size += file.length();
                    else size += calculateFolderSize(file.getPath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
}
