import java.awt.*;
import java.awt.image.BufferedImage;

public class Explosion implements HittableThing {
    private BufferedImage pic;
    private int[] x = new int[5], y = new int[5];
    private int delay = 0;
    private HittableThing.HitBox hb;
    private int damage = 2;

    Explosion(int x, int y) {
        pic = Thing.loadImage("explosion.png");
        hb = new HittableThing.HitBox(true, 0, 0);
        this.trigger(x, y);
    }

    public int getDamage() {
        return this.damage;
    }

    private void trigger(int x, int y) {
        if (delay < 1) {
            for (int a = 0; a < 5; a++) {
                this.x[a] = (int) (Math.random() * 50) + x;
                this.y[a] = (int) (Math.random() * 50) + y;
            }
            delay = 75;
        }
        hb.update(min(this.x), min(this.y), (max(this.x) - min(this.x)) + pic.getWidth(), (max(this.y) - min(this.y) + pic.getHeight()));
    }

    private boolean hasHit;

    @Override
    public HittableThing.HitBox currentHitBox() {
        if (hasHit)
            return null; // no hitbox if it has already hit something (so there isn't stacking dmg)
        return this.hb;
    }

    @Override
    public boolean hittableBy(HittableThing hb) {
        return (hb instanceof Player || hb instanceof Drone || hb instanceof Bulldog);
    }

    @Override
    public void handleHit(HittableThing hb) {
        hasHit = true; // note that this will only apply after it has checked the others (i.e. it can still hit multiple enemies at once)
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

    @Override
    public void paint(Graphics2D thisFrame) {
        if (delay > 0) {
            for (int a = 0; a < 5; a++) {
                thisFrame.drawImage(pic, x[a], y[a], null);
            }
            delay--;
        }
    }

    @Override
    public void move() {
        if (delay <= 0) {
            if (Main.ENABLE_DEBUG_FEATURES)
                System.out.println("missile delay is 0, so it's exploded, removing from level");
            this.currentLevel.removeThing(this);
        }
    }

    Level currentLevel;

    @Override
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}