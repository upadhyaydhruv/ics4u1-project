import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bulldog {

        private BufferedImage image;
        //private BufferedImage resizedImage;
        int frame = 0;

        int x, y, xVel, yVel;
        Player player;
        public Bulldog(Player player, int x, int y, int xVel, int yVel){

            this.x = x;
            this.y = y;
            this.xVel = xVel;
            this.yVel = yVel;
            this.player = player;

            try{
                image = ImageIO.read(this.getClass().getResource("esper.png"));
                // image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                // resizedImage = (BufferedImage) image;
            } catch(IOException e){
                System.out.print("there");
            }
        }

        public void move(Player player) {
            if (frame == 25000) {
                if (player.getyPos() - y > 0) {
                    y += yVel;

                } else if (player.getyPos() - y < 0) {
                    y -= yVel;
                } else {
                }

                if (player.getxPos() - x > 0) {
                    x += xVel;

                } else if (player.getxPos() - x < 0) {
                    x -= xVel;
                } else {
                }
                frame = 0;
            }
            frame ++;
        }

        public void paint(Graphics2D g){g.drawImage(image, x, y, null);
        }
    }

