import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Level7 {
    String nextScreen = "";

    private BufferedImage plat, barrels;
    Rectangle platRec = new Rectangle(0, 0, 960, 720);
    Rectangle barrelsRec = new Rectangle(253, 460, 100, 140);

    Glow glow=new Glow();


    int[] waveHold=new int[3];

    Level7(){
        try {

            plat = ImageIO.read(new File("res/background/jungle1.png"));

        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }
    public void start() {
        nextScreen = "";
        Main.player.newPlayer(425,300);
    }
    public String move() {
        Main.player.move();
        glow.move();

        if(Main.mouse.isMouseOn()){

        }

        //player.move();

        if (Main.keyboard.getEsc()) {
            nextScreen = "levelSelect";
        }
        return nextScreen;
    }
    public void paint(Graphics2D thisFrame) {
        thisFrame.setColor(glow.get());
        thisFrame.fillRect(0,0,960, 720);
        Screen.paint(platRec,plat,thisFrame);
        Main.player.paint(thisFrame);
        //Screen.paint(barrelsRec,barrels,thisFrame);


        //player.paint(thisFrame);
    }
}