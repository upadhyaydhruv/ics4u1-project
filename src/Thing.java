import java.awt.*;

public interface Thing {
    void paint(Graphics2D g);
    void move();
    void setCurrentLevel(Level currentLevel); // this will be called whenever a thing is added and called with null when it is removed
}
