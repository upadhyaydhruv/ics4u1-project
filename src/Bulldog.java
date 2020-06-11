import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bulldog extends Enemy {

        private BufferedImage image;
        //private BufferedImage resizedImage;

        int frame = 0;

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

        public void move(Player p) {
            frame++;
            if (frame == 1000000) {
                frame = 0;

                if (p.getxPos() - super.getxPos() > 0) {
                    super.setxPos(super.getxPos() + super.getxVel());
                    this.X = super.getxPos();

                } else if (p.getxPos() - super.getxPos() < 0) {
                    super.setxPos(super.getxPos() - super.getxVel());
                    this.X = super.getxPos();
                } else {
                }
                //this.X = super.getxPos();
                super.setxVel(super.getxVel());

                if (p.getyPos() - super.getyPos() < 0) {
                    super.setyPos(super.getyPos() - super.getyVel());
                    this.Y = super.getyPos();
                } else if (p.getyPos() - super.getyPos() > 0) {
                    super.setyPos(super.getyPos() + super.getyVel());
                    this.Y = super.getyPos();
                } else {

                }
                //this.Y = super.getyPos();
                super.setyVel(super.getyVel());
                //System.out.println(super.getxPos()+","+super.getyPos());
            }
        }

        public void paint(Graphics2D g){g.drawImage(image, super.getxPos(), super.getyPos(), null);
        }
    }

