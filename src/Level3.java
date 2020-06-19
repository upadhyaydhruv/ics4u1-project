import java.awt.*;
import java.awt.image.BufferedImage;

public class Level3 extends Level {
    private BufferedImage water, plat, stack, win;

    private Player player;
    private Rectangle platRec;
    private Rectangle stackRec;
    private Smoke smoke1;
    private Smoke smoke2;
    boolean levelComplete;
    private int ticker = 0;
    private int wave = 0;

    int[] waveHold = new int[3];

    public Level3() {
        water = this.loadImage("res/background/storm water.png");
        plat = this.loadImage("res/background/level 3 plat.png");
        stack = this.loadImage("res/smoke stack.png");
        win = this.loadImage("you win.png");
    }

    private HealthBar healthBar;

    @Override
    public void createThings() {
        player = Main.newPlayer(555, 500);
        platRec = new Rectangle(150, 15, 650, 650);
        stackRec = new Rectangle(150, 285, 100, 240);
        smoke1 = new Smoke(120, 320);
        smoke2 = new Smoke(200, 320);
        this.healthBar = new HealthBar(player);

        this.addThing(player);
        this.addDrone(400, 200);
        this.addDrone(100, 360);
        this.addBulldog(300, 360);
        this.addBomb();

        this.addThing(smoke1);
        this.addThing(smoke2);
    }

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
    public String moveLevel() {
        ticker++;
        Screen.waveMove(waveHold);
        if (wave == -1) Main.clearedLevels.add("L3");
        if (Main.ENABLE_DEBUG_FEATURES && player.getHealth() == 0)
            System.out.println("player died");

        if (ticker == 500 && wave != -1) {
            if (this.countThing(Bulldog.class, Drone.class) == 0) {
                wave++;
                if (Main.ENABLE_DEBUG_FEATURES)
                    System.out.printf("New Wave %d\n", wave);

                if (wave == 1) {
                    this.player.setHealth(player.getHealth() + 1);
                    this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addBomb();
                    this.addBomb();
                    this.addBomb();


                } else if (wave == 2) {
                    this.player.setHealth(player.getHealth() + 1);
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));

                } else if (wave == 3) {
                    this.player.setHealth(player.getHealth() + 1);
                    this.addBomb();
                    this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));

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
        Screen.paint(stackRec, stack, g);
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
