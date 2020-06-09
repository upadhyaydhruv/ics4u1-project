import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Drone extends Enemy{
        private int x, y, width, height;
        private int DIAMETER;
        private BufferedImage image;

        public Drone() {
            try{
                image = ImageIO.read(this.getClass().getResource("drone.png"));
                // image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                // resizedImage = (BufferedImage) image;
            } catch(IOException e){
                System.out.print("there");
            }
        }

        public void paint(Graphics2D g) {
            g.drawImage(image, super.getxPos(), super.getyPos(), null);
        }

        public void move() {
                if ((x + super.getxVel() < 0) || (x + super.getxVel() > 1020 - DIAMETER)) super.setxVel(super.getyVel()*-1);
                if ((y + super.getyVel() < 0) || (y + super.getyVel() > 125)) super.setyVel(super.getyVel()*-1);

            x += super.getxVel();
            y += super.getyVel();
        }
}