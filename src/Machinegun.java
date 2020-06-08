import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Machinegun {
    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private BufferedImage image;
    private int ticker = 0;

    public Machinegun(int xOrig, int yOrig){
        try {
            image = ImageIO.read(this.getClass().getResource("skuttler shot C.png"));
        } catch(IOException e) {}
        xPos = xOrig;
        yPos = yOrig;
        int xTar = Main.mouse.getX();
        int yTar = Main.mouse.getY();
        final int xDeff = -(xPos - xTar);
        final int yDeff = -(yPos - yTar);
        double dist = Math.sqrt((double)xDeff*(double)xDeff+(double)yDeff*(double)yDeff);
        if (xDeff > 0) {
            xVel = (int) Math.ceil(((double) xDeff / dist)*7);
        } else {
            xVel = (int) Math.ceil(((double) xDeff / dist) * 7);
        }

        if (yDeff > 0) {
            yVel = (int) Math.ceil(((double) yDeff / dist)*7);
        } else {
            yVel = (int) Math.ceil(((double) yDeff / dist)*7);
        }
        }
    /*
    public void setImage(BufferedImage image){
        this.image = image;
    }
    */

    public void move(){
        ticker++;
        if (ticker % 50000 == 0) {
            xPos += xVel;
            yPos += yVel;
            ticker = 0;
            //    }
        }
    }

    public void paint(Graphics2D g){
        g.drawImage(image,xPos, yPos,null);
    }
}
