import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu {
    String nextScreen = "";
    BufferedImage bullets, slice, back, splat, startButton, creditsButton, settingsButton;
    Rectangle startButtonRec = new Rectangle(375, 220, 200, 80);
    Rectangle settingsButtonRec = new Rectangle(275, 420, 400, 80);
    Rectangle creditsButtonRec = new Rectangle(325, 520, 300, 80);
    int Xoffset, Yoffset;

    Menu() {
        try {
            bullets = ImageIO.read(Menu.class.getResourceAsStream("menu/bullets.png"));
            slice = ImageIO.read(Menu.class.getResourceAsStream("menu/green slice.png"));
            back = ImageIO.read(Menu.class.getResourceAsStream("menu/menu back.png"));
            splat = ImageIO.read(Menu.class.getResourceAsStream("menu/pink splat.png"));
            startButton = ImageIO.read(Menu.class.getResourceAsStream("button/start button.png"));
            creditsButton = ImageIO.read(Menu.class.getResourceAsStream("button/credits button.png"));
            settingsButton = ImageIO.read(Menu.class.getResourceAsStream("button/settings button.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
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
            if (Main.mouse.intersects(creditsButtonRec)) {
                nextScreen = "credits";
            }
            if (Main.mouse.intersects(settingsButtonRec)) {
                nextScreen = "settings";
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
    }
}