import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Level4 {
    String nextScreen = "";

    private BufferedImage water,plat, bubbleTube;
    Rectangle platRec = new Rectangle(150, 15, 650, 650);
    Rectangle bubbleTubeRec = new Rectangle(670, 50, 108, 220);

    //Skuttler player= new Skuttler(Main.keyboard,10,10);

    int[] waveHold=new int[3];

    Level4(){
        try {
            water = ImageIO.read(new File("res\\storm water.png"));
            plat = ImageIO.read(new File("res\\level 4 plat.png"));
            bubbleTube = ImageIO.read(new File("res\\bubble tube skeleton.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }
    public void start() {
        nextScreen = "";

    }
    public String move() {

        Screen.waveMove(waveHold);

        if(Main.mouse.isMouseOn()){

        }

        //player.move();

        if (Main.keyboard.getEsc()) {
            nextScreen = "levelSelect";
        }
        return nextScreen;
    }
    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(water, -60+waveHold[1], -60+waveHold[2], 1010, 1010, null);
        Screen.paint(platRec,plat,thisFrame);
        Screen.paint(bubbleTubeRec,bubbleTube,thisFrame);


        //player.paint(thisFrame);
    }
}