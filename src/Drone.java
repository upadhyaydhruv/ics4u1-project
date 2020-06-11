import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Drone extends Enemy{
    private int x, y,  currentShot=1,delayCount=1000000000;
    private long angle;
    private BufferedImage drone,shooter,shot;
    //DroneShot[] droneShot = new DroneShot[20];
    DroneShot droneShot = new DroneShot();

    private int DIAMETER = 63;
    private Rectangle rec;

    public Drone(int x, int y) {

        super.setxPos(x);
        super.setyPos(y);

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


        if ((super.getxPos() + super.getxVel() < 0) || (super.getxPos() + super.getxVel() > 1020 - DIAMETER)) {
            super.setxVel(super.getxVel()*-1);
        }
        if ((super.getyPos() + super.getyVel() < 0) || (super.getyPos() + super.getyVel() > 720 - DIAMETER)) {
            super.setyVel(super.getyVel()*-1);
        }

        super.setxPos(super.getxPos() + super.getxVel());

        super.setyPos(super.getyPos() + super.getyVel());





        //bobby sez: this updates the gun :P
        angle= (long) (450-(Math.atan2(targetX-(super.getxPos()+31), targetY-(super.getyPos()+31))*180/Math.PI));
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
        droneShot.shoot(super.getxPos(),super.getyPos(),angle);
        currentShot++;
        if(currentShot==20) currentShot=1;
    }
    public void paint(Graphics2D g) {
        droneShot.paint(g, shot);

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle),super.getxPos()+31,super.getyPos()+31);
        transform.translate(super.getxPos()+9,super.getyPos()+21);
        g.drawImage(shooter,transform, null);
        g.drawImage(drone, super.getxPos(), super.getyPos(), null);

        /*
        for (int i = 1; i < droneShot.length-1; i++) {
            droneShot[i].paint(g, shot);
        }
        */
    }



}

