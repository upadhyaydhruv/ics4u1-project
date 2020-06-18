import java.awt.*;
import java.awt.image.BufferedImage;

public class StartScreen {
    String nextScreen = "";
    BufferedImage back, superDrive, leftClick, text;



    private int Xoffset, Yoffset;

    StartScreen() {
        back = Thing.loadImage("res/menu/menu back.png");
        superDrive = Thing.loadImage("res/menu/super drive.png");
        leftClick = Thing.loadImage("res/menu/left click.png");
        text = Thing.loadImage("res/menu/click to continue.png");

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
        if (Main.mouse.getLMB()) nextScreen = "menu";
        return nextScreen;
    }

    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(back, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);
        thisFrame.drawImage(superDrive,180-(Xoffset/10),30-(Yoffset/10),552,318,null);
        thisFrame.drawImage(leftClick,400,460,102,150,null);
        thisFrame.drawImage(text,300,630,320,28,null);
    }
}