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
        if (Main.ENABLE_DEBUG_FEATURES) {
            StackTraceElement[] st = Thread.currentThread().getStackTrace();
            System.out.printf("new blaster was shot by %s\n", st[2].getClassName());
        }

        this.shot = shot;

        this.x = x + (shot.getWidth() / 2);
        this.y = y + (shot.getHeight() / 2);
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
            if (Main.ENABLE_DEBUG_FEATURES)
                System.out.println("removing blaster");
            this.currentLevel.removeThing(this);
        }
    }

    long moveTime;

    @Override
    public void move() {
        long currentTime = this.currentLevel.getCurrentMilliseconds();
        if (currentTime - moveTime < 2) {
            return;
        }
        moveTime = currentTime;

        double dx = Math.cos(Math.toRadians(angle)) * (double) speed;
        double dy = Math.sin(Math.toRadians(angle)) * (double) speed;

        x += dx >= 0 ? Math.ceil(dx) : Math.floor(dx);
        y += dy >= 0 ? Math.ceil(dy) : Math.floor(dy);

        this.transform.setToRotation(Math.toRadians(angle), x + anchorX, y + anchorY);
        this.transform.translate(x, y);

        AffineTransform fake = (AffineTransform) this.transform.clone();
        fake.scale(1.8, 1.8);
        this.hb.update(0, 0, fake);

        if (hb.outOfBounds()) {
            if (Main.ENABLE_DEBUG_FEATURES)
                System.out.println("removing blaster because out of bounds");
            this.currentLevel.removeThing(this);
        }
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
