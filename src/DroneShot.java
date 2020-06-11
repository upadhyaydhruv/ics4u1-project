import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

class DroneShot {
    private int x = 0, y = 0, angle = 0;
    private boolean state = false;
    private final int anchorX = 4;
    private final int anchorY = 4;

    DroneShot() {
    }

    public void shoot(int x, int y, int angle) {
        if (!state) {
            state = true;
            this.x = x;
            this.y = y;
            this.angle = angle;
        }
    }

    public void move() {
        if (state) {

            x += Math.cos(Math.toRadians(angle))*2;
            y += Math.sin(Math.toRadians(angle))*2;

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