import java.awt.*;
import java.awt.image.BufferedImage;

public class Level2 extends Level {
    private BufferedImage water, plat, barrel, radar;

    private Player player;
    private Rectangle platRec;
    private Rectangle barrelsRec;
    private Rectangle radarRec;
    private boolean levelComplete;
    private int ticker = 0;
    private int wave = 0;
    int[] waveHold = new int[3];

    public Level2() {
        water = this.loadImage("res/background/storm water.png");
        plat = this.loadImage("res/background/level 2 plat.png");
        barrel = this.loadImage("res/barrel.png");
        radar = this.loadImage("res/portable radar.png");
    }

    private HealthBar healthBar;

    private void addBulldog(int x, int y) {
        Bulldog b = new Bulldog(x, y);
        b.setTarget(player);
        this.addThing(b);
    }

    private void addDrone(int x, int y) {
        Drone d = new Drone(x, y, 1, 1);
        d.setTarget(player);
        this.addThing(d);
    }

    @Override
    public void createThings() {
        player = Main.newPlayer(435, 170);
        platRec = new Rectangle(150, 15, 650, 650);
        barrelsRec = new Rectangle(430, 280, 40, 60);
        radarRec = new Rectangle(550, 15, 170, 130);
        this.healthBar = new HealthBar(player);
        this.addThing(player);

        this.addThing(player);
        this.addDrone(400, 500);
        this.addDrone(100, 360);
        this.addDrone(940, 360);
        this.addDrone(220, 300);
    }

    @Override
    public String moveLevel() {
        ticker++;
        Screen.waveMove(waveHold);
        if (Main.ENABLE_DEBUG_FEATURES && player.getHealth() == 0)
            System.out.println("player died");

        if (ticker == 500 && wave != -1) {
            if (this.countThing(Bulldog.class, Drone.class) == 0) {
                wave++;
                if (Main.ENABLE_DEBUG_FEATURES)
                    System.out.printf("New Wave %d\n", wave);
                if (wave == 1) {
                    player.decreaseHealth(-1);
                    this.addBulldog(700, 0);
                    this.addBulldog(700, 500);
                    Bomb bomb1 = new Bomb(300, 300);
                    Bomb bomb2 = new Bomb(100, 100);
                    this.addThing(bomb1);
                    this.addThing(bomb2);
                } else if (wave == 2) {
                    player.decreaseHealth(-1);
                    this.addDrone(480, 0);
                    this.addDrone(480, 500);
                    this.addDrone(0, 0);
                    this.addDrone(0, 700);
                } else {
                    wave = -1;
                }
            }
            ticker = 0;
        }
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
