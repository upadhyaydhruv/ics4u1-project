import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Skuttler extends Player {

    private BufferedImage image;
    public Skuttler(){
        try{
            this.image = ImageIO.read(this.getClass().getResource("skuttler.png"));
        } catch(IOException e){}
    }

    public void paint(Graphics2D g){
        g.drawImage(image, super.getxPos(), super.getyPos(), null);
    }
}
