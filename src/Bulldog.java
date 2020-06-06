import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bulldog extends Enemy {

        private Image image;
        private BufferedImage resizedImage;
        private Player p;

        int X,Y;
        public Bulldog(int x, int y){


            try{
                this.image = ImageIO.read(this.getClass().getResource("bulldog.png"));
                image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                resizedImage = (BufferedImage) image;
            } catch(IOException e){}
        }

        public void move(){
            if (p.getxPos() - super.getxPos() > 0) {
                super.setxPos(super.getxPos() + super.getxVel());

            }
            else if (p.getxPos() - super.getxPos() < 0) {
                super.setxPos(super.getxPos() - super.getxVel());
            }
            else {
            }
            super.setxVel(super.getxVel() + 1);

            if (p.getyPos() - super.getyPos() < 0) {
              super.setYPos(super.getyPos() + super.getyVel());;
          }
           else if (p.getyPos() - super.getyPos() > 0) {
               super.setYPos(super.getyPos() - super.getyVel());
          }
           else {

            }
           super.setxVel(super.getyVel() + 1);
        }

        public void paint(Graphics2D g){g.drawImage(resizedImage, super.getxPos(), super.getyPos(), null);
        }
    }

