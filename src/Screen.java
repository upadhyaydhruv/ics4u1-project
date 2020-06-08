import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class Screen {
    private String screenName;
    private final Menu menu = new Menu();
    private final LevelSelect levelSelect = new LevelSelect();
    private final Credits credits = new Credits();
    private final TestLevel testLevel = new TestLevel();
    private final Settings settings = new Settings();
    private final Level1 L1 = new Level1();
    private final Level2 L2 = new Level2();
    private final Level3 L3 = new Level3();

    Screen(String screenName) {
        this.screenName = screenName;
    }
    void changeScreen(String screenName) {
        this.screenName = screenName;
        switch (screenName) {
            case "menu":
                menu.start();
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
        }
    }
    public String move() throws InterruptedException {
        switch (screenName) {
            case "menu":
                return menu.move();
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
        }
        Thread.sleep(frameDelay);
        return "";
    }
    public void paint(Graphics2D thisFrame) throws InterruptedException{
        switch (screenName) {
            case "menu":
                menu.paint(thisFrame);
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