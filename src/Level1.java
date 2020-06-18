import java.awt.*;
import java.awt.image.BufferedImage;

public class Level1 extends Level {
    private BufferedImage water, plat, barrels;

    private Player player;
    private Rectangle platRec;
    private Rectangle barrelsRec;
    private BubbleTube levelTrigger;
    private int wave = 0;
    private int ticker = 0;

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
        this.addBulldog(480,700);
        this.addBulldog(0,360);
        this.addBulldog(940,360);
    }

    private void addBulldog(int x, int y) {
        Bulldog b = new Bulldog(x,y);
        b.setTarget(player);
        this.addThing(b);
    }

    private void addDrone(int x, int y) {
        Drone d = new Drone(x,y,1,1);
        d.setTarget(player);
        this.addThing(d);
    }

     
    @Override
    public String moveLevel() {
        ticker ++;
        Screen.waveMove(waveHold);
        if (levelTrigger.wasHit())
            return "L2";
        if (Main.ENABLE_DEBUG_FEATURES && player.getHealth() == 0)
            System.out.println("player died");

        if (ticker == 500 && wave != -1) {
            if (this.countThing(Bulldog.class, Drone.class) == 0) {
                wave ++;
                if (Main.ENABLE_DEBUG_FEATURES)
                    System.out.printf("New Wave %d\n", wave);
                if (wave == 1) {
                    this.addDrone(0,0);
                    this.addDrone(0,500);
                    this.addDrone(700,0);
                    this.addDrone(700,500);
                }
                else if (wave == 2) {
                    this.addDrone(480,0);
                    this.addDrone(480,500);
                    this.addBulldog(0,0);
                    this.addBulldog(0,700);
                    this.addBulldog(940,0);
                    this.addBulldog(940,700);
                }
                else {
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
