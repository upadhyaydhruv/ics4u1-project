import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Missile {
    private BufferedImage image;
    private int xPos;
    private int yPos;
    private int damage = 0;
    private int xVel = 1; //These are just placeholders for testing right now
    private int yVel = 1;
    private int ticker = 0; //This is to slow done speed of moving graphics objects using modular arithmetic
    private boolean RMBToggle=false;


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

    public int getDamage(){
        return this.damage;
    }

    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
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
        if (ticker%5000==0) {
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
