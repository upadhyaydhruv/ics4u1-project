import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Screen {
    private String screenName;
    private int lastX, lastY;
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
                menu.start(lastX, lastY);
            case "levelSelect":
                levelSelect.start(lastX, lastY);
            case "credits":
                credits.start(lastX, lastY);
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

    public void keyTyped(KeyEvent event) {
        switch (screenName) {
            case "menu":
                menu.keyTyped(event);
                break;
            case "levelSelect":
                levelSelect.keyTyped(event);
                break;
            case "credits":
                credits.keyTyped(event);
                break;
        }
    }

    public void keyReleased(KeyEvent event) {
        switch (screenName) {
            case "menu":
                menu.keyReleased(event);
                break;
            case "levelSelect":
                levelSelect.keyReleased(event);
                break;
            case "credits":
                credits.keyReleased(event);
                break;
        }
    }

    public void keyPressed(KeyEvent event) {
        switch (screenName) {
            case "menu":
                menu.keyPressed(event);
                break;
            case "levelSelect":
                levelSelect.keyPressed(event);
                break;
            case "credits":
                credits.keyPressed(event);
                break;
        }
    }

    public void mouseClicked(MouseEvent event) {
        switch (screenName) {
            case "menu":
                menu.mouseClicked(event);
                break;
            case "levelSelect":
                levelSelect.mouseClicked(event);
                break;
            case "credits":
                credits.mouseClicked(event);
                break;
        }
    }

    public void mousePressed(MouseEvent event) {
        switch (screenName) {
            case "menu":
                menu.mousePressed(event);
                break;
            case "levelSelect":
                levelSelect.mousePressed(event);
                break;
            case "credits":
                credits.mouseClicked(event);
                break;
        }
    }

    public void mouseReleased(MouseEvent event) {
        switch (screenName) {
            case "menu":
                menu.mouseReleased(event);
                break;
            case "levelSelect":
                levelSelect.mouseReleased(event);
                break;
            case "credits":
                credits.mouseReleased(event);
                break;
        }
    }

    public void mouseEntered(MouseEvent event) {
        switch (screenName) {
            case "menu":
                menu.mouseEntered(event);
                break;
            case "levelSelect":
                levelSelect.mouseEntered(event);
                break;
            case "credits":
                credits.mouseEntered(event);
                break;
        }
    }

    public void mouseExited(MouseEvent event) {
        switch (screenName) {
            case "menu":
                menu.mouseExited(event);
                break;
            case "levelSelect":
                levelSelect.mouseExited(event);
                break;
            case "credits":
                credits.mouseExited(event);
                break;
        }
    }

    public void mouseMoved(MouseEvent event) {
        lastX = event.getX();
        lastY = event.getY();
        switch (screenName) {
            case "menu":
                menu.mouseMoved(event);
                break;
            case "levelSelect":
                levelSelect.mouseMoved(event);
                break;
            case "credits":
                credits.mouseMoved(event);
                break;
        }
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }
}