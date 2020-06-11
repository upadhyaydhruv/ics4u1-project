import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class ChaseRocket {
    private int x, y, angle,moveDelay,turnDelay;
    BufferedImage rocket;
    ChaseRocket(int x, int y, int angle){
        this.x=x;
        this.y=y;
        this.angle=angle;
        try{
            rocket = ImageIO.read(this.getClass().getResource("chase rocket.png"));
        }
        catch (IOException e){
            System.out.print("image not found!");
        }
    }
    void move(int targetX, int targetY){
        int angle= (int) ((Math.atan2(targetX+(x+40), targetY+(y+24))*180/Math.PI));

/*
        if (turnDelay==10000){
            if (targetAngle < angle) {
                angle++;
                if(angle>360)angle=0;
            }
            else if(targetAngle > angle){
                angle--;
                if(angle<0)angle=360;
            }
            turnDelay=0;
        }

 */
        if(moveDelay==10000) {
            x += Math.cos(Math.toRadians(angle)) * 2;
            y += Math.sin(Math.toRadians(angle)) * 2;
            moveDelay=0;
        }
        moveDelay++;
        turnDelay++;
    }
    public void paint(Graphics2D thisFrame) {
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(angle), x + 40, y + 24);
            transform.translate(x, y);
            thisFrame.drawImage(rocket, transform, null);
    }
}