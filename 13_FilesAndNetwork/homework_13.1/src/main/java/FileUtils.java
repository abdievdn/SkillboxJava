import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public static long calculateFolderSize(String path) {
        long size = 0;
        try {
            size = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .mapToLong(f -> {
                        try {
                            return Files.size(f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return 0;
                    })
                    .sum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
}
