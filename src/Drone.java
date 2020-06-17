import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Drone implements HittableThing {
    private int x, y, xVel, yVel, currentShot = 1, delayCount1 = 12500;
    private long angle;
    private BufferedImage drone, shooter, shot;
    private Blaster droneShot;
    private HittableThing.HitBox hb = new HittableThing.HitBox(false, 0, 0);
    private int health = 2;
    AffineTransform transform = new AffineTransform();

    private int targetX;
    private int targetY;

    private int DIAMETER = 63;
    private Rectangle rec;

    public Drone(int x, int y, int xVel, int yVel) {

        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;

        try {
            drone = ImageIO.read(this.getClass().getResource("drone/drone.png"));
            shooter = ImageIO.read(this.getClass().getResource("drone/drone shooter.png"));
            shot = ImageIO.read(this.getClass().getResource("drone/drone shot.png"));
        } catch (IOException e) {
            System.out.print("there");
        }

    }

    @Override
    public HittableThing.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(HittableThing hb) {
        return (hb instanceof Machinegun || hb instanceof Explosion);
    }

    @Override
    public void handleHit(HittableThing hb) {
        if (hb instanceof Machinegun) {
            this.decreaseHealth(1);
        } else if (hb instanceof Explosion) {
            this.decreaseHealth(((Explosion) hb).getDamage());
        }
    }

    public void decreaseHealth(int diff) {
        this.health -= diff;
    }

    public void updateTarget(int x, int y) {
        this.targetX = x;
        this.targetY = y;
    }

    private long shootTime;
    private long moveTime;

    @Override
    public void move() {
        long currentTime = this.currentLevel.getCurrentMilliseconds();

        if (currentTime - moveTime > 5) {
            moveTime = currentTime;

            if ((x + xVel < 0) || (x + xVel > 1020 - (2 * DIAMETER))) {
                xVel *= -1;
            }
            if ((y + yVel < 0) || (y + yVel > 720 - (2 * DIAMETER))) {
                yVel *= -1;
            }

            x += xVel;

            y += yVel;
        }

        //bobby sez: this updates the gun :P
        angle = (long) (450 - (Math.atan2(targetX - (x + 31), targetY - (y + 31)) * 180 / Math.PI));

        if (currentTime - shootTime > 4000) {
            shootTime = currentTime;
            this.currentLevel.addThing(new Blaster(shot, x, y, angle, 2));
        }

        this.transform.setToRotation(Math.toRadians(angle), x + 31, y + 31);
        this.transform.translate(x + 9, y + 21);
        hb.update(0, 0, transform);
    }

    public void paint(Graphics2D g) {
        g.drawImage(shooter, transform, null);
        g.drawImage(drone, x, y, null);
    }

    Level currentLevel;

    @Override
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}

