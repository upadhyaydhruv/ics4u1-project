import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Drone extends Enemy{
    private int x, y, xVel, yVel, currentShot=1,delayCount=1000000000, delayCount1 = 12500;
    private long angle;
    private BufferedImage drone,shooter,shot;
    //DroneShot[] droneShot = new DroneShot[20];
    DroneShot droneShot = new DroneShot();

    private int DIAMETER = 63;
    private Rectangle rec;

    public Drone(int x, int y, int xVel, int yVel) {

        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;

        try{
            drone = ImageIO.read(this.getClass().getResource("drone\\drone.png"));
            shooter = ImageIO.read(this.getClass().getResource("drone\\drone shooter.png"));
            shot = ImageIO.read(this.getClass().getResource("drone\\drone shot.png"));
            // image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            // resizedImage = (BufferedImage) image;
        } catch(IOException e){
            System.out.print("there");
        }
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
        //

        //bobby sez: run this when you want the gun to shoot
        shoot();
        //


        //for (int i = 1; i < droneShot.length-1; i++) {
        //    droneShot[i].move();
        //}

    }
    private void shoot(){
        droneShot.shoot(x,y,angle);
        currentShot++;
        if(currentShot==20) currentShot=1;
    }
    public void paint(Graphics2D g) {
        droneShot.paint(g, shot);

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle),x+31,y+31);
        transform.translate(x+9,y+21);
        g.drawImage(shooter,transform, null);
        g.drawImage(drone, x, y, null);

        /*
        for (int i = 1; i < droneShot.length-1; i++) {
            droneShot[i].paint(g, shot);
        }
        */
    }



}

