import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class Screen {
    private String screenName;

    private final Menu menu = new Menu();
    private final ShipSelect shipSelect = new ShipSelect();
    private final LevelSelect levelSelect = new LevelSelect();
    private final Credits credits = new Credits();
    private final TestLevel testLevel = new TestLevel();
    private final Settings settings = new Settings();
    private final Level1 L1 = new Level1();
    private final Level2 L2 = new Level2();
    private final Level3 L3 = new Level3();
    private final Level4 L4 = new Level4();
    private final Level5 L5 = new Level5();
    private final Level6 L6 = new Level6();
    private final Level7 L7 = new Level7();
    private final Level8 L8 = new Level8();


    Screen(String screenName) {
        this.screenName = screenName;
    }

    void changeScreen(String screenName) {
        this.screenName = screenName;
        switch (screenName) {
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
            case "testLevel":
                testLevel.start();
                break;
            case "settings":
                settings.start();
                break;
            case "L1":
                L1.start();
                break;
            case "L2":
                L2.start();
                break;
            case "L3":
                L3.start();
                break;
            case "L4":
                L4.start();
                break;
            case "L5":
                L5.start();
                break;
            case "L6":
                L6.start();
                break;
            case "L7":
                L7.start();
                break;
            case "L8":
                L8.start();
        }
    }
    public String move() throws InterruptedException {

        switch (screenName) {
            case "menu":
                return menu.move();
            case "shipSelect":

                return  shipSelect.move();
            case "levelSelect":
                return levelSelect.move();
            case "credits":
                return credits.move();
            case "testLevel":
                return testLevel.move();
            case "settings":
                return settings.move();
            case "L1":
                return L1.move();
            case "L2":
                return L2.move();
            case "L3":
                return L3.move();
            case "L4":
                return L4.move();
            case "L5":
                return L5.move();
            case "L6":
                return L6.move();
            case "L7":
                return L7.move();
            case "L8":
                return L8.move();
        }
        Thread.sleep(frameDelay);
        return "";
    }
    public void paint(Graphics2D thisFrame) throws InterruptedException{

        switch (screenName) {
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
            case "testLevel":
                testLevel.paint(thisFrame);
                break;
            case "settings":
                settings.paint(thisFrame);
                break;
            case "L1":
                L1.paint(thisFrame);
            break;
                case "L2":
                L2.paint(thisFrame);
                    break;
            case "L3":
                L3.paint(thisFrame);
                break;
            case "L4":
                L4.paint(thisFrame);
                break;
            case "L5":
                L5.paint(thisFrame);
                break;
            case "L6":
                L6.paint(thisFrame);
                break;
            case "L7":
                L7.paint(thisFrame);
                break;
            case "L8":
                L8.paint(thisFrame);
        }
        Thread.sleep(frameDelay);
    }

    //bobby's secret code vault (i don't know where to put this so ima just leave it here for now)
    private int frameDelay = 10;
    public int getFrameDelay(){
        return frameDelay;
    }
    public void setFrameDelay(int frameDelay){
        this.frameDelay=frameDelay;
    }
    public static void paint(Rectangle rec, BufferedImage img, Graphics2D thisFrame) {
        thisFrame.drawImage(img, rec.x, rec.y, rec.width, rec.height, null);
    }
    public static int[] waveMove(int[] wave){
        if(wave[0]<30000000){
            wave[1]=(int)(wave[0]* 0.000002);
            wave[2]=(int)(wave[0]* 0.000001);
            wave[0]++;
        }
        else if(wave[0]<60000000) {
            wave[1] = 120+(int) (wave[0] * -0.000002);
            wave[2] = 60+(int) (wave[0] * -0.000001);
            wave[0]++;
        }
        else wave[0]=0;
        return wave;
    }
}