import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Level6 extends Level {
    private BufferedImage water, plat, barrels;

    Rectangle platRec;
    Rectangle barrelsRec;

    int[] waveHold = new int[3];

    Player player;

    public Level6() {
        water = this.loadImage("res/background/beach water.png");
        plat = this.loadImage("res/background/beach2.png");
        barrels = this.loadImage("res/barrels.png");
    }

    @Override
    public void createThings() {
        player = Main.newPlayer(555, 500);
        platRec = new Rectangle(0, 0, 960, 720);
        barrelsRec = new Rectangle(253, 460, 100, 140);

        this.addThing(player);
    }

    @Override
    public String moveLevel() {
        if (player.getHealth() == 0)
            System.out.println("player died");
        Screen.waveMove(waveHold);
        return null;
    }

    @Override
    public void paintLevelBack(Graphics2D g) {
        g.drawImage(water, -60 + waveHold[1], -60 + waveHold[2], 1010, 1010, null);
        Screen.paint(platRec, plat, g);
        Screen.paint(barrelsRec, barrels, g);
    }

    @Override
    public void paintLevelFront(Graphics2D g) {

    }

    @Override
    public void reset() {

    }
}
