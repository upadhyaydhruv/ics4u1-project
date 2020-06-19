import java.awt.*;
import java.awt.image.BufferedImage;

public class BubbleTube implements HittableThing {
    Rectangle bubbleTubeRec;
    BufferedImage bubbleTube;
    BubblePart[] bubbleParts = new BubblePart[4];
    Rainbow color = new Rainbow();
    HitBox hb;

    BubbleTube(int x, int y) {
        bubbleTubeRec = new Rectangle(x, y, 108, 220);
        for (int a = 0; a < bubbleParts.length; a++) {
            bubbleParts[a] = new BubblePart(x + 50, y + 165, (a * 44) + 16);
        }
        bubbleTube = Thing.loadImage("res/bubbles/bubble tube.png");
        hb = new HittableThing.HitBox(false, 108, 220, x, y, null);
    }

    @Override
    public HitBox currentHitBox() {
        return hb;
    }

    private boolean hit;

    @Override
    public void handleHit(HittableThing hb) {
        if (hb instanceof Player) {
            this.hit = true;
        }
    }

    public boolean wasHit() {
        return this.hit;
    }

    @Override
    public boolean hittableBy(HittableThing hb) {
        return (hb instanceof Player);
    }

    static BufferedImage newBubble() {
        switch ((int) (Math.random() * 9)) {
            case 0:
                return Thing.loadImage("res/bubbles/bubbles1.png");
            case 1:
                return Thing.loadImage("res/bubbles/bubbles2.png");
            case 2:
                return Thing.loadImage("res/bubbles/bubbles3.png");
            case 3:
                return Thing.loadImage("res/bubbles/bubbles4.png");
            case 4:
                return Thing.loadImage("res/bubbles/bubbles4.png");
            case 5:
                return Thing.loadImage("res/bubbles/bubbles6.png");
            case 6:
                return Thing.loadImage("res/bubbles/bubbles7.png");
            case 7:
                return Thing.loadImage("res/bubbles/bubbles8.png");
            case 8:
                return Thing.loadImage("res/bubbles/bubbles9.png");
        }
        return null;
    }

    @Override
    public void move() {
        color.move();
        for (BubblePart bubblePart : bubbleParts) {
            bubblePart.move();
        }
    }

    @Override
    public void paint(Graphics2D thisFrame) {
        thisFrame.setColor(color.get());
        thisFrame.fillRect(bubbleTubeRec.x + 48, bubbleTubeRec.y + 60, 44, 98);
        for (BubblePart bubblePart : bubbleParts) {
            bubblePart.paint(thisFrame);
        }
        Screen.paint(bubbleTubeRec, bubbleTube, thisFrame);
    }

    Level currentLevel;

    @Override
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}

class BubblePart {
    int x, y, height, frame;
    BufferedImage currentBubble;

    BubblePart(int x, int y, int startHeight) {
        this.x = x;
        this.y = y;
        height = startHeight;
        currentBubble = BubbleTube.newBubble();
    }

    void move() {
        frame++;
        if (frame == 10000) {
            frame = 0;
            height++;
            if (height == 170) reset();
        }
    }

    void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(currentBubble, x, y - height, 44, 44, null);
    }

    void reset() {
        height = 0;
        currentBubble = BubbleTube.newBubble();
    }
}