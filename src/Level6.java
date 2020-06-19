import java.awt.*;
import java.awt.image.BufferedImage;

public class Level6 extends Level {
    private BufferedImage water, plat, barrels;

    Rectangle platRec;
    Rectangle barrelsRec;
    private int wave;
    private int ticker;
    private int point;

    int[] waveHold = new int[3];

    Player player;

    public Level6() {
        water = this.loadImage("res/background/beach water.png");
        plat = this.loadImage("res/background/beach2.png");
        barrels = this.loadImage("res/barrels.png");
    }

    private HealthBar healthBar;

    @Override
    public void createThings() {
        this.wave = 0;
        this.ticker = 0;
        this.point = 0;
        player = Main.newPlayer(555, 500);
        platRec = new Rectangle(0, 0, 960, 720);
        barrelsRec = new Rectangle(253, 460, 100, 140);
        this.healthBar = new HealthBar(player);

        this.addThing(player);
        this.addBomb();
        this.addBomb();
        this.addBomb();
        this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
        this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
        this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
        this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
    }

    private void addBulldog(int x, int y) {
        Bulldog b = new Bulldog(x,y);
        b.setTarget(player);
        this.addThing(b);
    }

    private void addDrone(int x, int y) {
        Drone d = new Drone(x,y,1,1);
        d.setTarget(player);
        d.setXVel(d.getXVel()+point);
        d.setYVel(d.getXVel()+point);
        d.setDelay(d.getDelay() - (point * 500));
        this.addThing(d);
    }

    private void addBomb() {
        Bomb bo = new Bomb();
        this.addThing(bo);
    }

    @Override
    public String moveLevel() {
        ticker ++;
        Screen.waveMove(waveHold);
        if (wave == -1) Main.clearedLevels.add("L6");
        if (Main.ENABLE_DEBUG_FEATURES && player.getHealth() == 0)
            System.out.println("player died");
        if (ticker == 500 && wave != -1) {
            if (this.countThing(Bulldog.class, Drone.class) == 0) {
                wave ++;
                if (Main.ENABLE_DEBUG_FEATURES)
                    System.out.printf("New Wave %d\n", wave);
                if (wave == 1) {
                    point ++;
                    this.player.setHealth(player.getHealth() + 1);
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                }
                else if (wave == 2) {
                    point ++;
                    this.player.setHealth(player.getHealth() + 1);
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
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
