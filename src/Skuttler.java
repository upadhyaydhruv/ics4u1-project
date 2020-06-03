import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Skuttler extends Player {
    private BufferedImage image;
    private BufferedImage bullet;
    private BufferedImage sword;
    private int bulletPosX;
    private int bulletPosY;
    private Machinegun shooter;
    public Skuttler(int x, int y){
        super(x, y);
        super.setXVel(1);
        super.setYVel(1);
        try{
            image = ImageIO.read(this.getClass().getResource("skuttler.png"));
            bullet = ImageIO.read(this.getClass().getResource("skuttler shot C.png"));
        } catch(IOException e){}
    }

    public void shoot(){
        Machinegun shot = new Machinegun(150, 150, bullet);
        shooter = shot;
    }

    public void move(){
        super.move();
        if (Main.mouse.getRMB()){
            this.shoot();
            shooter.move();
        }
    }

    public void paint(Graphics2D g){
        g.drawImage(image, super.getxPos(), super.getyPos(), null);
        if (shooter!=null) {
            shooter.paint(g);
        }
    }
}
