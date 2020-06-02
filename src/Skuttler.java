import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Skuttler extends Player {

    private BufferedImage image;
    private BufferedImage resizedImage;
    public Skuttler(int x, int y){
        super(x, y);
        try{
            image = ImageIO.read(this.getClass().getResource("car.png"));
        } catch(IOException e){}
    }

    public void move(){
        super.move();
    }

    public void paint(Graphics2D g){
        g.drawImage(resizedImage, super.getxPos(), super.getyPos(), null);
    }
}
