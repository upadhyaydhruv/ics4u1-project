import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class Bomb implements HittableThing {
    RedGlow glow = new RedGlow();
    private BufferedImage bomb;
    private int x, y;
    private final HittableThing.HitBox hb;

    public Bomb(int x, int y) {
        if (Main.ENABLE_DEBUG_FEATURES)
            System.out.println(LocalDateTime.now().toString() + " bomb created");

        bomb = Thing.loadImage("landmine.png");

        hb = new HittableThing.HitBox(false, bomb.getHeight(), bomb.getWidth(), x, y, null);
        this.x = x;
        this.y = y;
    }

    public Bomb() {
        this((int) (Math.random() * 950), (int) (Math.random() * 700));
    }

    @Override
    public HittableThing.HitBox currentHitBox() {
        return this.hb;
    }

    @Override
    public boolean hittableBy(HittableThing hb) {
        return (hb instanceof Player || hb instanceof Machinegun || hb instanceof Missile);
    }

    @Override
    public void handleHit(HittableThing hb) {
        if (hb instanceof Player || hb instanceof Machinegun || hb instanceof Missile) {
            this.explode();
        }
    }

    private void explode() {
        this.currentLevel.addThing(new Explosion(x, y));
        this.currentLevel.removeThing(this);
        if (Main.ENABLE_DEBUG_FEATURES)
            System.out.println("bomb exploded, removed from level (don't use it anymore)");
    }

    private long explodeAt;

    @Override
    public void move() {
        long currentTime = this.currentLevel.getCurrentMilliseconds();
        if (explodeAt == 0) {
            explodeAt = currentTime + 2000;
        }

        //this advances the glow
        glow.move();

        if (currentTime >= explodeAt) {
            this.explode();
        }
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(glow.color);
        g.fillRect(x + 12, y + 3, 15, 6);
        g.drawImage(bomb, x, y, null);
    }

    Level currentLevel;

    @Override
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}