import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Esper extends Player implements HittableThing {
    private BufferedImage esper, bullet;
    private HittableThing.HitBox hb;

    //bobby's angle update3.

    private double angle;
    private AffineTransform transform = new AffineTransform();
    private final int anchorX = 5;
    private final int anchorY = 5;
    private boolean isAlive = false;

    //anchor X and Y tell the program which pixel on esper.png it should rotate around
    // (this should be changed to fit different pictures in the future)

    public Esper(int x, int y) {
        super(x, y);
        esper = Thing.loadImage("esper.png");
        bullet = Thing.loadImage("esper shot.png");

        super.setXVel(1);
        super.setYVel(1);
        super.setHealth(3);

        // thomas, you need to get rid of the space around the esper, then set round to true
        hb = new HittableThing.HitBox(false, esper.getHeight(), esper.getWidth(), x, y, null);

        this.setShootRate(1000);
    }

    @Override
    public void shoot() {
        if (this.currentLevel == null) {
            System.out.println("currentlevel null");
            return;
        }
        this.currentLevel.addThing(new Machinegun(super.getxPos(), super.getyPos(), bullet, angle));
    }

    @Override
    public HittableThing.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(HittableThing hb) {
        return (hb instanceof Explosion || hb instanceof Blaster);
    }

    @Override
    public void handleHit(HittableThing hb) {
        if (hb instanceof Explosion) {
            super.decreaseHealth(((Explosion) hb).getDamage());
        } else if (hb instanceof Blaster) {
            super.decreaseHealth(1);
        }
    }

    @Override
    public void move() {
        if (super.getHealth() == 0) {
            isAlive = false;
        }
        if (isAlive) {
            super.move();
        }

        hb.update(this.getxPos(), this.getyPos());

        if (Main.mouse.getRMB()) { //Adds teleporting functionality with left-click
            super.setxPos(Main.mouse.getX() - 15);
            super.setYPos(Main.mouse.getY() - 5);

        }
        hb.update(super.getxPos(), super.getyPos());

        //bobby's angle update
        angle = 450 - (Math.atan2(Main.mouse.getX() - (super.getxPos() + anchorX), Main.mouse.getY() - (super.getyPos() + anchorY)) * 180 / Math.PI);
        //(this part finds the angle between the player and the mouse)

        transform.setToRotation(Math.toRadians(angle), super.getxPos() + anchorX, super.getyPos() + anchorY);
        transform.translate(super.getxPos(), super.getyPos());
        hb.update(0, 0, transform);
    }

    public void paint(Graphics2D g) {
        g.drawImage(esper, transform, null);
    }

    Level currentLevel;

    @Override
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}