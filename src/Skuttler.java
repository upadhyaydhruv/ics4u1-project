import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Skuttler extends Player implements Hittable {
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
    private Hittable.HitBox hb;



    public Skuttler(int x, int y){
        super(x, y);
        super.setXVel(1);
        super.setYVel(1);
        super.setHealth(5);
        try{
            image = ImageIO.read(this.getClass().getResource("skuttler.png"));
            bullet = ImageIO.read(this.getClass().getResource("skuttler shot C.png"));
        } catch(IOException e){}
        hb = new Hittable.HitBox(false, image.getWidth(), image.getHeight(), super.getxPos(), super.getyPos(), null);
    }

    @Override
    public Hittable.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(Hittable hb) {
        return (hb instanceof Blaster || hb instanceof Explosion);
    }

    @Override
    public void handleHit(Hittable hb) {
        if (hb instanceof Blaster) {
            this.decreaseHealth(1);
        } else if (hb instanceof Explosion) {
            this.decreaseHealth(((Explosion) hb).getDamage());
        }
    }

    public void shoot(){
        ticker++;
        if (ticker%1000000==0){
            guns.add(new Machinegun(super.getxPos(), super.getyPos(), bullet, angle));
            ticker = 0;
        }
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

        angle=450-(Math.atan2(Main.mouse.getX()-(super.getxPos()+anchorX), Main.mouse.getY()-(super.getyPos()+anchorY))*180/Math.PI);
        hb.update(super.getxPos(), super.getyPos());
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
        hb.updateTransform(transform);
    }
}
