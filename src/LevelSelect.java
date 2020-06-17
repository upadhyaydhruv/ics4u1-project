import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LevelSelect {
    String nextScreen = "";
    BufferedImage back, green, blue, orange, pink, backButton, testButton, L1Button, L2Button, L3Button, L4Button, L5Button, L6Button, L7Button, L8Button;
    Rectangle backButtonRec = new Rectangle(60, 60, 80, 80);
    Rectangle testButtonRec = new Rectangle(400, 60, 160, 80);
    Rectangle L1ButtonRec = new Rectangle(100, 200, 160, 80);
    Rectangle L2ButtonRec = new Rectangle(300, 200, 160, 80);
    Rectangle L3ButtonRec = new Rectangle(500, 200, 160, 80);
    Rectangle L4ButtonRec = new Rectangle(700, 200, 160, 80);
    Rectangle L5ButtonRec = new Rectangle(100, 350, 160, 80);
    Rectangle L6ButtonRec = new Rectangle(300, 350, 160, 80);
    Rectangle L7ButtonRec = new Rectangle(500, 350, 160, 80);
    Rectangle L8ButtonRec = new Rectangle(700, 350, 160, 80);
    int Xoffset, Yoffset;
    boolean mousetoggle = false;

    LevelSelect() {
        try {
            back = ImageIO.read(Menu.class.getResourceAsStream("menu/menu back.png"));
            green = ImageIO.read(Menu.class.getResourceAsStream("menu/green shard.png"));
            blue = ImageIO.read(Menu.class.getResourceAsStream("menu/blue shard.png"));
            orange = ImageIO.read(Menu.class.getResourceAsStream("menu/orange shard.png"));
            pink = ImageIO.read(Menu.class.getResourceAsStream("menu/pink shard.png"));
            backButton = ImageIO.read(Menu.class.getResourceAsStream("button/back button.png"));
            testButton = ImageIO.read(Menu.class.getResourceAsStream("button/test button.png"));
            L1Button = ImageIO.read(Menu.class.getResourceAsStream("button/level 1 button.png"));
            L2Button = ImageIO.read(Menu.class.getResourceAsStream("button/level 2 button.png"));
            L3Button = ImageIO.read(Menu.class.getResourceAsStream("button/level 3 button.png"));
            L4Button = ImageIO.read(Menu.class.getResourceAsStream("button/level 4 button.png"));
            L5Button = ImageIO.read(Menu.class.getResourceAsStream("button/level 5 button.png"));
            L6Button = ImageIO.read(Menu.class.getResourceAsStream("button/level 6 button.png"));
            L7Button = ImageIO.read(Menu.class.getResourceAsStream("button/level 7 button.png"));
            L8Button = ImageIO.read(Menu.class.getResourceAsStream("button/level 8 button.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }

    public void start() {
        nextScreen = "";
        Xoffset = Main.mouse.getX() / 4;
        Yoffset = Main.mouse.getY() / 4;
        mousetoggle = false;
    }

    public String move() {
        if (Main.mouse.isMouseOn()) {
            Xoffset = Main.mouse.getX() / 4;
            Yoffset = Main.mouse.getY() / 4;
        }
        if (!Main.mouse.getLMB() && !mousetoggle) {
            mousetoggle = true;
        }
        if (Main.mouse.getLMB() && mousetoggle) {
            if (Main.mouse.intersects(backButtonRec)) nextScreen = "shipSelect";
            else if (Main.mouse.intersects(testButtonRec)) nextScreen = "testLevel";
            else if (Main.mouse.intersects(L1ButtonRec)) nextScreen = "L1";
            else if (Main.mouse.intersects(L2ButtonRec)) nextScreen = "L2";
            else if (Main.mouse.intersects(L3ButtonRec)) nextScreen = "L3";
            else if (Main.mouse.intersects(L4ButtonRec)) nextScreen = "L4";
            else if (Main.mouse.intersects(L5ButtonRec)) nextScreen = "L5";
            else if (Main.mouse.intersects(L6ButtonRec)) nextScreen = "L6";
            else if (Main.mouse.intersects(L7ButtonRec)) nextScreen = "L7";
            else if (Main.mouse.intersects(L8ButtonRec)) nextScreen = "L8";
        }
        return nextScreen;
    }

    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(back, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);
        thisFrame.drawImage(green, 225 - (Xoffset / 4), 50 + (Yoffset / 4), 100, 500, null);
        thisFrame.drawImage(blue, 375 - (Xoffset / 3), 50 + (Yoffset / 3), 100, 500, null);
        thisFrame.drawImage(orange, 525 - (Xoffset / 2), 50 + (Yoffset / 2), 100, 500, null);
        thisFrame.drawImage(pink, 750 - (Xoffset), 50 + (Yoffset), 100, 500, null);
        Screen.paint(backButtonRec, backButton, thisFrame);
        Screen.paint(testButtonRec, testButton, thisFrame);
        Screen.paint(L1ButtonRec, L1Button, thisFrame);
        Screen.paint(L2ButtonRec, L2Button, thisFrame);
        Screen.paint(L3ButtonRec, L3Button, thisFrame);
        Screen.paint(L4ButtonRec, L4Button, thisFrame);
        Screen.paint(L5ButtonRec, L5Button, thisFrame);
        Screen.paint(L6ButtonRec, L6Button, thisFrame);
        Screen.paint(L7ButtonRec, L7Button, thisFrame);
        Screen.paint(L8ButtonRec, L8Button, thisFrame);
    }
}