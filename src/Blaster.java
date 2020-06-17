import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Blaster implements Hittable {
    private int x = 0, y = 0;
    long angle = 0;
    private boolean state = false;
    private final int anchorX = 24;
    private final int anchorY = 4;
    private Hittable.HitBox hb = new Hittable.HitBox(false, 0, 0);
    private BufferedImage shot;
    AffineTransform transform = new AffineTransform();

    public Blaster(BufferedImage shot) {
        this.shot = shot;
    }

    @Override
    public Hittable.HitBox currentHitBox() {
        return state ? this.hb : null;
    }

    @Override
    public boolean hittableBy(Hittable hb) {
        return (hb instanceof Player);
    }

    @Override
    public void handleHit(Hittable hb) {
        if (hb instanceof Player) {
            this.state = false;
        }
    }

    public void shoot(int x, int y, long angle) {
        if (!state) {
            state = true;
            this.x = x + 7;
            this.y = y + 27;
            this.angle = angle;
        }
    }

    public void move() {
        if (state) {
            x += Math.cos(Math.toRadians(angle)) * 35;
            y += Math.sin(Math.toRadians(angle)) * 35;

            if (x < 0 || x > 960 || y < 0 || y > 720) state = false;
        }

        this.transform.setToRotation(Math.toRadians(angle), x + anchorX, y + anchorY);
        this.transform.translate(x, y);
        this.hb.update(0, 0, transform); // zero since the transform already includes the damn rotation
    }

    public void paint(Graphics2D thisFrame) {
        if (state) {
            thisFrame.drawImage(shot, this.transform, null);
        }
    }
}
