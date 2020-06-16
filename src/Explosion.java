import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Explosion implements Hittable {
    private BufferedImage pic;
    private int[] x = new int[5], y = new int[5];
    private int delay = 0;
    private Hittable.HitBox hb;
    private int damage = 2;

    Explosion(){
        try {
            pic = ImageIO.read(Menu.class.getResourceAsStream("explosion.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
        hb = new Hittable.HitBox(true, 0, 0);

    }

    public void setDamage(int in) {
        this.damage = in;
    }

    public int getDamage() {
        return this.damage;
    }

    public void trigger(int x,int y){
        if(delay<1){
            for(int a=0; a<5; a++){
                this.x[a]=(int) (Math.random()*50)+x;
                this.y[a]=(int) (Math.random()*50)+y;
            }
            delay=75;
        }
        hb.update(min(this.x), min(this.y), (max(this.x) - min(this.x)), (max(this.y) - min(this.y)));
    }

    @Override
    public Hittable.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(Hittable hb) {
        return false;
    }

    @Override
    public void handleHit(Hittable hb) {

    }

    private int max(int[] a) {
        int toReturn = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > toReturn) {
                toReturn = a[i];
            }
        }
        return toReturn;
    }

    private int min(int[] a) {
        int toReturn = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] < toReturn) {
                toReturn = a[i];
            }
        }
        return toReturn;
    }

    public void paint(Graphics2D thisFrame){
        if(delay>0){
            for(int a=0; a<5; a++){
                thisFrame.drawImage(pic, x[a],y[a],null);
            }
        delay--;
        }
    }
}