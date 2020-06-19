import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

class Drone implements HittableThing {
    private int x, y, xVel, yVel;
    private long angle;
    private BufferedImage drone, shooter, shot;
    private HittableThing.HitBox hb = new HittableThing.HitBox(false, 0, 0);
    private int health = 2;
    private int delay = 3000;
    AffineTransform transform = new AffineTransform();

    private int DIAMETER = 63;

    Player target;

    public Drone(int x, int y, int xVel, int yVel) {
        drone = Thing.loadImage("drone/drone.png");
        shooter = Thing.loadImage("drone/drone shooter.png");
        shot = Thing.loadImage("drone/drone shot.png");

        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;
        hb = new HittableThing.HitBox(false, drone.getWidth(), drone.getHeight(), this.x, this.y, null);
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
    public void setXVel(int xVel) {
        this.xVel = xVel;
    }

    public void setYVel(int yVel) {
        this.yVel = yVel;
    }

    public int getXVel() {
        return this.xVel;
    }

    public int getYVel() {
        return this.yVel;
    }

    public int getDelay() {
        return this.delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void decreaseHealth(int diff) {
        this.health -= diff;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    private long shootTime;
    private long moveTime;

    @Override
    public void move() {
        long currentTime = this.currentLevel.getCurrentMilliseconds();

        if (this.health <= 0) {
            if (Main.ENABLE_DEBUG_FEATURES)
                System.out.println("drone died");
            this.currentLevel.removeThing(this);
        }

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
        angle = (long) (450 - (Math.atan2(this.target.getxPos() - (x + 31), this.target.getyPos() - (y + 31)) * 180 / Math.PI));

        if (currentTime - shootTime > delay) {
            shootTime = currentTime;
            this.currentLevel.addThing(new Blaster(shot, x, y, angle, 4));
        }

        this.transform.setToRotation(Math.toRadians(angle), x + 31, y + 31);
        this.transform.translate(x + 9, y + 21);
        hb.update(this.x, this.y);
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

