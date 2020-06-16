import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Missile implements Hittable {
    private BufferedImage image;
    private int xPos;
    private int yPos;
    private int damage = 0;
    private int xVel; //These are just placeholders for testing right now
    private int yVel;
    private final int anchorX=30;
    private final int anchorY=30;
    private int ticker = 0; //This is to slow done speed of moving graphics objects using modular arithmetic
    private boolean RMBToggle=false;
    private double angle;
    private Hittable.HitBox hb;


    public Missile(int xOrig, int yOrig, double angle) {
        try {
            image = ImageIO.read(this.getClass().getResource("tiamat rocket.png"));
        } catch(IOException e) {}
        this.angle = angle;
        this.xVel = (int)Math.ceil(Math.cos(Math.toRadians(angle))*5);
        this.yVel = (int)Math.ceil(Math.sin(Math.toRadians(angle))*5);
        this.xPos = xOrig;
        this.yPos = yOrig;
    }

    public void flipVel(){
        xVel = -xVel;
        yVel = -yVel;
    }

    public int getDamage(){
        return this.damage;
    }

    public int getxPos(){
        return this.xPos;
    }

    public int getyPos(){
        return this.yPos;
    }


    public void move(){
        if (Main.mouse.getRMB()&&RMBToggle){
            this.flipVel();
            RMBToggle=false;
        }
        if (!Main.mouse.getRMB()){
            RMBToggle=true;
        }


        ticker++;
        if (ticker%25000==0) {
            xPos += xVel;
            yPos += yVel;
        }

        if (ticker%1000000==0){
            damage++;
            System.out.println(damage);
        }
    }

    public void paint(Graphics2D g){
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle),xPos+anchorX,yPos+anchorY);
        transform.translate(xPos,yPos);
        g.drawImage(image, transform, null);
    }

    public Hittable.HitBox currentHitBox() {
        return hb;
    }
    }

