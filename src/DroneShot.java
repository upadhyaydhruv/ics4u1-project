import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

class DroneShot implements Hittable {
    private int x = 0, y = 0;
    long angle = 0;
    private boolean state = false;
    private final int anchorX = 24;
    private final int anchorY = 4;
    private Hittable.HitBox hb;

    public DroneShot() {
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

            /*
            x += Math.cos(Math.toRadians(angle));
            y += Math.sin(Math.toRadians(angle));
        */
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