import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;

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

        public boolean isTouching(HitBox other) {
            throw new RuntimeException("ahlie, dhruv, you messed up again (from John)");
        }

        private Shape getShape(){
            if (this.round){
                Ellipse2D shape = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
                transform.createTransformedShape(shape).getBounds2D();
                return shape;
            }
            else{
                Rectangle shape = new Rectangle(this.x, this.y, this.w, this.h);
                transform.createTransformedShape(shape).getBounds2D();
                return shape;
            }
        }

        public boolean isWithin(HitBox other) {
            throw new RuntimeException("hurry up");
        }

        // if needed, also store the velocity of an object and add an isPointingAt method
    }

    // this should return an existing HitBox in each class instead of creating a new one each time for performance reasons
    HitBox currentHitBox();
}
