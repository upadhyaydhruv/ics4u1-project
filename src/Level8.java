import java.awt.*;
import java.awt.image.BufferedImage;

public class Level8 extends Level {
    private BufferedImage plat, stone;

    Rectangle platRec;
    Rectangle stoneRec;
    boolean levelComplete;

    Player player;

    public Level8() {
        plat = this.loadImage("res/background/jungle2.png");
        stone = this.loadImage("res/rune stone.png");
    }
    private HealthBar healthBar;

    @Override
    public void createThings() {
        player = Main.newPlayer(555, 500);
        platRec = new Rectangle(0, 0, 960, 720);
        stoneRec = new Rectangle(580, 350, 132, 222);
        this.healthBar = new HealthBar(player);

        this.addThing(player);
    }

    @Override
    public String moveLevel() {
        if (Main.ENABLE_DEBUG_FEATURES && player.getHealth() == 0)
            System.out.println("player died");
        return null;
    }

    @Override
    public void paintLevelBack(Graphics2D g) {
        Screen.paint(platRec, plat, g);
        g.fillRect(stoneRec.x + 65, stoneRec.y + 50, 45, 100);
        Screen.paint(stoneRec, stone, g);
    }

    @Override
    public void paintLevelFront(Graphics2D g) {
        healthBar.paint(g);
    }

    @Override
    public void reset() {

    }
}
