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
                super.getxPos() += super.getxVel();

            }
            else if (p.getxPos() - super.getxPos() < 0) {
                super.getxPos() -=  super.getxVel();
            }
            else {
            }
            super.getxVel() += 1;

            if (p.getyPos() - this.yPos < 0) {
              yPos += yVel;
          }
           else if (p.getyPos() - this.yPos > 0) {
               yPos -= yVel;
          }
           else {

            }
           yVel += 1;
        }

        public void paint(Graphics2D g){g.drawImage(resizedImage, super.getxPos(), super.getyPos(), null);
        }
    }

