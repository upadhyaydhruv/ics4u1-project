import java.awt.*;
import java.awt.image.BufferedImage;

public class Level1 extends Level {
    private BufferedImage water, plat, barrels;

    Player player;
    Rectangle platRec;
    Rectangle barrelsRec;
    BubbleTube levelTrigger;

    int[] waveHold = new int[3];
    boolean levelComplete;

    public Level1() {
        water = this.loadImage("res/background/storm water.png");
        plat = this.loadImage("res/background/level 1 plat.png");
        barrels = this.loadImage("res/barrels.png");
    }
    private HealthBar healthBar;

    @Override
    public void createThings() {
        player = Main.newPlayer(435, 170);
        levelTrigger = new BubbleTube(30, 100);
        barrelsRec = new Rectangle(253, 460, 100, 140);
        platRec = new Rectangle(150, 15, 650, 650);
        this.healthBar = new HealthBar(player);

        this.addThing(player);
        this.addThing(levelTrigger);
    }

    @Override
    public String moveLevel() {
        Screen.waveMove(waveHold);
        if (levelTrigger.wasHit())
            return "L2";
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
