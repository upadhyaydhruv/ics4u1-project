import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class Bomb implements Hittable {
    private BufferedImage bomb, explosion;
    //private BufferedImage resizedImage;
    private int frame = 0;
    private int x, y;
    private Boolean isPlaced = false;
    private Boolean onTimer = false;
    private int ticktick = 0;
    public int frameDelay = 0;
    Explosion e = new Explosion();
    private Hittable.HitBox hb;

    public Bomb() {

        try{
            bomb = ImageIO.read(this.getClass().getResource("landmine.png"));
        } catch(IOException e){
            System.out.print("there");
        }
        x = (int)(Math.random()*960);
        y = (int)(Math.random()*720);
        hb = new Hittable.HitBox(false, bomb.getHeight(), bomb.getWidth(), x, y, 0);
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
            e.trigger(x,y);
                isPlaced = false;
                ticktick = 0;
            }
        e.paint(g);
        }
}