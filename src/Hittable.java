import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.time.LocalDateTime;
import java.util.List;

public interface Hittable { // pass an ArrayList<Hittable> of the things relevant to each thing
    class HitBox {
        private boolean round;
        private int x, y, w, h;
        private AffineTransform transform; // this assumes rotation about the x-y corner

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

        public void update(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void update(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.w = width;
            this.h = height;
        }

        public void updateTransform(AffineTransform transform){
            this.transform = transform;
        }

        public boolean isTouching(HitBox other) {
            if (other == null) return false;
            return other.getShape().intersects(this.getShape().getBounds2D());
        }

        private Shape getShape(){
            if (this.round){
                Shape shape = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
                if (transform!=null) {
                    shape = transform.createTransformedShape(shape);
                }
                return shape;
            }
            else{
                Shape shape = new Rectangle(this.x, this.y, this.w, this.h);
                if (transform!=null) {
                    shape = transform.createTransformedShape(shape);
                }
                return shape;
            }
        }

        public boolean isWithin(HitBox other) {
            if (other == null) return false;
            return other.getShape().contains(this.getShape().getBounds2D());
        }

        public String toString() {
            String b = "";
            if (this.transform != null) {
                Rectangle2D d = this.getShape().getBounds2D();
                b = String.format("[transformed=(%d,%d)+(%d,%d)]", (int) d.getX(), (int) d.getY(), (int) d.getWidth(), (int) d.getHeight());
            }
            return String.format("%s%s@(%d,%d)+(%d,%d)", this.round ? "Ellipse" : "Rectangle", b, this.x, this.y, this.w, this.h);
        }

        // if needed, also store the velocity of an object and add an isPointingAt method
    }

    static void handleHits(List<Hittable> things) {
        // warning: the time this takes increases exponentially compared to the number of items, so try to limit the
        // number of hittable things. if needed, this can be made more efficient in many ways.
        Hittable a, b;
        boolean ab, ba;
        HitBox ah, bh;
        for (int ai = 0; ai < things.size(); ai++) {
            a = things.get(ai);
            for (int bi = ai + 1; bi < things.size(); bi++) {
                b = things.get(bi);
                ab = a.hittableBy(b);
                ba = b.hittableBy(a);
                if (ab || ba) {
                    if ((ah = a.currentHitBox()) != null && (bh = b.currentHitBox()) != null) {
                        if (ah.isTouching(bh)) { // note: this is (should be) the same as bh.isTouching(ah)
                            System.out.printf("at %s, %s @ (%s) hit %s @ (%s)\n", LocalDateTime.now(), a.getClass().getName(), ah, b.getClass().getName(), bh);
                            if (ab)
                                a.handleHit(b);
                            if (ba)
                                b.handleHit(a);
                        }
                    }
                }
            }
        }
    }

    // this should return an existing HitBox in each class instead of creating a new one each time for performance reasons
    HitBox currentHitBox();

    void handleHit(Hittable hb);

    boolean hittableBy(Hittable hb);
}
