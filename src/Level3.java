import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Level3 {
    String nextScreen = "";
    private BufferedImage water,plat, stack;
    Rectangle platRec = new Rectangle(150, 15, 650, 650);
    Rectangle stackRec = new Rectangle(150, 285, 100, 240);

    Smoke smoke1=new Smoke(120,320);
    Smoke smoke2=new Smoke(200,320);
    //Skuttler player= new Skuttler(Main.keyboard,10,10);

    int[] waveHold=new int[3];

    Level3(){
        try {
            water = ImageIO.read(new File("res\\background\\storm water.png"));
            plat = ImageIO.read(new File("res\\background\\level 3 plat.png"));
            stack = ImageIO.read(new File("res\\smoke stack.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }
    public void start() {
        nextScreen = "";
        Main.player.newPlayer(100,200);
    }
    public String move() {
        Main.player.move();
        Screen.waveMove(waveHold);
        if(Main.mouse.isMouseOn()){

        }
        smoke1.move();
        smoke2.move();
        //player.move();

        if (Main.keyboard.getEsc()) {
            nextScreen = "levelSelect";
        }
        return nextScreen;
    }
    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(water, -60+waveHold[1], -60+waveHold[2], 1010, 1010, null);
        Screen.paint(platRec,plat,thisFrame);
        Screen.paint(stackRec,stack,thisFrame);
        Main.player.paint(thisFrame);
        //player.paint(thisFrame);

        smoke1.paint(thisFrame);
        smoke2.paint(thisFrame);
    }
}