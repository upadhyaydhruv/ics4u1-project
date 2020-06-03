import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tiamat extends Player {
    private BufferedImage image;
    private BufferedImage bullet;
    private BufferedImage sword;
    private int bulletPosX;
    private int bulletPosY;
    private Machinegun shooter;
    public Tiamat(int x, int y){
        super(x, y);
        super.setXVel(1);
        super.setYVel(1);
        try{
            image = ImageIO.read(this.getClass().getResource("tiamat.png"));
        } catch(IOException e){}
    }

    public void shoot(){
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
