import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestLevel {
    String nextScreen = "";
    int Xoffset,Yoffset;
    BufferedImage water,plat,object, bullet;

    Rectangle platRec = new Rectangle(150, 15, 650, 650);
    Rectangle objectRec = new Rectangle(600, 50, 100, 120);
    private Esper player;
    //private Skuttler player;
    Missile testMissile; //Testing non-fully-functional missile for debugging - Dhruv
    //Machinegun test;
    //Bulldog bulldog = new Bulldog(5,5);

    TestLevel(){
        try {
            water = ImageIO.read(new File("res\\storm water.png"));
            plat = ImageIO.read(new File("res\\test plat.png"));
            object = ImageIO.read(new File("res\\test object.png"));
            bullet = ImageIO.read(new File("res\\skuttler shot C.png"));

        } catch (IOException e) {
            System.out.println("image not found!");
        }
        player = new Esper(100, 100);
        testMissile = new Missile(150, 150);
        //test = new Machinegun(200, 200);
    }
    public void start() {
        nextScreen = "";

    }
    public String move() {
        if(Main.mouse.isMouseOn()){


            }
        player.move();
        testMissile.move();
        //test.move();
        //bulldog.move(player);
        if (Main.keyboard.getEsc()) {
            nextScreen = "levelSelect";
        }
        return nextScreen;
    }
    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(water, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);

        Screen.paint(platRec,plat,thisFrame);

        thisFrame.drawImage(plat, platRec.x, platRec.y, platRec.width, platRec.height, null);
        thisFrame.drawImage(object, objectRec.x, objectRec.y, objectRec.width, objectRec.height, null);
        //test.paint(thisFrame);
        player.paint(thisFrame);
        testMissile.paint(thisFrame);
        //bulldog.paint(thisFrame);
    }
}