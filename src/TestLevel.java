import java.awt.*;
import java.awt.image.BufferedImage;

public class TestLevel extends Level {
    BufferedImage water, plat, object, bullet;

    public TestLevel() {
        water = this.loadImage("res/background/storm water.png");
        plat = this.loadImage("res/background/test plat.png");
        object = this.loadImage("res/test object.png");
        bullet = this.loadImage("res/skuttler shot C.png");
    }

    private Player player;
    private Drone drone;
    private Bulldog bulldog;
    private ChaseRocket rocket;

    @Override
    public void createThings() {
        this.player = Main.newPlayer(0, 0);
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
        if (player.getHealth() == 0)
            System.out.println("player died");

        drone.updateTarget(this.player.getxPos(), this.player.getyPos());
        rocket.updateTarget(this.player.getxPos(), this.player.getyPos());
        bulldog.updateTarget(this.player);

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

    @Override
    public void reset() {

    }
}
