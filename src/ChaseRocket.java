import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ChaseRocket implements Thing {
    private int x, y, angle, moveDelay, turnDelay;
    BufferedImage rocket;
    AffineTransform transform = new AffineTransform();

    private int targetX;
    private int targetY;

    ChaseRocket(int x, int y, int angle) {
        rocket = Thing.loadImage("chase rocket.png");

        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    void updateTarget(int x, int y) {
        this.targetX = x;
        this.targetY = y;
    }

    @Override
    public void move() {
        int targetAngle = (int) ((Math.atan2(targetX + (x + 40), targetY + (y + 24)) * 180 / Math.PI));


        if (turnDelay == 1000) {
            if (targetAngle < angle) {
                angle++;
                if (angle > 360) angle = 0;
            } else if (targetAngle > angle) {
                angle--;
                if (angle < 0) angle = 360;
            }
            turnDelay = 0;
        }


        if (moveDelay == 900) {
            x += Math.cos(Math.toRadians(angle)) * 1;
            y += Math.sin(Math.toRadians(angle)) * 1;
            moveDelay = 0;
        }
        moveDelay++;
        turnDelay++;

        transform.setToRotation(Math.toRadians(angle), x + 40, y + 24);
        transform.translate(x, y);
    }

    @Override
    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(rocket, transform, null);
    }

    Level currentLevel;

    @Override
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}