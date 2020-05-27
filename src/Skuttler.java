import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Skuttler extends Player {

    private Image image;
    private BufferedImage resizedImage;
    public Skuttler(Keyboard kb, int x, int y){
        super(kb, x, y);
        try{
            this.image = ImageIO.read(this.getClass().getResource("skuttler.png"));
            image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            resizedImage = (BufferedImage) image;
        } catch(IOException e){}
    }

    public void move(){
        super.move();
    }

    public void paint(Graphics2D g){
        g.drawImage(resizedImage, super.getxPos(), super.getyPos(), null);
    }
}
