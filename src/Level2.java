import java.awt.*;
import java.awt.image.BufferedImage;

public class Level2 extends Level {
    private BufferedImage water, plat, barrel, radar;

    Player player;
    Rectangle platRec;
    Rectangle barrelsRec;
    Rectangle radarRec;
    boolean levelComplete;

    int[] waveHold = new int[3];

    public Level2() {
        water = this.loadImage("res/background/storm water.png");
        plat = this.loadImage("res/background/level 2 plat.png");
        barrel = this.loadImage("res/barrel.png");
        radar = this.loadImage("res/portable radar.png");
    }

    private HealthBar healthBar;

    @Override
    public void createThings() {
        player = Main.newPlayer(435, 170);
        platRec = new Rectangle(150, 15, 650, 650);
        barrelsRec = new Rectangle(430, 280, 40, 60);
        radarRec = new Rectangle(550, 15, 170, 130);
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
        Screen.paint(barrelsRec, barrel, g);
        Screen.paint(radarRec, radar, g);
    }

    @Override
    public void paintLevelFront(Graphics2D g) {
        healthBar.paint(g);
    }

    @Override
    public void reset() {

    }
}
