import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class Bomb {
    private BufferedImage bomb, explosion;
    //private BufferedImage resizedImage;
    private int frame = 0;
    private int x, y, xVel, yVel;

    public Bomb() {

        try{
            bomb = ImageIO.read(this.getClass().getResource("esper.png"));
            explosion = ImageIO.read(this.getClass().getResource("esper.png"));
            // image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            // resizedImage = (BufferedImage) image;
        } catch(IOException e){
            System.out.print("there");
        }
    }

    public void move() {
        
    }

    public void paint(Graphics2D g){
        g.drawImage(bomb, x,y,null);
        g.drawImage(explosion,x,y,null);
    }

}