import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Credits {
    String nextScreen = "";
    BufferedImage back, monitor, backButton;
    Rectangle backButtonRec = new Rectangle(60, 60, 80, 80);
    int Xoffset, Yoffset;

    Credits() {
        back = Thing.loadImage("res/menu/menu back.png");
        monitor = Thing.loadImage("res/menu/credit monitor.png");
        backButton = Thing.loadImage("res/button/back button.png");
    }

    public void start() {
        nextScreen = "";
        Xoffset = Main.mouse.getX() / 4;
        Yoffset = Main.mouse.getY() / 4;
    }

    public String move() {
        if (Main.mouse.isMouseOn()) {
            Xoffset = Main.mouse.getX() / 4;
            Yoffset = Main.mouse.getY() / 4;
        }
        if (Main.mouse.getLMB()) {
            if (Main.mouse.intersects(backButtonRec)) {
                nextScreen = "menu";
            }
        }
        return nextScreen;
    }

    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(back, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);
        thisFrame.drawImage(monitor, (Xoffset / 4) + 225, 50 + (Yoffset / 4), 500, 600, null);
        Screen.paint(backButtonRec, backButton, thisFrame);
    }
}