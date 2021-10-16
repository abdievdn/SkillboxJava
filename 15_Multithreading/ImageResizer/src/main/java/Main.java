import java.io.File;

public class Main {

    public static int CORES = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        System.out.println("Обнаружено ядер: " + CORES);

        String srcFolder = "img/src";
        String dstFolder = "img/dst";

        File folder = new File(dstFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();
        int newWidth = 300;
        int filesLength = files.length;
        int threadLength;
        int srcPos = 0;

        for (int i = CORES; i > 0; i--) {
            threadLength = filesLength / i;
            File[] filesInThread = new File[threadLength];
            System.arraycopy(files, srcPos, filesInThread, 0, threadLength);
            ImageResizer resizer = new ImageResizer(filesInThread, dstFolder, newWidth);
            new Thread(resizer).start();
            filesLength = filesLength - threadLength;
            srcPos = srcPos + threadLength;
        }
    }
}
