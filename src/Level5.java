import java.awt.*;
import java.awt.image.BufferedImage;

public class Level5 extends Level {
    private BufferedImage water, plat, barrels;

    Rectangle platRec;
    Rectangle barrelsRec;

    int[] waveHold = new int[3];

    Player player;

    public Level5() {
        water = this.loadImage("res/background/beach water.png");
        plat = this.loadImage("res/background/beach1.png");
        barrels = this.loadImage("res/barrels.png");
    }

    private HealthBar healthBar;

    @Override
    public void createThings() {
        player = Main.newPlayer(555, 500);
        platRec = new Rectangle(0, 0, 960, 720);
        barrelsRec = new Rectangle(253, 460, 100, 140);
        this.healthBar = new HealthBar(player);

        this.addThing(player);
    }

    @Override
    public String moveLevel() {
        Screen.waveMove(waveHold);
        if (Main.ENABLE_DEBUG_FEATURES && player.getHealth() == 0)
            System.out.println("player died");
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
        healthBar.paint(g);
    }

    @Override
    public void reset() {

    }
}
