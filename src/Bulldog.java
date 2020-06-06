import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bulldog extends Enemy {

        private BufferedImage image;
        //private BufferedImage resizedImage;


        int X,Y;
        public Bulldog(int x, int y){


            try{
                image = ImageIO.read(this.getClass().getResource("skuttler.png"));
                // image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                // resizedImage = (BufferedImage) image;
            } catch(IOException e){
                System.out.print("there");
            }
        }

        public void move(Player p){
            if (p.getxPos() - super.getxPos() > 0) {
                super.setxPos(super.getxPos() + super.getxVel());

            }
            else if (p.getxPos() - super.getxPos() < 0) {
                super.setxPos(super.getxPos() - super.getxVel());
            }
            else {
            }
            super.setxVel(super.getxVel() + 1);
            this.X = super.getxPos();

            if (p.getyPos() - super.getyPos() < 0) {
              super.setYPos(super.getyPos() + super.getyVel());;
          }
           else if (p.getyPos() - super.getyPos() > 0) {
               super.setYPos(super.getyPos() - super.getyVel());
          }
           else {

            }
           this.Y = super.getyPos();
           super.setxVel(super.getyVel() + 1);
        }

        public void paint(Graphics2D g){g.drawImage(image, super.getxPos(), super.getyPos(), null);
        }
    }

