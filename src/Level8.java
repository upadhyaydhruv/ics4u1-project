import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Level8 {
    String nextScreen = "";

    private BufferedImage plat, stone;
    Rectangle platRec = new Rectangle(0, 0, 960, 720);
    Rectangle stoneRec = new Rectangle(580, 350, 132, 222);

    Glow glow=new Glow();

    int[] waveHold=new int[3];

    Level8(){
        try {

            plat = ImageIO.read(new File("res\\background\\jungle2.png"));
            stone = ImageIO.read(new File("res\\rune stone.png"));
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
        glow.move();Screen.waveMove(waveHold);

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
        thisFrame.fillRect(stoneRec.x+65,stoneRec.y+50,45, 100);
        Screen.paint(stoneRec,stone,thisFrame);
        Main.player.paint(thisFrame);

        //player.paint(thisFrame);
    }
}