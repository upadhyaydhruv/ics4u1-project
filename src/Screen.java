import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Screen {
    private String screenName;
    private final Menu menu = new Menu();
    private final LevelSelect levelSelect = new LevelSelect();
    private final Credits credits = new Credits();

    Screen(String screenName) {
        this.screenName = screenName;
    }

    void changeScreen(String screenName) {
        this.screenName = screenName;
        switch (screenName) {
            case "menu":
                menu.start();
            case "levelSelect":
                levelSelect.start();
            case "credits":
                credits.start();
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
        }

    }
}
