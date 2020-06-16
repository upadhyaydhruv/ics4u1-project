import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class Explosion implements Hittable  {
    BufferedImage pic;
    int[] x = new int[5], y = new int[5];
    int delay=0;
    private Hittable.HitBox hb;
    Explosion(){
        try {
            pic = ImageIO.read(Menu.class.getResourceAsStream("explosion.png"));
    }catch (IOException e) {
            System.out.println("image not found!");
        }
    }
    public void trigger(int x,int y){
        if(delay<1){
            for(int a=0; a<5; a++){
                this.x[a]=(int) (Math.random()*50)+x;
                this.y[a]=(int) (Math.random()*50)+y;
            }
            delay=75;
        }
    }
    public void paint(Graphics2D thisFrame){
        if(delay>0){
            for(int a=0; a<5; a++){
                thisFrame.drawImage(pic, x[a],y[a],null);
            }
        delay--;
        }
    }

    public Hittable.HitBox currentHitBox() {
        return hb;
    }
}