import java.awt.*;
import java.awt.image.BufferedImage;

public class Level3 extends Level {
    private BufferedImage water, plat, stack;

    Player player;
    Rectangle platRec;
    Rectangle stackRec;
    Smoke smoke1;
    Smoke smoke2;

    int[] waveHold = new int[3];

    public Level3() {
        water = this.loadImage("res/background/storm water.png");
        plat = this.loadImage("res/background/level 3 plat.png");
        stack = this.loadImage("res/smoke stack.png");
    }

    @Override
    public void createThings() {
        player = Main.newPlayer(555, 500);
        platRec = new Rectangle(150, 15, 650, 650);
        stackRec = new Rectangle(150, 285, 100, 240);
        smoke1 = new Smoke(120, 320);
        smoke2 = new Smoke(200, 320);

        this.addThing(player);
        this.addThing(smoke1);
        this.addThing(smoke2);
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
        Screen.paint(stackRec, stack, g);
    }

    @Override
    public void paintLevelFront(Graphics2D g) {

    }

    @Override
    public void reset() {

    }
}
