import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Esper extends Player {
    private BufferedImage image;
    private BufferedImage bullet;
    private int bulletPosX;
    private int bulletPosY;
    private Machinegun shooter;
    public Esper(int x, int y){
        super(x, y);
        super.setXVel(1);
        super.setYVel(1);
        try{
            image = ImageIO.read(this.getClass().getResource("esper.png"));
        } catch(IOException e){}
    }

    /*
    public void shoot(){
        Machinegun shot = new Machinegun(150, 150, bullet);
        shooter = shot;
    }
    */

    public void move(){
        super.move();

        if (Main.mouse.getLMB()){ //Adds teleporting functionality with left-click
            super.setxPos(Main.mouse.getX()-15);
            super.setYPos(Main.mouse.getY()-5);
        }
    }

    public void paint(Graphics2D g){
        g.drawImage(image, super.getxPos(), super.getyPos(), null);
    }
}
