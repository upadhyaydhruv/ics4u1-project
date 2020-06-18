import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Missile implements Thing {
    private BufferedImage image;
    private int xPos;
    private int yPos;
    private int damage = 0;
    private int xVel; //These are just placeholders for testing right now
    private int yVel;
    private final int anchorX = 30;
    private final int anchorY = 30;
    private int ticker = 0; //This is to slow down speed of moving graphics objects using modular arithmetic
    private boolean RMBToggle = false;
    private double angle;
    AffineTransform transform = new AffineTransform();


    public Missile(int xOrig, int yOrig, double angle) {
        image = Thing.loadImage("tiamat rocket.png");

        this.angle = angle;
        this.xVel = (int) Math.ceil(Math.cos(Math.toRadians(angle)) * 10);
        this.yVel = (int) Math.ceil(Math.sin(Math.toRadians(angle)) * 10);
        this.xPos = xOrig;
        this.yPos = yOrig;
    }

    public void flipVel() {
        xVel = -xVel;
        yVel = -yVel;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getxPos() {
        return this.xPos;
    }

    public int getyPos() {
        return this.yPos;
    }

    @Override
    public void move() {
        if (Main.mouse.getRMB() && RMBToggle) {
            this.flipVel();
            RMBToggle = false;
        }
        if (!Main.mouse.getRMB()) {
            RMBToggle = true;
        }

        if (Main.mouse.getLMB()) {
            ticker++;
            if (ticker % 3750 == 0) {
                xPos += xVel;
                yPos += yVel;
            }

            if (ticker % 10000000 == 0) {
                damage++;
                System.out.println(damage);
            }
        }

        transform.setToRotation(Math.toRadians(angle), xPos + anchorX, yPos + anchorY);
        transform.translate(xPos, yPos);
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(image, transform, null);
    }

    Level currentLevel;

    @Override
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}

