import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class ImageResizer implements Runnable {

    private String dstFolder;
    private File[] files;
    private int newWidth;
    private int newHeight;

    public ImageResizer(File[] files, String dstFolder, int newWidth) {
        this.dstFolder = dstFolder;
        this.files = files;
        this.newWidth = newWidth;
    }

    public static BufferedImage resize(BufferedImage originalImage, int targetWidth, int targetHeight) {
        return Scalr.resize(originalImage, targetWidth, targetHeight);
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));
                BufferedImage newImage = resize(image, newWidth, newHeight);

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " finished in " + (System.currentTimeMillis() - start) + " ms");
    }
}
