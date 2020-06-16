import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Machinegun implements Hittable {
    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private BufferedImage image;
    private int ticker = 0;
    private double angle;
    private final int anchorX=0;
    private final int anchorY=0;
    private Hittable.HitBox hb;

    public Machinegun(int xOrig, int yOrig, BufferedImage image, double angle){
        this.image = image;
        this.xPos = xOrig;
        this.yPos = yOrig;
        this.angle = angle;
        this.xVel = (int)Math.ceil(Math.cos(Math.toRadians(angle))*5);
        this.yVel = (int)Math.ceil(Math.sin(Math.toRadians(angle))*5);
    }

    public boolean hit(){
        return true;
    }

    public void move(){
        ticker++;
        if (ticker % 10000 == 0) {
            xPos += xVel;
            yPos += yVel;
            ticker = 0;
        }
    }

    public void paint(Graphics2D g){
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle),xPos+anchorX,yPos+anchorY);
        transform.translate(xPos,yPos);
        g.drawImage(image,transform,null);
    }
}
