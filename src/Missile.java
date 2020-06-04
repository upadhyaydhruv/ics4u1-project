import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Missile {
    private BufferedImage image;
    private int xPos;
    private int yPos;
    private int damage;
    private int xVel = 1; //These are just placeholders for testing right now
    private int yVel = 1;
    private int ticker = 0; //This is to slow done speed of moving graphics objects using modular arithmetic

    public Missile(int xOrig, int yOrig) {
        try {
            image = ImageIO.read(this.getClass().getResource("tiamat rocket.png"));
        } catch(IOException e) {}

        this.xPos = xOrig;
        this.yPos = yOrig;
    }

    public void flipVel(){
        xVel = -xVel;
        yVel = -yVel;
    }

    public void move(){
        if (Main.mouse.getLMB()){
            this.flipVel();
        }

        ticker++;
        if (ticker%50000==0) {
            xPos += xVel;
            yPos += yVel;
        }

    }

    public void paint(Graphics2D g){
        g.drawImage(image, xPos, yPos, null);
    }
}
