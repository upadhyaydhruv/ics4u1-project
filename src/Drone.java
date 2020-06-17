import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Drone implements Hittable {
    private int x, y, xVel, yVel, currentShot=1,delayCount=1000000000, delayCount1 = 12500;
    private long angle;
    private BufferedImage drone,shooter,shot;
    private Blaster droneShot;
    private Hittable.HitBox hb;
    private int health = 2;

    private int DIAMETER = 63;
    private Rectangle rec;

    public Drone(int x, int y, int xVel, int yVel) {

        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;

        try{
            drone = ImageIO.read(this.getClass().getResource("drone/drone.png"));
            shooter = ImageIO.read(this.getClass().getResource("drone/drone shooter.png"));
            shot = ImageIO.read(this.getClass().getResource("drone/drone shot.png"));
        } catch(IOException e){
            System.out.print("there");
        }
        droneShot = new Blaster(shot);
    }

    @Override
    public Hittable.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(Hittable hb) {
        return (hb instanceof Machinegun || hb instanceof Explosion);
    }

    @Override
    public void handleHit(Hittable hb) {
        if (hb instanceof Machinegun) {
            this.decreaseHealth(1);
        } else if (hb instanceof Explosion) {
            this.decreaseHealth(((Explosion) hb).getDamage());
        }
    }

    private void shoot(){
        droneShot.shoot(x,y,angle);
        currentShot++;
        if(currentShot==20) currentShot=1;
    }


    public void decreaseHealth(int diff) {
        this.health -= diff;
    }

    public void move(int targetX,int targetY) {

        if (delayCount1 == 12500) {

            if ((x + xVel < 0) || (x + xVel > 1020 - (2*DIAMETER))) {
                xVel *= -1;
            }
            if ((y + yVel < 0) || (y + yVel > 720 - (2*DIAMETER))) {
                yVel *= -1;
            }

            x += xVel;

            y += yVel;

            delayCount1 = 0;

        }

            delayCount1 ++;

        //bobby sez: this updates the gun :P
        angle= (long) (450-(Math.atan2(targetX-(x+31), targetY-(y+31))*180/Math.PI));
        delayCount++;
        if(delayCount>150000){
            droneShot.move();
            delayCount=0;
        }

        shoot();

    }

    public void paint(Graphics2D g) {
        droneShot.paint(g);

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle),x+31,y+31);
        transform.translate(x+9,y+21);
        g.drawImage(shooter,transform, null);
        g.drawImage(drone, x, y, null);
    }
}

