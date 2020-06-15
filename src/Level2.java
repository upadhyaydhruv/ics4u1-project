import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Level2 {
    String nextScreen = "";

    private BufferedImage water,plat, barrel,radar;
    Rectangle platRec = new Rectangle(150, 15, 650, 650);
    Rectangle barrelsRec = new Rectangle(430, 280, 40, 60);
    Rectangle radarRec = new Rectangle(550, 15, 170, 130);

    int[] waveHold=new int[3];

    Level2(){
        try {

            water = ImageIO.read(new File("res\\background\\storm water.png"));
            plat = ImageIO.read(new File("res\\background\\level 2 plat.png"));
            barrel = ImageIO.read(new File("res\\barrel.png"));
            radar = ImageIO.read(new File("res\\portable radar.png"));
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
        Screen.paint(barrelsRec,barrel,thisFrame);
        Screen.paint(radarRec,radar,thisFrame);
        Main.player.paint(thisFrame);
        //player.paint(thisFrame);
    }
}