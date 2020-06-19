import java.awt.*;
import java.awt.image.BufferedImage;

public class HowToPlay {
    String nextScreen = "";
    BufferedImage back, backButton, use, wasd, toMove,leftClick,and,rightClick,toAttack;
    Rectangle backButtonRec = new Rectangle(60, 60, 80, 80);


    private int Xoffset, Yoffset;

    HowToPlay() {
        back = Thing.loadImage("res/menu/menu back.png");
        backButton = Thing.loadImage("res/button/back button.png");
        use = Thing.loadImage("res/menu/use.png");
        wasd = Thing.loadImage("res/menu/wasd.png");
        toMove = Thing.loadImage("res/menu/to move.png");
        use = Thing.loadImage("res/menu/use.png");
        wasd = Thing.loadImage("res/menu/wasd.png");
        toMove = Thing.loadImage("res/menu/to move.png");
        leftClick = Thing.loadImage("res/menu/left click.png");
        and = Thing.loadImage("res/menu/and.png");
        rightClick = Thing.loadImage("res/menu/right click.png");
        toAttack = Thing.loadImage("res/menu/to attack.png");
    }

    public void start() {
        nextScreen = "";

        Xoffset = Main.mouse.getX() / 4;
        Yoffset = Main.mouse.getY() / 4;
    }

    public String move() {
        if (Main.mouse.isMouseOn()) {
            Xoffset = Main.mouse.getX() / 4;
            Yoffset = Main.mouse.getY() / 4;
        }
        if (Main.mouse.getLMB()) {
            if (Main.mouse.intersects(backButtonRec)) {
                nextScreen = "menu";
            }
        }
        return nextScreen;
    }

    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(back, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);
        Screen.paint(backButtonRec, backButton, thisFrame);
        thisFrame.drawImage(use,200,200,64,28,null);
        thisFrame.drawImage(wasd,285,180,92,68,null);
        thisFrame.drawImage(toMove,400,200,144,28,null);

        thisFrame.drawImage(use,200,400,64,28,null);
        thisFrame.drawImage(leftClick,280,370,68,100,null);
        thisFrame.drawImage(and,380,400,64,28,null);
        thisFrame.drawImage(rightClick,470,370,68,100,null);
        thisFrame.drawImage(toAttack,570,400,184,28,null);
    }
}