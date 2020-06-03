import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Machinegun {
    private Player origin;
    private int xPos;
    private int yPos;
    private int xVel = 0;
    private int yVel = 0;
    private int xTar;
    private int yTar;
    private BufferedImage image = null;
    int ticker = 0;

    public Machinegun(int xOrig, int yOrig, BufferedImage image){
        this.image = image;
        xPos = xOrig;
        yPos = yOrig;
    }

    public void setImage(BufferedImage image){
        this.image = image;
    }

    public void move(){
        int xDeff = xPos - xTar;
        int yDeff = yTar - yPos;
        yVel = yDeff/xDeff;
        xVel = 1;
        if (Main.mouse.getRMB()) {
            ticker++;
            if (ticker % 10000 == 0) {
                xPos += xVel;
                yPos += yVel;
                ticker = 0;
            }
        }
    }

    public void paint(Graphics2D g){
        g.drawImage(image,xPos, yPos,null);
    }

}
