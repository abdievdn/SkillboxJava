import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) throws IOException {
        // TODO: write code copy content of sourceDirectory to destinationDirectory
        Path source = Paths.get(sourceDirectory);
        Path dest = Paths.get(destinationDirectory);
        try {
            if (!Files.exists(dest))
                Files.createDirectory(dest);
            for (Path f : Files.newDirectoryStream(source)) {
                if (Files.isDirectory(f))
                    copyFolder(f.toString(), dest.resolve(f.getFileName()).toString());
                if (!Files.exists(dest.resolve(f.getFileName())))
                    Files.copy(f, dest.resolve(f.getFileName()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
