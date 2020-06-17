import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class Bulldog implements Hittable {

    BufferedImage pic, bulldogBall;
    //private BufferedImage resizedImage;
    private int frame = 0;
    private int x, y, xVel, yVel, currentShot = 1;
    private final int anchorX = 15;
    private final int anchorY = 15;
    private double angle;
    private AffineTransform transform = new AffineTransform();
    private int random;
    private int delayCount = 0;
    private int health = 3; //Can be changed if needed
    private Hittable.HitBox hb;
    private List<Hittable> hitList;
    private Blaster bigBall;


    //Player player;
    Bulldog(Player player, int x, int y, int xVel, int yVel, List<Hittable> h) {

        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;
        //this.player = player;
        this.random = (int) (Math.random() * 8) + 1;
        this.hitList = h;

        try {
            switch (random) {
                case 2:
                    pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog/bulldog B.png"));
                    break;
                case 3:
                    pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog/bulldog C.png"));
                    break;
                case 4:
                    pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog/bulldog D.png"));
                    break;
                case 5:
                    pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog/bulldog E.png"));
                    break;
                case 6:
                    pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog/bulldog F.png"));
                    break;
                case 7:
                    pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog/bulldog G.png"));
                    break;
                case 8:
                    pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog/bulldog H.png"));
                    break;
                default:
                    pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog/bulldog A.png"));
                    break;
            }
            bulldogBall = ImageIO.read(this.getClass().getResource("bulldog/bulldog ball.png"));
        } catch (IOException e) {
            System.out.print("there");
        }
        hb = new Hittable.HitBox(false, pic.getWidth(), pic.getHeight(), this.x, this.y, null);
        bigBall = new Blaster(bulldogBall);
        h.add(bigBall);
        h.add(this);
    }

    public void decreaseHealth(int diff) {
        this.health -= diff;
    }

    @Override
    public Hittable.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(Hittable hb) {
        return (hb instanceof Explosion || hb instanceof Machinegun);
    }

    @Override
    public void handleHit(Hittable hb) {
        if (hb instanceof Explosion) {
            this.decreaseHealth(((Explosion) hb).getDamage());
        } else if (hb instanceof Machinegun) {
            this.decreaseHealth(1);
        }
    }

    private void shoot() {
        bigBall.shoot(x, y, (long) angle);
        currentShot++;
        if (currentShot == 20) currentShot = 1;
    }

    public void move(Player player) {
        angle = 450 - (Math.atan2(player.getxPos() - (x + anchorX), player.getyPos() - (y + anchorY)) * 180 / Math.PI);
        // this.xVel = (int) Math.ceil(Math.cos(Math.toRadians(angle)));
        // this.yVel = (int) Math.ceil(Math.sin(Math.toRadians(angle)));

        frame++;
        if (frame == 25000) {
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
            frame = 0;
        }
        angle = (long) (450 - (Math.atan2(player.getxPos() - (x + 31), player.getyPos() - (y + 31)) * 180 / Math.PI));
        delayCount++;
        if (delayCount > 150000) {
            bigBall.move();
            delayCount = 0;
        }

        shoot();

        transform.setToRotation(Math.toRadians(angle), x + anchorX, y + anchorY);
        transform.translate(x, y);
        hb.update(0, 0, transform);
    }

    public void paint(Graphics2D g) {
        bigBall.paint(g);
        g.drawImage(pic, transform, null);
    }

}

