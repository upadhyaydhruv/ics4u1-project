import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Drone extends Enemy{
    private int x, y, angle, currentShot;
    private BufferedImage drone,shooter,shot;
    private final int anchorX=22, anchorY=10;
    private final DroneShot[] droneShot = new DroneShot[20];

    private int DIAMETER, width, height;
    private Rectangle rec;

    public Drone() {
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


        if ((x + super.getxVel() < 0) || (x + super.getxVel() > 1020 - DIAMETER)) super.setxVel(super.getyVel()*-1);
        if ((y + super.getyVel() < 0) || (y + super.getyVel() > 125)) super.setyVel(super.getyVel()*-1);
        x += super.getxVel();
        y += super.getyVel();


        //bobby sez: run this when you want the gun to shoot
        //shoot();


        //bobby sez: this updates the gun :P
        angle= (int) (450-(Math.atan2(targetX-(super.getxPos()+anchorX+9), targetY-(super.getyPos()+anchorY+21))*180/Math.PI));
        //for (int i = 0; i < droneShot.length-1; i++) {
        //    droneShot[i].move();
        //}
    }
    private void shoot(){
        droneShot[currentShot].shoot(super.getxPos(),super.getyPos(),angle);
        currentShot++;
        if(currentShot>20) currentShot=0;
    }
    public void paint(Graphics2D g) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle),super.getxPos()+anchorX+9,super.getyPos()+anchorY+21);
        transform.translate(super.getxPos()+9,super.getyPos()+21);
        g.drawImage(shooter,transform, null);
        g.drawImage(drone, super.getxPos(), super.getyPos(), null);
        //for (int i = 0; i < droneShot.length-1; i++) {
        //    droneShot[i].paint(g, shot);
        //}
    }
}
class DroneShot{
    private int x, y,angle=0;
    private boolean state=false;
    private final int anchorX =4;
    private final int anchorY =4;
    public void shoot(int x,int y,int angle){
        if(!state){
            state=true;
            this.x=x;
            this.y=y;
            this.angle=angle;
        }
    }
    public void move(){
        if(state){
            x += Math.cos(Math.toRadians(angle));
            y += Math.sin(Math.toRadians(angle));
        }
    }
    public void paint(Graphics2D thisFrame,BufferedImage shot){
        if(state){
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(angle),x+anchorX,y+anchorY);
            transform.translate(x,y);
            thisFrame.drawImage(shot,transform, null);
        }
    }
}