import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Tiamat extends Player implements HittableThing {
    private BufferedImage image;
    private boolean isAlive = true;
    private HittableThing.HitBox hb;

    private double angle;
    private final int anchorX = 15;
    private final int anchorY = 15;

    AffineTransform transform = new AffineTransform();


    public Tiamat(int x, int y) {
        super(x, y,5);

        image = Thing.loadImage("tiamat.png");

        //this.sword = sword;
        super.setXVel(1);
        super.setYVel(1);
        this.setShootRate(1000);
        hb = new HittableThing.HitBox(false, image.getWidth(), image.getHeight(), super.getxPos(), super.getyPos(), null);
    }

    public void shoot() {
        throw new RuntimeException("shooting a missile not implemented for tiamat");
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
            this.decreaseHealth(((Explosion) hb).getDamage());
        } else if (hb instanceof Blaster) {
            this.decreaseHealth(1);
        }
    }

    @Override
    public void move() {
        if (super.getHealth() <= 0) {
            isAlive = false;
        }
        if (isAlive) {
            super.move();
        }

        angle = 450 - (Math.atan2(Main.mouse.getX() - (super.getxPos() + anchorX), Main.mouse.getY() - (super.getyPos() + anchorY)) * 180 / Math.PI);

        transform.setToRotation(Math.toRadians(angle), super.getxPos() + anchorX, super.getyPos() + anchorY);
        transform.translate(super.getxPos(), super.getyPos());
        hb.update(0, 0, transform);
    }

    public void paint(Graphics2D g) {
        if (isAlive) {
            g.drawImage(image, transform, null);
        }
    }

    Level currentLevel;

    @Override
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}