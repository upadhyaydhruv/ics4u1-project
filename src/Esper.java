import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Esper extends Player implements Hittable {
    private BufferedImage esper,bullet;
    private int bulletPosX,bulletPosY,ticker = 0;
    private Machinegun shooter;
    private Hittable.HitBox hb;
    private double angle;
    private AffineTransform transform = new AffineTransform();
    private final int anchorX=5;
    private final int anchorY=5;
    private boolean isAlive = false;
    private List<Hittable> hitList;

    //anchor X and Y tell the program which pixel on esper.png it should rotate around
    // (this should be changed to fit different pictures in the future)
    private CopyOnWriteArrayList<Machinegun> guns = new CopyOnWriteArrayList<>();

    public Esper(int x, int y, List<Hittable> h) {
        super(x, y);
        super.setXVel(1);
        super.setYVel(1);
        super.setHealth(3);
        try{
            esper = ImageIO.read(this.getClass().getResource("esper.png"));
            bullet = ImageIO.read(this.getClass().getResource("esper shot.png"));
        } catch(IOException e){}

        hb = new Hittable.HitBox(false, esper.getHeight(), esper.getWidth(), x, y, null);

        h.add(this);
    }

    public void shoot(){
        ticker++;
        if (ticker%1000000==0){
            guns.add(new Machinegun(super.getxPos(), super.getyPos(), bullet, angle));
            ticker = 0;
        }
    }

    @Override
    public Hittable.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(Hittable hb) {
        return (hb instanceof Explosion || hb instanceof Blaster);
    }

    @Override
    public void handleHit(Hittable hb) {
        if (hb instanceof Explosion) {
            super.decreaseHealth(((Explosion) hb).getDamage());
        } else if (hb instanceof Blaster) {
            super.decreaseHealth(1);
        }
    }

    public void move(){
        if (super.getHealth()==0){
            isAlive = false;
        }
        if (isAlive) {
            super.move();
        }

        hb.update(this.getxPos(), this.getyPos());

        if (Main.mouse.getLMB()){
            this.shoot();
        }
        for (Machinegun gun : guns){
            gun.move();
        }

        if (Main.mouse.getRMB()){ //Adds teleporting functionality with left-click
            super.setxPos(Main.mouse.getX()-15);
            super.setYPos(Main.mouse.getY()-5);

        }
        hb.update(super.getxPos(), super.getyPos());

        //bobby's angle update
        angle=450-(Math.atan2(Main.mouse.getX()-(super.getxPos()+anchorX), Main.mouse.getY()-(super.getyPos()+anchorY))*180/Math.PI);
        //(this part finds the angle between the player and the mouse)

        transform.setToRotation(Math.toRadians(angle),super.getxPos()+anchorX,super.getyPos()+anchorY);
        transform.translate(super.getxPos(),super.getyPos());
        hb.update(0, 0, transform);
    }

    public void paint(Graphics2D g){

        g.drawImage(esper, transform, null);
        for (Machinegun gun : guns){
            gun.paint(g);
        }
    }
}