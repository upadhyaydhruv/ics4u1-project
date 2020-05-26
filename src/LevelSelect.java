import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LevelSelect {
    String nextScreen = "";

    BufferedImage back, green, blue, orange, pink, backButton,testButton;
    Rectangle backButtonRec = new Rectangle(60, 60, 80, 80);
    Rectangle testButtonRec = new Rectangle(400, 60, 160, 80);
    int Xoffset, Yoffset;

    LevelSelect() {
        try {
            back = ImageIO.read(Menu.class.getResourceAsStream("menu back.png"));
            green = ImageIO.read(Menu.class.getResourceAsStream("green shard.png"));
            blue = ImageIO.read(Menu.class.getResourceAsStream("blue shard.png"));
            orange = ImageIO.read(Menu.class.getResourceAsStream("orange shard.png"));
            pink = ImageIO.read(Menu.class.getResourceAsStream("pink shard.png"));
            backButton = ImageIO.read(Menu.class.getResourceAsStream("back button.png"));
            testButton = ImageIO.read(Menu.class.getResourceAsStream("test button.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }

    public void start() {
        nextScreen = "";
        Xoffset = Main.mouse.getX() / 4;
        Yoffset = Main.mouse.getY() / 4;
    }

    public String move() {
        if(Main.mouse.isMouseOn()){
            Xoffset = Main.mouse.getX() / 4;
            Yoffset = Main.mouse.getY() / 4;
        }
        if(Main.mouse.getLMB()) {
            if (Main.mouse.intersects(backButtonRec)) {
                nextScreen = "menu";
            }
            if (Main.mouse.intersects(testButtonRec)) {
                nextScreen = "testLevel";
            }
        }
        return nextScreen;
    }

    public void paint(Graphics2D thisFrame) {

        thisFrame.drawImage(back, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);
        thisFrame.drawImage(green, 225 - (Xoffset / 4), 50 + (Yoffset / 4), 100, 500, null);
        thisFrame.drawImage(blue, 375 - (Xoffset / 3), 50 + (Yoffset / 3), 100, 500, null);
        thisFrame.drawImage(orange, 525 - (Xoffset / 2), 50 + (Yoffset / 2), 100, 500, null);
        thisFrame.drawImage(pink, 750 - (Xoffset), 50 + (Yoffset), 100, 500, null);

        thisFrame.drawImage(backButton, backButtonRec.x, backButtonRec.y, backButtonRec.width, backButtonRec.height,
                null);
        thisFrame.drawImage(testButton, testButtonRec.x, testButtonRec.y, testButtonRec.width, testButtonRec.height,
                null);
    }
}