import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static jdk.nashorn.internal.objects.NativeMath.max;
import static jdk.nashorn.internal.objects.NativeMath.min;

public class Explosion implements Hittable {
    private BufferedImage pic;
    private int[] x = new int[5], y = new int[5];
    private int delay = 0;
    private Hittable.HitBox hb;

    Explosion(){
        try {
            pic = ImageIO.read(Menu.class.getResourceAsStream("explosion.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
        hb = new Hittable.HitBox(true, 0, 0);

    }
    public void trigger(int x,int y){
        if(delay<1){
            for(int a=0; a<5; a++){
                this.x[a]=(int) (Math.random()*50)+x;
                this.y[a]=(int) (Math.random()*50)+y;
            }
            delay=75;
        }
        hb.update((int) min(x), (int) min(y), (int) (max(x) - min(x)), (int) (max(y) - min(y)));
    }

    @Override
    public Hittable.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(Object obj) {
        return false;
    }

    @Override
    public void handleHit(Object obj) {

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