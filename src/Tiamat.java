import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Tiamat extends Player {
    private BufferedImage image;
    private BufferedImage sword;
    private int bulletPosX;
    private int bulletPosY;
    private Missile shooter;
    private final boolean missileReleased = false;
    private final boolean isClicked = false;
    private int ticker = 0;

    private double angle;
    private final int anchorX=30;
    private final int anchorY=30;
    private boolean LMBPressed = true;

    private final ArrayList<Missile> missile = new ArrayList<>();

    public Tiamat(int x, int y){
        super(x, y);
        //this.sword = sword;
        super.setXVel(1);
        super.setYVel(1);
        try{
            image = ImageIO.read(this.getClass().getResource("tiamat.png"));
        } catch(IOException ignored){}
    }

    public void shoot() {
        ticker++;
        if (ticker % 1000000 == 0) {
            if (missile.size()==0) {
                missile.add(new Missile(super.getxPos(), super.getyPos()));
                ticker = 0;
            }
        }
    }

    public void move(){
        if (!Main.mouse.getLMB()){
            LMBPressed = false;
        }
        super.move();
        if (Main.mouse.getLMB()&&!LMBPressed){
            this.shoot();
            LMBPressed = true;

        }
        angle=450-(Math.atan2(Main.mouse.getX()-(super.getxPos()+anchorX), Main.mouse.getY()-(super.getyPos()+anchorY))*180/Math.PI);
        for (Missile missiles : missile){
            missiles.move();
            if (!Main.mouse.getLMB()&&missile.size()>0){
                missile.remove(missiles);
            }
        }


    }

    public void paint(Graphics2D g){
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle),super.getxPos()+anchorX,super.getyPos()+anchorY);
        transform.translate(super.getxPos(),super.getyPos());
        g.drawImage(image,transform, null);
        for (Missile missiles : missile){
            missiles.paint(g);
        }
    }
}