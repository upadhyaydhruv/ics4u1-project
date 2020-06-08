import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Skuttler extends Player {
    private BufferedImage image;
    private BufferedImage bullet;
    private BufferedImage sword;
    private int bulletPosX;
    private int bulletPosY;
    private int ticker = 0;
    private double angle;
    private final int anchorX=38;
    private final int anchorY=37;
    private AffineTransform transform = new AffineTransform();
    private ArrayList<Machinegun> guns = new ArrayList<>();

    public Skuttler(int x, int y){
        super(x, y);
        super.setXVel(1);
        super.setYVel(1);
        try{
            image = ImageIO.read(this.getClass().getResource("skuttler.png"));
            bullet = ImageIO.read(this.getClass().getResource("skuttler shot C.png"));
        } catch(IOException e){}
    }

    public void shoot(){
        ticker++;
        if (ticker%1000000==0){
            guns.add(new Machinegun(super.getxPos(), super.getyPos()));
            ticker = 0;
        }
    }

    public void move(){
        super.move();
        if (Main.mouse.getLMB()){
            this.shoot();
        }
        for (Machinegun gun : guns){
            gun.move();
        }
        angle=450-(Math.atan2(Main.mouse.getX()-super.getxPos(), Main.mouse.getY()-super.getyPos())*180/Math.PI);
    }

    public void paint(Graphics2D g){
        transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle),super.getxPos()+anchorX,super.getyPos()+anchorY);
        transform.translate(super.getxPos(),super.getyPos());
        g.drawImage(image, transform, null);
        for (Machinegun gun : guns){
            gun.paint(g);
        }
    }
}
