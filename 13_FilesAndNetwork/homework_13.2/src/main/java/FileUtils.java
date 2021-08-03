import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) throws IOException {
        // TODO: write code copy content of sourceDirectory to destinationDirectory
        Path source = Paths.get(sourceDirectory);
        Path dest = Paths.get(destinationDirectory);
        if (!Files.exists((dest)))
            Files.createDirectory(dest);
        try {
            Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path copyDir = dest.resolve(source.relativize(dir));
                    if (!Files.exists(copyDir))
                        Files.createDirectory(copyDir);
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFile(Path f, BasicFileAttributes attrs) throws IOException {
                    Path copyFile = dest.resolve(source.relativize(f));
                    if (!Files.exists(copyFile))
                        Files.copy(f, copyFile);
                    return FileVisitResult.CONTINUE;
                }
            });

//            Old Version
//            if (!Files.exists(dest))
//                Files.createDirectory(dest);
//            for (Path f : Files.newDirectoryStream(source)) {
//                if (Files.isDirectory(f))
//                    copyFolder(f.toString(), dest.resolve(f.getFileName()).toString());
//                if (!Files.exists(dest.resolve(f.getFileName())))
//                    Files.copy(f, dest.resolve(f.getFileName()));
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
