import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Date;
import java.util.List;

public interface HittableThing extends Thing { // pass an ArrayList<Hittable> of the things relevant to each thing
    class HitBox {
        private boolean round;
        private int x, y, w, h;
        // this assumes rotation about the x-y corner
        private boolean hasTransform;
        private final AffineTransform transform = new AffineTransform();

        public HitBox(boolean round, int w, int h) {
            update(round, w, h);
        }

        public HitBox(boolean round, int w, int h, int x, int y, AffineTransform transform) {
            update(round, w, h);
            update(x, y, transform);
        }

        public void update(AffineTransform transform) {
            // NOTE(Patrick): we need to copy it because repainting is done async, which means we might catch a hitbox
            // while it the reference to the transform is being update by remove. but, instead of allocating a new one
            // by clone()ing it each time (which will be slow due to Java's memory allocator and cause lag due to the
            // GC), we update a private one.
            this.hasTransform = transform != null;
            if (this.hasTransform)
                this.transform.setTransform(transform);
            else
                this.transform.setTransform(1, 0, 0, 1, 0, 0); // neutral transformation matrix
        }

        public void update(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void update(boolean round, int w, int h) {
            this.round = round;
            this.w = w;
            this.h = h;
        }

        public void update(int x, int y, int width, int height) {
            update(x, y);
            this.w = width;
            this.h = height;
        }

        public void update(int x, int y, AffineTransform transform) {
            update(x, y);
            update(transform);
        }

        public void update(int x, int y, int w, int h, AffineTransform transform) {
            update(x, y, w, h);
            update(transform);
        }

        public boolean isTouching(HitBox other) {
            if (other == null) return false;
            return other.getShape().intersects(this.getShape().getBounds2D());
        }

        private Shape getShape() {
            Shape shape = this.round
                    ? new Ellipse2D.Double(this.x, this.y, this.w, this.h)
                    : new Rectangle(this.x, this.y, this.w, this.h);
            if (this.hasTransform)
                shape = transform.createTransformedShape(shape);
            return shape;
        }

        public String toString() {
            String b = "";
            if (this.hasTransform) {
                Rectangle2D d = this.getShape().getBounds2D();
                b = String.format("[transformed=(%d,%d)+(%d,%d)]", (int) d.getX(), (int) d.getY(), (int) d.getWidth(), (int) d.getHeight());
            }
            return String.format("%s%s@(%d,%d)+(%d,%d)", this.round ? "Ellipse" : "Rectangle", b, this.x, this.y, this.w, this.h);
        }

        public void paintDebug(Graphics2D g) {
            Shape s = this.getShape();
            Stroke os = g.getStroke();
            Paint op = g.getPaint();
            g.setPaint(Color.RED);
            g.setStroke(new BasicStroke(4));
            g.draw(s);
            g.setStroke(os);
            g.setPaint(op);
        }

        public boolean outOfBounds() {
            Rectangle2D d = this.getShape().getBounds2D();
            return Screen.outOfBounds((int) d.getX(), (int) d.getY(), (int) d.getWidth(), (int) d.getHeight());
        }

        // if needed, also store the velocity of an object and add an isPointingAt method
    }

    static void paintDebugHits(Graphics2D g, List<HittableThing> things) {
        for (HittableThing thing: things) {
            HitBox b = thing.currentHitBox();
            if (b != null)
                b.paintDebug(g);
        }
    }

    static void handleHits(List<HittableThing> things) {
        // warning: the time this takes increases exponentially compared to the number of items, so try to limit the
        // number of hittable things. if needed, this can be made more efficient in many ways.
        HittableThing a, b;
        boolean ab, ba;
        HitBox ah, bh;
        for (int ai = 0; ai < things.size(); ai++) {
            a = things.get(ai);
            for (int bi = ai + 1; bi < things.size(); bi++) {
                b = things.get(bi);
                ab = a.hittableBy(b);
                ba = b.hittableBy(a);
                try {
                    if (ab || ba) {
                        if ((ah = a.currentHitBox()) != null && (bh = b.currentHitBox()) != null) {
                            if (ah.isTouching(bh)) { // note: this is (should be) the same as bh.isTouching(ah)
                                if (Main.ENABLE_DEBUG_FEATURES)
                                    System.out.printf("at %s, %s @ (%s) hit %s @ (%s)\n", new Date(), a.getClass().getName(), ah, b.getClass().getName(), bh);
                                if (ab)
                                    a.handleHit(b);
                                if (ba)
                                    b.handleHit(a);
                            }
                        }
                    }
                } catch (RuntimeException ex) {
                    System.out.printf("check hit for %s and %s: %s\n", a.getClass().getName(), b.getClass().getName(), ex.toString());
                }
            }
        }
    }

    // this should return an existing HitBox in each class instead of creating a new one each time for performance reasons
    HitBox currentHitBox();

    void handleHit(HittableThing hb);

    boolean hittableBy(HittableThing hb);
}
