import java.awt.*;
import java.awt.image.BufferedImage;

public class Level4 extends Level {
    private BufferedImage water, plat;

    Player player;
    Rectangle platRec;
    BubbleTube tube;

    int[] waveHold = new int[3];

    public Level4() {
        water = this.loadImage("res/background/storm water.png");
        plat = this.loadImage("res/background/level 4 plat.png");
    }

    private HealthBar healthBar;

    @Override
    public void createThings() {
        player = Main.newPlayer(555, 500);
        platRec = new Rectangle(150, 15, 650, 650);
        tube = new BubbleTube(670, 50);
        this.healthBar = new HealthBar(player);

        this.addThing(player);
        this.addThing(tube);
    }

    @Override
    public String moveLevel() {
        Screen.waveMove(waveHold);
        if (player.getHealth() == 0)
            System.out.println("player died");
        return null;
    }

    @Override
    public void paintLevelBack(Graphics2D g) {
        g.drawImage(water, -60 + waveHold[1], -60 + waveHold[2], 1010, 1010, null);
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
