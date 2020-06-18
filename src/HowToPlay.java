import java.awt.*;
import java.awt.image.BufferedImage;

public class HowToPlay {
    String nextScreen = "";
    BufferedImage back, backButton, frameDelay;
    Rectangle backButtonRec = new Rectangle(60, 60, 80, 80);
    Rectangle frameDelayRec = new Rectangle(180, 180, 360, 80);

    private int Xoffset, Yoffset;

    HowToPlay() {
        back = Thing.loadImage("res/menu/menu back.png");
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
        Screen.paint(backButtonRec, backButton, thisFrame);
        Screen.paint(frameDelayRec, frameDelay, thisFrame);
    }
}