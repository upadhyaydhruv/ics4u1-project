import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestLevel {
    String nextScreen = "";
    int Xoffset,Yoffset;
    BufferedImage back,water,plat,object;
    Rectangle backButtonRec = new Rectangle(160, 60, 80, 80);
    Rectangle platRec = new Rectangle(150, 15, 650, 650);
    Rectangle objectRec = new Rectangle(600, 50, 100, 120);
    Skuttler player = new Skuttler(Main.keyboard, 100, 100);

    TestLevel(){
        try {
            back = ImageIO.read(new File("res\\back button.png"));
            water = ImageIO.read(new File("res\\storm water.png"));
            plat = ImageIO.read(new File("res\\test plat.png"));
            object = ImageIO.read(new File("res\\test object.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }
    public void start() {
        nextScreen = "";

    }
    public String move() {
        if(Main.mouse.isMouseOn()){
            if(Main.mouse.getLMB()&&Main.mouse.intersects(backButtonRec)) nextScreen = "levelSelect";

            }
        player.move();
        return nextScreen;
    }
    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(water, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);
        thisFrame.drawImage(back, backButtonRec.x, backButtonRec.y, backButtonRec.width, backButtonRec.height, null);
        thisFrame.drawImage(plat, platRec.x, platRec.y, platRec.width, platRec.height, null);
        thisFrame.drawImage(object, objectRec.x, objectRec.y, objectRec.width, objectRec.height, null);
        player.paint(thisFrame);
    }
}