import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Smoke implements Thing {
    int x, y;
    SmokePart[] smokeParts = new SmokePart[10];

    Smoke(int x, int y) {
        this.x = x;
        this.y = y;
        for (int a = 0; a < smokeParts.length; a++) {
            smokeParts[a] = new SmokePart(x, y, a * 15);
        }
    }

    static BufferedImage newSmoke() {
        try {
            switch ((int) (Math.random() * 9)) {
                case 0:
                    return ImageIO.read(new File("res/smoke/smoke1.png"));
                case 1:
                    return ImageIO.read(new File("res/smoke/smoke2.png"));
                case 2:
                    return ImageIO.read(new File("res/smoke/smoke3.png"));
                case 3:
                    return ImageIO.read(new File("res/smoke/smoke4.png"));
                case 4:
                    return ImageIO.read(new File("res/smoke/smoke5.png"));
                case 5:
                    return ImageIO.read(new File("res/smoke/smoke6.png"));
                case 6:
                    return ImageIO.read(new File("res/smoke/smoke7.png"));
                case 7:
                    return ImageIO.read(new File("res/smoke/smoke8.png"));
                case 8:
                    return ImageIO.read(new File("res/smoke/smoke9.png"));
            }
        } catch (IOException e) {
            System.out.println("image not found!");
        }
        return null;
    }

    @Override
    public void move() {
        for (SmokePart smokePart : smokeParts) {
            smokePart.move();
        }
    }

    @Override
    public void paint(Graphics2D thisFrame) {
        for (SmokePart smokePart : smokeParts) {
            smokePart.paint(thisFrame);
        }
    }

    Level currentLevel;

    @Override
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}

class SmokePart {
    int x, y, height, frame;
    BufferedImage currentSmoke;

    SmokePart(int x, int y, int startHeight) {
        this.x = x;
        this.y = y;
        height = startHeight;
        currentSmoke = Smoke.newSmoke();
    }

    void move() {
        frame++;
        if (frame == 100000) {
            frame = 0;
            height++;
            if (height > 150) reset();
        }
    }

    void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(currentSmoke, x, y - height, 80, 80, null);
    }

    void reset() {
        height = 0;
        currentSmoke = Smoke.newSmoke();
    }
}