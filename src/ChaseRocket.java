import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class ChaseRocket implements Hittable {
    private int x, y, angle, moveDelay, turnDelay;
    private BufferedImage rocket;
    private AffineTransform transform = new AffineTransform();
    private Hittable.HitBox hb;
    private List<Hittable> h;

    ChaseRocket(int x, int y, int angle, List<Hittable> h) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        try {
            rocket = ImageIO.read(this.getClass().getResource("chase rocket.png"));
        } catch (IOException e) {
            System.out.print("image not found!");
        }
        hb = new Hittable.HitBox(false, rocket.getWidth(), rocket.getHeight(), this.x, this.y, null);
        this.h = h;
        h.add(this);
    }

    @Override
    public Hittable.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(Hittable hb) {
        return false;
    }

    @Override
    public void handleHit(Hittable hb) {
    }

    void move(int targetX, int targetY) {
        int targetAngle = (int) ((Math.atan2(targetX + (x + 40), targetY + (y + 24)) * 180 / Math.PI));


        if (turnDelay == 10000) {
            if (targetAngle < angle) {
                angle++;
                if (angle > 360) angle = 0;
            } else if (targetAngle > angle) {
                angle--;
                if (angle < 0) angle = 360;
            }
            turnDelay = 0;
        }


        if (moveDelay == 9000) {
            x += Math.cos(Math.toRadians(angle)) * 2;
            y += Math.sin(Math.toRadians(angle)) * 2;
            moveDelay = 0;
        }
        moveDelay++;
        turnDelay++;

        transform.setToRotation(Math.toRadians(angle), x + 40, y + 24);
        transform.translate(x, y);

        hb.update(this.x, this.y, transform);
    }

    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(rocket, transform, null);
    }
}