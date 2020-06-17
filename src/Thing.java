import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public interface Thing {
    static BufferedImage loadImage(String name) {
        try {
            return ImageIO.read(Main.class.getResourceAsStream(name.replaceAll("\\\\", "/").replace("res/", "")));
        } catch (Exception ex) {
            System.out.println("error loading " + name);
            throw new RuntimeException(ex);
        }
    }

    void paint(Graphics2D g);
    void move();
    void setCurrentLevel(Level currentLevel); // this will be called whenever a thing is added and called with null when it is removed
}
