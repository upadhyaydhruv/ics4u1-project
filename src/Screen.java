import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Screen {
    private String screenName;

    private final StartScreen start = new StartScreen();
    private final Menu menu = new Menu();
    private final ShipSelect shipSelect = new ShipSelect();
    private final LevelSelect levelSelect = new LevelSelect();
    private final Credits credits = new Credits();
    private final Settings settings = new Settings();
    private final HowToPlay howToPlay = new HowToPlay();

    private Level currentLevel;

    Screen(String screenName) {
        this.screenName = screenName;
    }

    void changeScreen(String screenName) {
        this.screenName = screenName;
        switch (screenName) {
            case "start":
                start.start();
                break;
            case "menu":
                menu.start();
                break;
            case "shipSelect":
                shipSelect.start();
                break;
            case "levelSelect":
                levelSelect.start();
                break;
            case "credits":
                credits.start();
                break;
            case "settings":
                settings.start();
                break;
            case "howToPlay":
                howToPlay.start();
                break;
                case "testLevel":
                currentLevel = new TestLevel();
                currentLevel.start();
                break;
            case "L1":
                currentLevel = new Level1();
                currentLevel.start();
                break;
            case "L2":
                currentLevel = new Level2();
                currentLevel.start();
                break;
            case "L3":
                currentLevel = new Level3();
                currentLevel.start();
                break;
            case "L4":
                currentLevel = new Level4();
                currentLevel.start();
                break;
            case "L5":
                currentLevel = new Level5();
                currentLevel.start();
                break;
            case "L6":
                currentLevel = new Level6();
                currentLevel.start();
                break;
            case "L7":
                currentLevel = new Level7();
                currentLevel.start();
                break;
            case "L8":
                currentLevel = new Level8();
                currentLevel.start();
                break;
        }
    }

    public String move() throws InterruptedException {
        switch (screenName) {
            case "start":
                return start.move();
            case "menu":
                return menu.move();
            case "shipSelect":
                return shipSelect.move();
            case "levelSelect":
                return levelSelect.move();
            case "credits":
                return credits.move();
            case "settings":
                return settings.move();
            case "howToPlay":
                return howToPlay.move();
            case "testLevel":
            case "L1":
            case "L2":
            case "L3":
            case "L4":
            case "L5":
            case "L6":
            case "L7":
            case "L8":
                if (currentLevel != null) {
                        return currentLevel.move();
                    }

        }
        return "";
    }

    public void paint(Graphics2D thisFrame) throws InterruptedException {
        switch (screenName) {
            case "start":
                start.paint(thisFrame);
                break;
            case "menu":
                menu.paint(thisFrame);
                break;
            case "shipSelect":
                shipSelect.paint(thisFrame);
                break;
            case "levelSelect":
                levelSelect.paint(thisFrame);
                break;
            case "credits":
                credits.paint(thisFrame);
                break;
            case "settings":
                settings.paint(thisFrame);
                break;
            case "howToPlay":
                howToPlay.paint(thisFrame);
                break;
            case "testLevel":
            case "L1":
            case "L2":
            case "L3":
            case "L4":
            case "L5":
            case "L6":
            case "L7":
            case "L8":
                if (currentLevel != null)
                    currentLevel.paint(thisFrame);
                break;
        }
        Thread.sleep(frameDelay);
    }

    private int frameDelay = 10;

    public int getFrameDelay() {
        return frameDelay;
    }

    public void setFrameDelay(int frameDelay) {
        this.frameDelay = frameDelay;
    }

    public static void paint(Rectangle rec, BufferedImage img, Graphics2D thisFrame) {
        thisFrame.drawImage(img, rec.x, rec.y, rec.width, rec.height, null);
    }

    public static int[] waveMove(int[] wave) {
        if (wave[0] < 30000000) {
            wave[1] = (int) (wave[0] * 0.000002);
            wave[2] = (int) (wave[0] * 0.000001);
            wave[0]++;
        } else if (wave[0] < 60000000) {
            wave[1] = 120 + (int) (wave[0] * -0.000002);
            wave[2] = 60 + (int) (wave[0] * -0.000001);
            wave[0]++;
        } else wave[0] = 0;
        return wave;
    }

    public static boolean outOfBounds(int x, int y) {
        return Screen.outOfBounds(x, y, 0, 0);
    }

    public static boolean outOfBounds(int x, int y, int w, int h) {
        return (x+w <= 0 || x >= 960 || y+h <= 0 || y >= 720);
    }
}