import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public interface Thing {
    static BufferedImage loadImage(String name) {
        InputStream is = null;

        // normalize the path
        String baseImage = name.replaceAll("\\\\", "/").replace("res/", "");

        try {
            // try loading it as a resource (this is the fastest one and usually the best)
            try {
                is = Main.class.getResourceAsStream(baseImage);
            } catch (Exception ignored) {}

            // fall back to loading it from the current folder
            if (is == null) {
                try {
                    is = new FileInputStream(baseImage);
                } catch (Exception ignored) {}
            }

            // fall back to loading it from the res folder (and pass on the error)
            if (is == null) {
                is = new FileInputStream("res/" + baseImage);
            }

            // read it
            return ImageIO.read(is);
        } catch (Exception ex) {
            System.out.println("error loading image " + name);
            System.out.println("try right-clicking res in the Project tree and marking it as Resources Root.");
            throw new RuntimeException(ex);
        }
    }

    void paint(Graphics2D g);
    void move();
    void setCurrentLevel(Level currentLevel); // this will be called whenever a thing is added and called with null when it is removed
}
