import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Level4 {
    String nextScreen = "";

    private BufferedImage water,plat;
    Rectangle platRec = new Rectangle(150, 15, 650, 650);
    BubbleTube tube = new BubbleTube(670, 50);

    //Skuttler player= new Skuttler(Main.keyboard,10,10);

    int[] waveHold=new int[3];

    Level4(){
        try {
            water = ImageIO.read(new File("res\\background\\storm water.png"));
            plat = ImageIO.read(new File("res\\background\\level 4 plat.png"));

        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }
    public void start() {
        nextScreen = "";

    }
    public String move() {

        Screen.waveMove(waveHold);
        tube.move();

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


        tube.paint(thisFrame);


        //player.paint(thisFrame);
    }
}