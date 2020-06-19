import java.awt.*;
import java.awt.image.BufferedImage;

public class Level2 extends Level {
    private BufferedImage water, plat, barrel, radar, win;

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
        win = this.loadImage("you win.png");    }

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

    private void addBomb() {
        Bomb bo = new Bomb();
        this.addThing(bo);
    }

    @Override
    public void createThings() {
        player = Main.newPlayer(435, 170);
        platRec = new Rectangle(150, 15, 650, 650);
        barrelsRec = new Rectangle(430, 280, 40, 60);
        radarRec = new Rectangle(550, 15, 170, 130);
        this.healthBar = new HealthBar(player);
        this.addThing(player);

        this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
        this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
        this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
        this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
        this.addBomb();
    }

    @Override
    public String moveLevel() {
        ticker++;
        Screen.waveMove(waveHold);
        if (wave == -1) Main.clearedLevels.add("L2");
        if (Main.ENABLE_DEBUG_FEATURES && player.getHealth() == 0)
            System.out.println("player died");

        if (ticker == 500 && wave != -1) {
            if (this.countThing(Bulldog.class, Drone.class) == 0) {
                wave++;
                if (Main.ENABLE_DEBUG_FEATURES)
                    System.out.printf("New Wave %d\n", wave);

                if (wave == 1) {
                    this.player.setHealth(player.getHealth() + 1);
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addBomb();
                    this.addBomb();

                } else if (wave == 2) {
                    this.player.setHealth(player.getHealth() + 1);
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addBomb();
                    this.addBomb();
                } else {
                    wave = -1;
                    this.levelComplete = true;
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
        if (wave == -1) {
            g.drawImage(win, 300,200,null);
        }
        healthBar.paint(g);
    }

    @Override
    public void reset() {

    }
}
