import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Level {
    private final List<Thing> things = new CopyOnWriteArrayList<>();
    private final List<HittableThing> hittableThings = new CopyOnWriteArrayList<>();

    public abstract void createThings(); // should call addThing for each thing

    public abstract String moveLevel(); // override this for custom move logic (it will run before the things move)

    private Date start = new Date();
    private long currentMilliseconds;

    public long getCurrentMilliseconds() {
        return currentMilliseconds;
    }

    public final String move() {
        currentMilliseconds = new Date().getTime() - start.getTime();

        if (Main.keyboard.getEsc()) {
            return "levelSelect";
        }

        String n = this.moveLevel();

        HittableThing.handleHits(hittableThings);

        for (Thing t: things)
            t.move();

        return n == null ? "" : n;
    }

    public void paintLevelBack(Graphics2D g) {}; // override this for painting the level background
    public void paintLevelFront(Graphics2D g) {}; // override this for painting the level background

    public final void paint(Graphics2D g) {
        this.paintLevelBack(g);
        for (Thing t: things)
            t.paint(g);
        if (Main.ENABLE_DEBUG_FEATURES)
            HittableThing.paintDebugHits(g, hittableThings);
        this.paintLevelFront(g);
    }

    public final void addThing(Thing t) {
        if (Main.ENABLE_DEBUG_FEATURES)
            System.out.printf("Added %s (%s)\n", t.getClass().getName(), t);
        t.setCurrentLevel(this);
        this.things.add(t);
        if (t instanceof HittableThing)
            this.hittableThings.add((HittableThing)t);
    }

    public final void removeThing(Thing t) {
        if (Main.ENABLE_DEBUG_FEATURES)
            System.out.printf("Removed %s (%s)\n", t.getClass().getName(), t);
        this.things.remove(t);
        if (t instanceof HittableThing)
            this.hittableThings.remove((HittableThing)t);
        t.setCurrentLevel(null);
    }

    public final void start() {
        this.resetBase();
        this.reset();
    }

    public final void resetBase() {
        things.clear();
        hittableThings.clear();
        this.createThings();
    }

    public void reset() {}; // override this for custom stuff

    public BufferedImage loadImage(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (Exception ex) {
            System.out.println("error loading " + name);
            throw new RuntimeException(ex);
        }
    }

}
