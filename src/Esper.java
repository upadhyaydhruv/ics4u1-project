import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Esper extends Player {
    private BufferedImage image;
    private BufferedImage bullet;
    private int bulletPosX;
    private int bulletPosY;
    private Machinegun shooter;
    private int ticker = 0;
    public Esper(int x, int y){
        super(x, y);
        super.setXVel(1);
        super.setYVel(1);
        try{
            image = ImageIO.read(this.getClass().getResource("esper.png"));
        } catch(IOException e){}
    }

    /*
    public void shoot(){
        Machinegun shot = new Machinegun(150, 150, bullet);
        shooter = shot;
    }
    */

    public void move(){
        super.move();

        if (Main.mouse.getLMB()){ //Adds teleporting functionality with left-click
            super.setxPos(Main.mouse.getX()-15);
            super.setYPos(Main.mouse.getY()-5);
            this.image = super.rotateImageByDegrees(this.image, Math.PI/4);
        }
    }

    public void paint(Graphics2D g){
        /*
        if (ticker%100000==0) {
            this.image = super.rotateImageByDegrees(image, (Math.PI) / 4);
            ticker = 0;
        }
        */
        ticker++;
        g.drawImage(image, super.getxPos(), super.getyPos(), null);
        //Main.rotate(this.image, (Math.PI)/4);
        //super.setPreviousState(super.getStates()[1]);
        //System.out.println(super.getStates()[0]+" "+super.getStates()[1]);
    }
}
