import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Machinegun implements HittableThing {
    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private BufferedImage image;
    private double angle;
    private final int anchorX = 0;
    private final int anchorY = 0;
    private HittableThing.HitBox hb;
    AffineTransform transform = new AffineTransform();

    public Machinegun(int xOrig, int yOrig, BufferedImage image, double angle) {
        this.image = image;
        this.xPos = xOrig;
        this.yPos = yOrig;
        this.angle = angle;
        this.xVel = (int) Math.ceil(Math.cos(Math.toRadians(angle)) * 5);
        this.yVel = (int) Math.ceil(Math.sin(Math.toRadians(angle)) * 5);
        hb = new HitBox(false, image.getWidth(), image.getHeight(), xPos, yPos, null);
    }

    @Override
    public HittableThing.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(HittableThing hb) {
        return false;
    }

    @Override
    public void handleHit(HittableThing hb) {

    }

    private long time;

    @Override
    public void move() {
        long lastTime = time;
        time = this.currentLevel.getCurrentMilliseconds();
        if (time - lastTime < 1) {
            return;
        }

        xPos += xVel;
        yPos += yVel;

        transform.setToRotation(Math.toRadians(angle), xPos + anchorX, yPos + anchorY);
        transform.translate(xPos, yPos);
        hb.update(0, 0, transform);

        if (hb.outOfBounds()) {
            if (Main.ENABLE_DEBUG_FEATURES)
                System.out.println("removing machinegun (bullet) because out of bounds");
            this.currentLevel.removeThing(this);
        }
    }

    public void paint(Graphics2D g) {
        g.drawImage(image, transform, null);
    }

    Level currentLevel;

    @Override
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}
