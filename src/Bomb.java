import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class Bomb implements Hittable {
    public int frameDelay = 0;
    Explosion e = new Explosion();
    RedGlow glow = new RedGlow();
    private BufferedImage bomb, explosion;
    //private BufferedImage resizedImage;
    private final int frame = 0;
    private int x, y;
    private Boolean isPlaced = false;
    private final Boolean onTimer = false;
    private int ticktick = 0;
    private final Hittable.HitBox hb;
    //private int height =


    public Bomb(List<Hittable> h) {

        try {
            bomb = ImageIO.read(this.getClass().getResource("landmine.png"));
        } catch (IOException e) {
            System.out.print("there");
        }
        x = (int) (Math.random() * 960);
        y = (int) (Math.random() * 720);
        hb = new Hittable.HitBox(false, bomb.getHeight(), bomb.getWidth(), x, y, null);
        h.add(e);
    }

    public boolean getisPlaced() {
        return isPlaced;
    }

    @Override
    public Hittable.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(Hittable hb) {
        return (hb instanceof Player);
    }

    @Override
    public void handleHit(Hittable hb) {
        if (hb instanceof Player) {
            e.trigger(x, y);
            isPlaced = false;
            ticktick = 0;
        }
    }

    public void move() {
        if (!isPlaced) {
            x = (int) (Math.random() * 960);
            y = (int) (Math.random() * 720);
            isPlaced = true;
        }
        hb.update(x, y);
        //this advances the glow
        glow.move();
    }


    public void paint(Graphics2D g) {

        if (isPlaced) {
            g.setColor(glow.color);
            g.fillRect(x + 12, y + 3, 15, 6);
            g.drawImage(bomb, x, y, null);
            if (isPlaced) {
                ticktick++;
            }
        }

        if (ticktick >= 200) {
            e.trigger(x, y);
            isPlaced = false;
            ticktick = 0;
        }
        e.paint(g);
    }

}