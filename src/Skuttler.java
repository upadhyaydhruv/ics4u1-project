import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Skuttler extends Player {
    private BufferedImage image;
    private BufferedImage bullet;
    private BufferedImage sword;
    public Skuttler(int x, int y){
        super(x, y);
        super.setXVel(1);
        super.setYVel(1);
        try{
            image = ImageIO.read(this.getClass().getResource("skuttler.png"));
            bullet = ImageIO.read(this.getClass().getResource("skuttler shot C.png"));
        } catch(IOException e){}
    }

    public void shoot(int x, int y){
        int deltaX = x-super.getxPos();
        int deltaY = y-super.getyPos();

    }

    public void move(){
        super.move();
        if (Main.mouse.getLMB()){
            this.shoot(Main.mouse.getX(), Main.mouse.getY());
        }
    }

    public void paint(Graphics2D g){
        g.drawImage(image, super.getxPos(), super.getyPos(), null);
    }
}
