import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Missile {
    private BufferedImage image;
    private int xPos;
    private int yPos;
    private int damage = 0;
    private int xVel; //These are just placeholders for testing right now
    private int yVel;
    private int ticker = 0; //This is to slow done speed of moving graphics objects using modular arithmetic
    private boolean RMBToggle=false;


    public Missile(int xOrig, int yOrig) {
        try {
            image = ImageIO.read(this.getClass().getResource("tiamat rocket.png"));
        } catch(IOException e) {}

        this.xPos = xOrig;
        this.yPos = yOrig;
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
            yVel = (int) Math.ceil(((double) yDeff / dist)*3);
        } else {
            yVel = (int) Math.ceil(((double) yDeff / dist)*3);
        }
    }

    public void flipVel(){
        xVel = -xVel;
        yVel = -yVel;
    }

    public int getDamage(){
        return this.damage;
    }

    //bobby's fix
    public void move(){
        if (Main.mouse.getRMB()&&RMBToggle){
            this.flipVel();
            RMBToggle=false;
        }
        if (!Main.mouse.getRMB()){
            RMBToggle=true;
        }
    //bobby's fix

        ticker++;
        if (ticker%50000==0) {
            xPos += xVel;
            yPos += yVel;
        }

        if (ticker%1000000==0){
            damage++;
        }
    }

    public void paint(Graphics2D g){
        g.drawImage(image, xPos, yPos, null);
    }
}
