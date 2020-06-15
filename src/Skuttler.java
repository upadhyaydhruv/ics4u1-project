import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Skuttler extends Player {
    private BufferedImage image;
    private BufferedImage bullet;
    private BufferedImage sword;
    private int bulletPosX;
    private int bulletPosY;
    private int ticker = 0;
    private double angle;
    private final int anchorX=38;
    private final int anchorY=37;
    private boolean isAlive = true;
    private AffineTransform transform = new AffineTransform();
    private CopyOnWriteArrayList<Machinegun> guns = new CopyOnWriteArrayList<>();
    private Rectangle hitbox;

    public Skuttler(int x, int y){
        super(x, y);
        super.setXVel(1);
        super.setYVel(1);
        super.setHealth(5);
        try{
            image = ImageIO.read(this.getClass().getResource("skuttler.png"));
            bullet = ImageIO.read(this.getClass().getResource("skuttler shot C.png"));
        } catch(IOException e){}
        hitbox = new Rectangle(image.getHeight(), image.getWidth(), x, y);
    }

    public void shoot(){
        ticker++;
        if (ticker%1000000==0){
            guns.add(new Machinegun(super.getxPos(), super.getyPos(), bullet, angle));
            ticker = 0;
        }
    }

    public Rectangle getHitbox(){
        return hitbox;
    }

    public void move(){
        if (super.getHealth()==0){
            isAlive = false;
        }
        if (isAlive) {
            super.move();
        }
        if (Main.mouse.getLMB()){
            this.shoot();
        }
        for (Machinegun gun : guns){
            gun.move();
        }
        hitbox.x = this.getxPos();
        hitbox.y = this.getyPos();
        angle=450-(Math.atan2(Main.mouse.getX()-(super.getxPos()+anchorX), Main.mouse.getY()-(super.getyPos()+anchorY))*180/Math.PI);
    }

    public void paint(Graphics2D g){
        transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle),super.getxPos()+anchorX,super.getyPos()+anchorY);
        transform.translate(super.getxPos(),super.getyPos());
        if (isAlive) {
            g.drawImage(image, transform, null);
        }
        for (Machinegun gun : guns){
            gun.paint(g);
        }
    }
}
