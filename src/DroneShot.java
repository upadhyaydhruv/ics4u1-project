import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

class DroneShot implements Hittable {
    private int x = 0, y = 0;
    long angle = 0;
    private boolean state = false;
    private final int anchorX = 24;
    private final int anchorY = 4;
    private Hittable.HitBox hb;
    private BufferedImage shot;

    public DroneShot() {
        try {
            shot = ImageIO.read(this.getClass().getResource("drone/drone shooter.png"));
        } catch (IOException e) {
        }
    }

    @Override
    public Hittable.HitBox currentHitBox() {
        return this.hb;
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
            this.x = x+7;
            this.y = y+27;
            this.angle = angle;
        }
    }

    public void move() {
        if (state) {

            x += Math.cos(Math.toRadians(angle))*35;
            y += Math.sin(Math.toRadians(angle))*35;

            if(x<0||x>960||y<0||y>720) state=false;
        }

    }

    public void paint(Graphics2D thisFrame, BufferedImage shot) {
        if (state) {
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(angle), x + anchorX, y + anchorY);
            transform.translate(x, y);
            thisFrame.drawImage(shot, transform, null);
        }
    }
}