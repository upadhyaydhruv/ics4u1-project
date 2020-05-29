import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Level1 {
    String nextScreen = "";

    private BufferedImage back,water,plat, barrels;
    Rectangle platRec = new Rectangle(150, 15, 650, 650);
    Rectangle barrelsRec = new Rectangle(253, 460, 100, 140);

    int[] waveHold=new int[3];

    Level1(){
        try {
            back = ImageIO.read(new File("res\\back button.png"));
            water = ImageIO.read(new File("res\\storm water.png"));
            plat = ImageIO.read(new File("res\\level 1 plat.png"));
            barrels = ImageIO.read(new File("res\\barrels.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }
    public void start() {
        nextScreen = "";

    }
    public String move() {

        waveHold=Screen.waveMove(waveHold);

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

        Screen.paint(barrelsRec,barrels,thisFrame);
        //player.paint(thisFrame);
    }
}