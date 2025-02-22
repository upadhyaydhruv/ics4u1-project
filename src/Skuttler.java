import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Skuttler extends Player implements HittableThing {
    private BufferedImage image;
    private BufferedImage bullet;
    private double angle;
    private final int anchorX = 38;
    private final int anchorY = 37;
    private boolean isAlive = true;
    private AffineTransform transform = new AffineTransform();
    private HittableThing.HitBox hb;


    public Skuttler(int x, int y) {
        super(x, y, 7);

        image = Thing.loadImage("skuttler.png");
        bullet = Thing.loadImage("skuttler shot C.png");

        super.setXVel(4);
        super.setYVel(4);
        hb = new HittableThing.HitBox(false, image.getWidth(), image.getHeight(), super.getxPos(), super.getyPos(), null);

        this.setShootRate(1000);
    }

    @Override
    public HittableThing.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(HittableThing hb) {
        return (hb instanceof Blaster || hb instanceof Explosion);
    }

    @Override
    public void handleHit(HittableThing hb) {
        if (hb instanceof Blaster) {
            this.decreaseHealth(1);
        } else if (hb instanceof Explosion) {
            this.decreaseHealth(((Explosion) hb).getDamage());
        }
    }

    @Override
    public void shoot() {
        this.currentLevel.addThing(new Machinegun(super.getxPos(), super.getyPos(), bullet, angle));
    }

    @Override
    public void move() {
        if (super.getHealth() <= 0) {
            isAlive = false;
        }
        if (isAlive) {
            super.move();
        } else {
            return;
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
