import java.awt.*;
import java.awt.image.BufferedImage;

public class Level7 extends Level {
    private BufferedImage plat, barrels;

    Rectangle platRec;
    Rectangle barrelsRec;

    int[] waveHold = new int[3];

    Player player;

    public Level7() {
        plat = this.loadImage("res/background/jungle1.png");
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
        Screen.paint(platRec, plat, g);
    }

    @Override
    public void paintLevelFront(Graphics2D g) {
        healthBar.paint(g);
    }

    @Override
    public void reset() {

    }
}
