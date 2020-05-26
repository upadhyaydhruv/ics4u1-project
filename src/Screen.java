import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Screen {
    private String screenName;
    private final Menu menu = new Menu();
    private final LevelSelect levelSelect = new LevelSelect();
    private final Credits credits = new Credits();
    private final TestLevel testLevel = new TestLevel();

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
        }
        return "";
    }

    public void paint(Graphics2D thisFrame) {
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
        }

    }
}
