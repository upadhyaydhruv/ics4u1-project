import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bulldog {

        BufferedImage pic, bulldogBall;
        //private BufferedImage resizedImage;
        private int frame = 0;
        private int x, y, xVel, yVel,currentShot=1;
        private final int anchorX=5;
        private final int anchorY=5;
        private double angle;
        private AffineTransform transform;
        private int random;
        private int delayCount = 0;

        BulldogBall bigBall = new BulldogBall();


        //Player player;
        Bulldog(Player player, int x, int y, int xVel, int yVel){

            this.x = x;
            this.y = y;
            this.xVel = xVel;
            this.yVel = yVel;
            //this.player = player;
            this.random = (int)(Math.random()*8)+1;

            try{
                switch(random) {
                    case 2:
                        pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog\\bulldog B.png"));
                        break;
                    case 3:
                        pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog\\bulldog C.png"));
                        break;
                    case 4:
                        pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog\\bulldog D.png"));
                        break;
                    case 5:
                        pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog\\bulldog E.png"));
                        break;
                    case 6:
                        pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog\\bulldog F.png"));
                        break;
                    case 7:
                        pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog\\bulldog G.png"));
                        break;
                    case 8:
                        pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog\\bulldog H.png"));
                        break;
                    default :
                        pic = ImageIO.read(Menu.class.getResourceAsStream("bulldog\\bulldog A.png"));
                        break;
                }
                     bulldogBall = ImageIO.read(this.getClass().getResource("bulldog\\bulldog ball"));
                // image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                // resizedImage = (BufferedImage) image;
            } catch(IOException e){
                System.out.print("there");
            }
        }

    private void shoot(){
        bigBall.shoot(x,y, (long)angle);
        currentShot++;
        if(currentShot==20) currentShot=1;
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
            angle= (long) (450-(Math.atan2(player.getxPos()-(x+31), player.getyPos()-(y+31))*180/Math.PI));
            delayCount++;
            if(delayCount>150000){
                bigBall.move();
                delayCount=0;
            }
        }

        public void paint(Graphics2D g){
            bigBall.paint(g, bulldogBall);

            transform = new AffineTransform();
            transform.rotate(Math.toRadians(angle),x+anchorX,y+anchorY);
            transform.translate(x,y);
            g.drawImage(pic, transform, null);
            g.drawImage(bulldogBall, transform,null);
        }
    }

