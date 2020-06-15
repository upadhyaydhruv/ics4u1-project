public interface Hittable { // pass an ArrayList<Hittable> of the things relevant to each thing
    class HitBox {
        private boolean round;
        private int x, y, w, h;
        private int rotateRad; // this assumes rotation about the x-y corner

        public HitBox(boolean round, int w, int h) {
            this.round = round;
            this.w = w;
            this.h = h;
        }

        public HitBox(boolean round, int w, int h, int x, int y, int rotateRad) {
            this.round = round;
            this.w = w;
            this.h = h;
            this.x = x;
            this.y = y;
            this.rotateRad = rotateRad;
        }

        public void update(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isTouching(HitBox other) {
            throw new RuntimeException("ahlie, dhruv, you messed up again (from John)");
        }

        public boolean isWithin(HitBox other) {
            throw new RuntimeException("hurry up");
        }

        // if needed, also store the velocity of an object and add an isPointingAt method
    }

    // this should return an existing HitBox in each class instead of creating a new one each time for performance reasons
    HitBox currentHitBox();
}
