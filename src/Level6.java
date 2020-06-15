import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Level6 {
    String nextScreen = "";

    private BufferedImage water,plat, barrels;
    Rectangle platRec = new Rectangle(0, 0, 960, 720);
    Rectangle barrelsRec = new Rectangle(253, 460, 100, 140);



    int[] waveHold=new int[3];

    Level6(){
        try {
            water = ImageIO.read(new File("res\\background\\beach water.png"));
            plat = ImageIO.read(new File("res\\background\\beach2.png"));
            barrels = ImageIO.read(new File("res\\barrels.png"));
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

        //player.move();

        if (Main.keyboard.getEsc()) {
            nextScreen = "levelSelect";
        }
        return nextScreen;
    }
    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(water, -60+waveHold[1], -60+waveHold[2], 1010, 1010, null);
        Screen.paint(platRec,plat,thisFrame);
        Main.player.paint(thisFrame);
        //Screen.paint(barrelsRec,barrels,thisFrame);


        //player.paint(thisFrame);
    }
}