import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bulldog {

        BufferedImage pic, bulldogBall;
        //private BufferedImage resizedImage;
        private int frame = 0;
        private int x, y, xVel, yVel;
        private final int anchorX=5;
        private final int anchorY=5;
        private double angle;
        private AffineTransform transform;
        private int random;


        //Player player;
        Bulldog(Player player, int x, int y, int xVel, int yVel){

            this.x = x;
            this.y = y;
            this.xVel = xVel;
            this.yVel = yVel;
            //this.player = player;
            this.random = (int)(Math.random()*8);

            try{
                //switch(random) {
                ///    default:
                        pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog\\bulldog A.png"));
                //}
                // image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                // resizedImage = (BufferedImage) image;
            } catch(IOException e){
                System.out.print("there");
            }
        }

        public void move(Player player) {
            angle=450-(Math.atan2(player.getxPos()-(x+anchorX), player.getyPos()-(y+anchorY))*180/Math.PI);
            // this.xVel = (int) Math.ceil(Math.cos(Math.toRadians(angle)));
            // this.yVel = (int) Math.ceil(Math.sin(Math.toRadians(angle)));

            frame ++;
            if (frame == 25000) {
                if (player.getxPos()<x){
                    x -= xVel;
                }
                else if (player.getxPos()>x) {
                    x += xVel;
                }
                else {
                }

                if (player.getyPos()<y){
                    y -= yVel;
                }
                else if (player.getyPos()>y) {
                    y += yVel;
                }
                else {
                }
                frame = 0;
            }
        }

        public void paint(Graphics2D g){
            transform = new AffineTransform();
            transform.rotate(Math.toRadians(angle),x+anchorX,y+anchorY);
            transform.translate(x,y);
            //g.drawImage(bulldosdfg, transform, null);
        }
    }

