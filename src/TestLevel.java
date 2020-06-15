import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class TestLevel {
    String nextScreen = "";
    int Xoffset,Yoffset;
    BufferedImage water,plat,object, bullet;
    private boolean isClicked = false;

    Rectangle platRec = new Rectangle(150, 15, 650, 650);
    Rectangle objectRec = new Rectangle(600, 50, 100, 120);
    private Skuttler player = new Skuttler(400, 350);
    private Drone drone =new Drone(400,400,1,1);
    private ChaseRocket rocket =new ChaseRocket(600,300,90);
    private Bulldog bulldog = new Bulldog(player,0,0,1,1);
    private Bomb bomb = new Bomb();

    private Explosion explosion = new Explosion();

    TestLevel(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch(InterruptedException e){}
        try {
            water = ImageIO.read(new File("res\\background\\storm water.png"));
            plat = ImageIO.read(new File("res\\background\\test plat.png"));
            object = ImageIO.read(new File("res\\test object.png"));
            bullet = ImageIO.read(new File("res\\skuttler shot C.png"));

        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }
    public void start() {
        nextScreen = "";

        drone =new Drone(400,400,1,1);
        bulldog = new Bulldog(player,0,0,1,1);
        rocket =new ChaseRocket(600,300,90);
        bomb = new Bomb();

    }
    public String move() {
        if(Main.mouse.isMouseOn()){


            }
        player.move();

        if (Main.keyboard.getEsc()) {
            nextScreen = "levelSelect";
        }

        //bobby: the X and Y tell the drone where its target is
        drone.move(player.getxPos(),player.getyPos());
        rocket.move(player.getxPos(),player.getyPos());
        bulldog.move(player);
        bomb.move();

        explosion.trigger(300,300);


        return nextScreen;
    }
    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(water, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);

        Screen.paint(platRec,plat,thisFrame);

        thisFrame.drawImage(plat, platRec.x, platRec.y, platRec.width, platRec.height, null);
        thisFrame.drawImage(object, objectRec.x, objectRec.y, objectRec.width, objectRec.height, null);
        player.paint(thisFrame);

        drone.paint(thisFrame);
        rocket.paint(thisFrame);
        bulldog.paint(thisFrame);
        bomb.paint(thisFrame);


        explosion.paint(thisFrame);

    }
}