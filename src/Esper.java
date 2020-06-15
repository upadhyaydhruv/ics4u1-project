import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Esper extends Player implements Hittable {
    private BufferedImage esper,bullet;
    private int bulletPosX,bulletPosY,ticker = 0;
    private Machinegun shooter;
    private Hittable.HitBox hb;

    //bobby's angle update3.

    private double angle;
    private AffineTransform transform = new AffineTransform();
    private final int anchorX=5;
    private final int anchorY=5;
    private boolean isAlive = false;

    //anchor X and Y tell the program which pixel on esper.png it should rotate around
    // (this should be changed to fit different pictures in the future)
    private CopyOnWriteArrayList<Machinegun> guns = new CopyOnWriteArrayList<>();

    public Esper(int x, int y){
        super(x, y);
        super.setXVel(1);
        super.setYVel(1);
        super.setHealth(3);
        try{
            esper = ImageIO.read(this.getClass().getResource("esper.png"));
            bullet = ImageIO.read(this.getClass().getResource("esper shot.png"));
        } catch(IOException e){}

        // thomas, you need to get rid of the space around the esper, then set round to true
        hb = new Hittable.HitBox(false, esper.getHeight(), esper.getWidth(), x, y, 0);
    }

    public void shoot(){
        ticker++;
        if (ticker%1000000==0){
            guns.add(new Machinegun(super.getxPos(), super.getyPos(), bullet, angle));
            ticker = 0;
        }
    }

    public HitBox currentHitBox() {
        return hb;
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

        //bobby's angle update
        angle=450-(Math.atan2(Main.mouse.getX()-(super.getxPos()+anchorX), Main.mouse.getY()-(super.getyPos()+anchorY))*180/Math.PI);
        //(this part finds the angle between the player and the mouse)

    }

    public void paint(Graphics2D g){

        //bobby's angle update(these must be in this order)
        transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle),super.getxPos()+anchorX,super.getyPos()+anchorY);
        transform.translate(super.getxPos(),super.getyPos());
        g.drawImage(esper, transform, null);

        for (Machinegun gun : guns){
            gun.paint(g);
        }
    }
}