import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bulldog implements HittableThing {

    BufferedImage pic, bulldogBall;
    //private BufferedImage resizedImage;
    private int x, y, xVel = 1, yVel = 1, currentShot = 1;
    private final int anchorX = 15;
    private final int anchorY = 15;
    private double angle;
    private int ticker = 0;
    private AffineTransform transform = new AffineTransform();
    private int random;
    private int health = 6; //Can be changed if needed
    private HittableThing.HitBox hb;

    Player player;


    Bulldog(int x, int y) {

        this.x = x;
        this.y = y;
        //this.player = player;
        this.random = (int) (Math.random() * 8) + 1;

        switch (random) {
            case 2:
                pic = Thing.loadImage("bulldog/bulldog B.png");
                break;
            case 3:
                pic = Thing.loadImage("bulldog/bulldog C.png");
                break;
            case 4:
                pic = Thing.loadImage("bulldog/bulldog D.png");
                break;
            case 5:
                pic = Thing.loadImage("bulldog/bulldog E.png");
                break;
            case 6:
                pic = Thing.loadImage("bulldog/bulldog F.png");
                break;
            case 7:
                pic = Thing.loadImage("bulldog/bulldog G.png");
                break;
            case 8:
                pic = Thing.loadImage("bulldog/bulldog H.png");
                break;
            default:
                pic = Thing.loadImage("bulldog/bulldog A.png");
                break;
        }
        bulldogBall = Thing.loadImage("bulldog/bulldog ball.png");

        hb = new HittableThing.HitBox(false, pic.getWidth(), pic.getHeight(), this.x, this.y, null);
    }

    public void setTarget(Player p) {
        this.player = p;
    }

    public void decreaseHealth(int diff) {
        this.health -= diff;
    }

    @Override
    public HittableThing.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(HittableThing hb) {
        return (hb instanceof Explosion || hb instanceof Machinegun);
    }

    @Override
    public void handleHit(HittableThing hb) {
        if (hb instanceof Explosion) {
            this.decreaseHealth(((Explosion) hb).getDamage());
        } else if (hb instanceof Machinegun) {
            this.decreaseHealth(1);
        }
    }

    private long time;

    @Override
    public void move() {
        long lastTime = time;
        long newTime = this.currentLevel.getCurrentMilliseconds();

        if (player == null) {
            throw new RuntimeException("please call updateTarget before move");
        }

        if (this.health <= 0) {
            if (Main.ENABLE_DEBUG_FEATURES)
                System.out.println("bulldog died");
            this.currentLevel.removeThing(this);
        }

        angle = 450 - (Math.atan2(player.getxPos() - (x + anchorX), player.getyPos() - (y + anchorY)) * 180 / Math.PI);

        if (ticker == 10000) {
            if (player.getxPos() < x) {
                x -= xVel;
            } else if (player.getxPos() > x) {
                x += xVel;
            }

            if (player.getyPos() < y) {
                y -= yVel;
            } else if (player.getyPos() > y) {
                y += yVel;
            }
            ticker = 0;
        } else {
            ticker++;
        }

        if (newTime - lastTime > 2000) {
            time = newTime;
            if (Main.ENABLE_DEBUG_FEATURES)
                System.out.println("bulldog shot");
            angle = (long) (450 - (Math.atan2(player.getxPos() - (x + 31), player.getyPos() - (y + 31)) * 180 / Math.PI));
            this.currentLevel.addThing(new Blaster(bulldogBall, x, y, (long) angle, 1));
        }

        transform.setToRotation(Math.toRadians(angle), x + anchorX, y + anchorY);
        transform.translate(x, y);
        hb.update(0, 0, transform);
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(pic, transform, null);
    }


    Level currentLevel;

    @Override
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}