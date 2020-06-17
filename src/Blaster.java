import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Blaster implements HittableThing {
    private int x, y;
    long angle;
    private final int anchorX = 24;
    private final int anchorY = 4;
    private HittableThing.HitBox hb = new HittableThing.HitBox(false, 0, 0);
    private BufferedImage shot;
    AffineTransform transform = new AffineTransform();

    private int speed;

    public Blaster(BufferedImage shot, int x, int y, long angle, int speed) {
        if (Main.ENABLE_DEBUG_FEATURES)
            System.out.println("new blaster was shot");

        this.shot = shot;

        this.x = x + 7;
        this.y = y + 27;
        this.angle = angle;
        this.speed = speed;
    }

    @Override
    public HittableThing.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(HittableThing hb) {
        return (hb instanceof Player);
    }

    @Override
    public void handleHit(HittableThing hb) {
        if (hb instanceof Player) {
            System.out.println("removing blaster");
            this.currentLevel.removeThing(this);
        }
    }

    @Override
    public void move() {
        x += Math.cos(Math.toRadians(angle)) * speed;
        y += Math.sin(Math.toRadians(angle)) * speed;

        if (x < 0 || x > 960 || y < 0 || y > 720) {
            System.out.println("removing blaster");
            this.currentLevel.removeThing(this);
        }

        this.transform.setToRotation(Math.toRadians(angle), x + anchorX, y + anchorY);
        this.transform.translate(x, y);
        this.hb.update(0, 0, transform); // zero since the transform already includes the damn rotation
    }

    @Override
    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(shot, this.transform, null);
    }

    Level currentLevel;

    @Override
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}
