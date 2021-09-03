import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static int CORES = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        String srcFolder = "C:/users/kainart/Desktop/src";
        String dstFolder = "C:/users/kainart/Desktop/dst";

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();
        int newWidth = 300;
        int filesLength = files.length / CORES;

        System.out.println("Обнаружено ядер: " + CORES);

        for (int i = 0; i < CORES; i++) {
            File[] filesInThread = new File[filesLength];
            System.arraycopy(files, i * filesLength, filesInThread, 0, i * filesLength + filesLength);
            ImageResizer resizer = new ImageResizer(filesInThread, dstFolder, newWidth);
            new Thread(resizer).start();
        }
    }
}
