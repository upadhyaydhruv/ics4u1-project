import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;
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

    public final long getCurrentMilliseconds() {
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

    public abstract void paintLevelBack(Graphics2D g); // override this for painting the level background
    public abstract void paintLevelFront(Graphics2D g); // override this for painting the level background

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
            this.hittableThings.remove(t);

        // NOTE(Patrick):
        // This is technically the correct thing to do, but it increases the chance of having null pointer exceptions on
        // badly designed Things. And, not having it won't currently cause issues since most Things only use the current
        // level for removing itself from it or for checking for other Things, not for modifying it directly.
        // t.setCurrentLevel(null);
    }

    public final List<Thing> getAllThings() { // do not change
        return Collections.unmodifiableList(this.things);
    }

    // NOTE(Patrick): use like ... .countThing(Bulldog.class) or countThing(Bulldog.class, Bomb.class)
    @SafeVarargs // all Class instances have isInstance (i.e. Class<>[] is safe)
    public final int countThing(Class<? extends Thing> ...tts) {
        int count = 0;
        for (Thing t: things)
            for (Class<? extends Thing> tt: tts)
                if (tt.isInstance(t))
                    count++;
        return count;
    }
    @SafeVarargs
    public final boolean hasThing(Class<? extends Thing> ...tts) {
        for (Thing t: things)
            for (Class<? extends Thing> tt: tts)
                if (tt.isInstance(t))
                    return true;
        return false;
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

    public abstract void reset(); // override this for custom stuff

    public final BufferedImage loadImage(String name) {
        return Thing.loadImage(name);
    }

    private boolean cleared;

    public final boolean isCleared() {
        return cleared;
    }

    public final void setCleared(boolean cleared) {
        this.cleared = cleared;
    }
}
