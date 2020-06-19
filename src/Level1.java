import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Level1 extends Level {
    private BufferedImage water, plat, barrels, arrow, win, death;

    private Player player;
    private Rectangle platRec;
    private Rectangle barrelsRec;
    private BubbleTube levelTrigger;
    private int wave;
    private int ticker;
    private final AffineTransform arrowTransform;

    int[] waveHold = new int[3];

    public Level1() {
        arrowTransform = new AffineTransform();
        arrowTransform.translate(50,400);
        arrowTransform.rotate(Math.toRadians(270), 0, 0);
        water = this.loadImage("res/background/storm water.png");
        plat = this.loadImage("res/background/level 1 plat.png");
        barrels = this.loadImage("res/barrels.png");
        arrow = this.loadImage("next arrow.png");
        win = this.loadImage("you win.png");
        death = this.loadImage("you die.png");
    }
    private HealthBar healthBar;

    @Override
    public void createThings() {
        this.wave = 0;
        this.ticker = 0;
        player = Main.newPlayer(435, 170);
        levelTrigger = new BubbleTube(30, 100);
        barrelsRec = new Rectangle(253, 460, 100, 140);
        platRec = new Rectangle(150, 15, 650, 650);
        this.healthBar = new HealthBar(player);

        this.addThing(player);
        this.addThing(levelTrigger);
        this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
        this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
        this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
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

    private void addBomb() {
        Bomb bo = new Bomb();
        this.addThing(bo);
    }

     
    @Override
    public String moveLevel() {
        ticker ++;
        Screen.waveMove(waveHold);
        if (wave == -1) Main.clearedLevels.add("L1");
        if (levelTrigger.wasHit() && wave == -1) {
            return "L2";
        }
        if (Main.ENABLE_DEBUG_FEATURES && player.getHealth() == 0)
            System.out.println("player died");

        if (ticker == 500 && wave != -1) {
            if (this.countThing(Bulldog.class, Drone.class) == 0) {
                wave ++;
                if (Main.ENABLE_DEBUG_FEATURES)
                    System.out.printf("New Wave %d\n", wave);
                if (wave == 1) {
                    this.player.setHealth(player.getHealth() + 1);
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                }
                else if (wave == 2) {
                    this.addBomb();
                    this.player.setHealth(player.getHealth() + 1);
                    this.addDrone((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
                    this.addBulldog((int)((Math.random()*875) + 1),(int)((Math.random()*500)+1));
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

        if (player.getHealth() <= 0) {
            g.drawImage(death, 300, 200, null);
        }
        if (wave == -1) {
            g.drawImage(arrow, arrowTransform,null);
        }
        if (wave == -1) {
            g.drawImage(win, 300, 200,null);
        }
        healthBar.paint(g);
    }

    @Override
    public void reset() {
    }
}
