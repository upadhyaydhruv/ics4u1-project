import java.awt.*;
import java.awt.image.BufferedImage;

public class Menu {
    String nextScreen = "";
    BufferedImage bullets, slice, back, splat, startButton, creditsButton, settingsButton,howToButton;
    Rectangle startButtonRec = new Rectangle(375, 220, 200, 80);
    Rectangle settingsButtonRec = new Rectangle(275, 420, 400, 80);
    Rectangle creditsButtonRec = new Rectangle(325, 520, 300, 80);
    Rectangle howToButtonRec = new Rectangle(325, 320, 300, 80);
    int Xoffset, Yoffset;

    Menu() {
        bullets = Thing.loadImage("menu/bullets.png");
        slice = Thing.loadImage("menu/green slice.png");
        back = Thing.loadImage("menu/menu back.png");
        splat = Thing.loadImage("menu/pink splat.png");
        startButton = Thing.loadImage("button/start button.png");
        creditsButton = Thing.loadImage("button/credits button.png");
        settingsButton = Thing.loadImage("button/settings button.png");
        howToButton= Thing.loadImage("button/how to play button.png");
    }

    public void start() {
        nextScreen = "";
        Xoffset = Main.mouse.getX() / 24;
        Yoffset = Main.mouse.getY() / 24;
    }

    public String move() {
        if (Main.mouse.isMouseOn()) {
            Xoffset = Main.mouse.getX() / 24;
            Yoffset = Main.mouse.getY() / 24;
        }
        if (Main.mouse.getLMB()) {
            if (Main.mouse.intersects(startButtonRec)) {
                nextScreen = "shipSelect";
            }
            else if (Main.mouse.intersects(creditsButtonRec)) {
                nextScreen = "credits";
            }
            else if (Main.mouse.intersects(settingsButtonRec)) {
                nextScreen = "settings";
            }
            else if (Main.mouse.intersects(howToButtonRec)) {
                nextScreen = "howToPlay";
            }
        }
        return nextScreen;
    }

    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(back, Xoffset - 60, Yoffset - 60, 1010, 1010, null);
        thisFrame.drawImage(splat, (Xoffset / 2) + 80, (Yoffset / 2) + 50, 400, 500, null);
        thisFrame.drawImage(slice, 380 - (Xoffset / 2), 50 - (Yoffset / 2), 500, 300, null);
        thisFrame.drawImage(bullets, 120 - Xoffset, -50 - Yoffset, 800, 800, null);
        Screen.paint(startButtonRec, startButton, thisFrame);
        Screen.paint(creditsButtonRec, creditsButton, thisFrame);
        Screen.paint(settingsButtonRec, settingsButton, thisFrame);
        Screen.paint(howToButtonRec, howToButton, thisFrame);
    }
}