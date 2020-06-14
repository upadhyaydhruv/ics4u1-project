import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class Bomb {
    private BufferedImage bomb, explosion;
    //private BufferedImage resizedImage;
    private int frame = 0;
    private int x, y;
    private Boolean isPlaced = false;
    private Boolean onTimer = false;
    private int ticktick = 0;
    public int frameDelay = 0;

    public Bomb() {

        try{
            bomb = ImageIO.read(this.getClass().getResource("barrel.png"));
            explosion = ImageIO.read(this.getClass().getResource("box.png"));
        } catch(IOException e){
            System.out.print("there");
        }
        x = (int)(Math.random()*960);
        y = (int)(Math.random()*720);

    }

    public boolean getisPlaced() {
        return isPlaced;
    }

    public void move() {
        if (!isPlaced) {
            x = (int)(Math.random()*960);
            y = (int)(Math.random()*720);
            isPlaced = true;
        }
    }

    public void paint(Graphics2D g) {

        if (isPlaced) {
            g.drawImage(bomb, x, y, null);
            if (isPlaced) {
                ticktick++;
            }
        }

        if (ticktick >= 200) {
            g.drawImage(explosion, x, y, null);
            frameDelay++;
            if (frameDelay == 50) {
                isPlaced = false;
                ticktick = 0;
                frameDelay = 0;
            }
        }
    }
}