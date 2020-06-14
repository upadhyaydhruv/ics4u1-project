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
    private CopyOnWriteArrayList<Bomb> boomboom = new CopyOnWriteArrayList<>();

    public Bomb() {

        try{
            bomb = ImageIO.read(this.getClass().getResource("barrel.png"));
            explosion = ImageIO.read(this.getClass().getResource("box.png"));
            // image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            // resizedImage = (BufferedImage) image;
        } catch(IOException e){
            System.out.print("there");
        }
    }

    public void move() {
        if (!isPlaced) {
            x = (int)(Math.random()*960);
            y = (int)(Math.random()*720);
            isPlaced = true;
            onTimer = true;
        }
        if (onTimer) {
            ticktick ++;
        }
    }

    public void paint(Graphics2D g){

        if (onTimer) {
            g.drawImage(bomb, x, y, null);
            System.out.println("Tick");
            }

        if (ticktick == 100000) {
            g.drawImage(explosion, x, y, null);
            ticktick = 0;
            System.out.println("BOOM");
            isPlaced = false;
        }
    }

}