import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public interface HittableThing extends Thing { // pass an ArrayList<Hittable> of the things relevant to each thing
    class HitBox {
        private boolean round;
        private int x, y, w, h;
        private AffineTransform transform; // this assumes rotation about the x-y corner

        // because people forget simple things and there isn't time to reorganize stuff
        private boolean everChecked;
        private boolean everUpdated;

        public HitBox(boolean round, int w, int h) {
            this.round = round;
            this.w = w;
            this.h = h;
        }

        public HitBox(boolean round, int w, int h, int x, int y, AffineTransform transform) {
            this.round = round;
            this.w = w;
            this.h = h;
            this.x = x;
            this.y = y;
            this.transform = transform;
        }

        public void update(AffineTransform transform) {
            this.everUpdated = true;
            this.transform = transform;
        }

        public void update(int x, int y) {
            this.everUpdated = true;
            this.x = x;
            this.y = y;
        }

        public void update(int x, int y, AffineTransform transform) {
            this.everUpdated = true;
            this.x = x;
            this.y = y;
            this.transform = transform;
        }

        public void update(int x, int y, int width, int height) {
            this.everUpdated = true;
            this.x = x;
            this.y = y;
            this.w = width;
            this.h = height;
        }

        public void update(int x, int y, int width, int height, AffineTransform transform) {
            this.everUpdated = true;
            this.x = x;
            this.y = y;
            this.w = width;
            this.h = height;
            this.transform = transform;
        }

        public boolean isTouching(HitBox other) {
            if (this.everChecked && !this.everUpdated) {
            }//throw new RuntimeException("did you forget to actually update the hitbox?!?");
            this.everChecked = true;

            if (other == null) return false;
            return other.getShape().intersects(this.getShape().getBounds2D());
        }

        private Shape getShape() {
            if (this.round) {
                Shape shape = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
                if (transform != null) {
                    shape = transform.createTransformedShape(shape);
                }
                return shape;
            } else {
                Shape shape = new Rectangle(this.x, this.y, this.w, this.h);
                if (transform != null) {
                    shape = transform.createTransformedShape(shape);
                }
                return shape;
            }
        }

        public String toString() {
            String b = "";
            if (this.transform != null) {
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
                                if (Main.ENABLE_DEBUG_FEATURES) {
                                    //it lags: System.out.printf("at %s, %s @ (%s) hit %s @ (%s)\n", LocalDateTime.now(), a.getClass().getName(), ah, b.getClass().getName(), bh);
                                }
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
