import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Drone extends Enemy{
    private int x, y, width, height,angle;
    private int DIAMETER;
    private BufferedImage drone,shooter;
    private final int anchorX=22;
    private final int anchorY=10;

    private Rectangle rec;
    public Drone() {
        try{
            drone = ImageIO.read(this.getClass().getResource("drone.png"));
            shooter = ImageIO.read(this.getClass().getResource("drone shooter.png"));
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

        angle= (int) (450-(Math.atan2(targetX-(super.getxPos()+anchorX+9), targetY-(super.getyPos()+anchorY+21))*180/Math.PI));
    }
    public void paint(Graphics2D g) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle),super.getxPos()+anchorX+9,super.getyPos()+anchorY+21);
        transform.translate(super.getxPos()+9,super.getyPos()+21);
        g.drawImage(shooter,transform, null);
        g.drawImage(drone, super.getxPos(), super.getyPos(), null);
    }
}