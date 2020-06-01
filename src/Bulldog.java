import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bulldog extends Enemy {

        private Image image;
        private BufferedImage resizedImage;
        private Player p;
        public Bulldog(Player p, int xPos, int yPos, int xVel, int yVel){
            super(p, xPos, yPos, xVel, yVel);
            this.p = p;
            try{
                this.image = ImageIO.read(this.getClass().getResource("bulldog.png"));
                image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                resizedImage = (BufferedImage) image;
            } catch(IOException e){}
        }



        public void move(){
            if (p.getxPos() - this.xPos > 0) {
                xPos += xVel;
            }
            else if (p.getxPos() - this.xPos < 0) {
                xPos -= xVel;
            }
            else {
            }

            if (p.getyPos() - this.yPos < 0) {
                yPos += yVel;
            }
            else if (p.getyPos() - this.yPos > 0) {
                yPos -= yVel;
            }
            else {

            }
        }

        public void paint(Graphics2D g){g.drawImage(resizedImage, super.getxPos(), super.getyPos(), null);
        }
    }

