import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class TestLevel extends Level {
    BufferedImage water, plat, object, bullet;

    public TestLevel() {
        water = this.loadImage("res/background/storm water.png");
        plat = this.loadImage("res/background/test plat.png");
        object = this.loadImage("res/test object.png");
        bullet = this.loadImage("res/skuttler shot C.png");
    }

    private Skuttler player;
    private Drone drone;
    private Bulldog bulldog;
    private ChaseRocket rocket;

    @Override
    public void createThings() {
        this.player = new Skuttler(500, 50);
        this.drone = new Drone(400, 400, 1, 1);
        this.bulldog = new Bulldog(0, 0);
        this.rocket = new ChaseRocket(600, 300, 90);

        this.addThing(player);
        this.addThing(drone);
        this.addThing(bulldog);
        this.addThing(rocket);
    }

    private long lastBombTime;

    @Override
    public String moveLevel() {
        drone.updateTarget(player.getxPos(), player.getyPos());
        rocket.updateTarget(player.getxPos(), player.getyPos());
        bulldog.updateTarget(player);

        long currentTime = this.getCurrentMilliseconds();
        if (currentTime - lastBombTime > 5000) {
            lastBombTime = currentTime;
            this.addThing(new Bomb());
        }

        return null;
    }

    @Override
    public void paintLevelBack(Graphics2D g) {
        g.drawImage(water, (1 / 8) - 60, (1 / 8) - 60, 1010, 1010, null);
    }

    @Override
    public void paintLevelFront(Graphics2D g) {
    }
}
