import java.awt.*;
import java.awt.image.BufferedImage;

public class Level8 extends Level {
    private BufferedImage plat, stone;

    Rectangle platRec;
    Rectangle stoneRec;

    Player player;

    public Level8() {
        plat = this.loadImage("res/background/jungle2.png");
        stone = this.loadImage("res/rune stone.png");
    }

    @Override
    public void createThings() {
        player = Main.newPlayer(555, 500);
        platRec = new Rectangle(0, 0, 960, 720);
        stoneRec = new Rectangle(580, 350, 132, 222);

        this.addThing(player);
    }

    @Override
    public String moveLevel() {
        if (player.getHealth() == 0)
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

    }

    @Override
    public void reset() {

    }
}
