import java.awt.*;


public class Screen {
    private String screenName;
    private final Menu menu = new Menu();
    private final LevelSelect levelSelect = new LevelSelect();
    private final Credits credits = new Credits();
    private final TestLevel testLevel = new TestLevel();
    private final Settings settings = new Settings();
    private final Level1 L1 = new Level1();


    private int frameDelay = 10;
    public int getFrameDelay(){
        return frameDelay;
    }
    public void setFrameDelay(int frameDelay){
        this.frameDelay=frameDelay;
    }

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
        }
    }

    public String move() {
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
        }
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
        }
        Thread.sleep(frameDelay);
    }
}
