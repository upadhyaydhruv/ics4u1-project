import java.awt.*;
import java.awt.image.BufferedImage;

public class ShipSelect {
    String nextScreen = "";
    int state = 0, Xoffset, Yoffset;
    BufferedImage back, backButton, shipSelectBack, choseShip, esperPlate, skuttlerPlate, tiamatPlate, currentPlate, selectButton, esperButton, skuttlerButton, tiamatButton;
    Rectangle backButtonRec = new Rectangle(60, 60, 80, 80);
    Rectangle selectButtonRec = new Rectangle(600, 500, 160, 80);
    Rectangle esperButtonRec = new Rectangle(100, 200, 104, 88);
    Rectangle skuttlerButtonRec = new Rectangle(300, 180, 120, 116);
    Rectangle tiamatButtonRec = new Rectangle(500, 200, 92, 84);
    boolean mousetoggle = false;
    int height, count=0;

    ShipSelect() {
        back = Thing.loadImage("menu/menu back.png");
        backButton = Thing.loadImage("button/back button.png");
        shipSelectBack = Thing.loadImage("menu/ship select back.png");
        choseShip = Thing.loadImage("menu/chose a ship.png");
        esperPlate = Thing.loadImage("menu/esper nameplate.png");
        skuttlerPlate = Thing.loadImage("menu/skuttler nameplate.png");
        tiamatPlate = Thing.loadImage("menu/tiamat nameplate.png");
        selectButton = Thing.loadImage("button/select button.png");
        esperButton = Thing.loadImage("button/esper button.png");
        skuttlerButton = Thing.loadImage("button/skuttler button.png");
        tiamatButton = Thing.loadImage("button/tiamat button.png");
    }

    public void start() {
        nextScreen = "";
        Xoffset = Main.mouse.getX() / 4;
        Yoffset = Main.mouse.getY() / 4;
        mousetoggle = false;
        state = 0;
        height = 300;
    }

    public String move() {
        if (Main.mouse.isMouseOn()) {
            Xoffset = Main.mouse.getX() / 4;
            Yoffset = Main.mouse.getY() / 4;
        }
        if (!Main.mouse.getLMB() && !mousetoggle) {
            mousetoggle = true;
        }
        if (Main.mouse.getLMB() && mousetoggle) {
            mousetoggle = false;
            if (Main.mouse.intersects(backButtonRec)) nextScreen = "menu";
            else if (Main.mouse.intersects(esperButtonRec)) if (state != 1) {
                state = 1;
                currentPlate = esperPlate;
            } else state = 0;
            else if (Main.mouse.intersects(skuttlerButtonRec)) if (state != 2) {
                state = 2;
                currentPlate = skuttlerPlate;
            } else state = 0;
            else if (Main.mouse.intersects(tiamatButtonRec)) if (state != 3) {
                state = 3;
                currentPlate = tiamatPlate;
            } else state = 0;
            else if (Main.mouse.intersects(selectButtonRec)) if (state != 0) {
                Main.playerType = state;
                nextScreen = "levelSelect";
            }
        }
        if(count==100){
            count=0;
            if (state != 0) {
                if(height!=0)
                    height--;
            }
            else {
                if(height!=300)
                    height++;
            }
        }
        count++;
        return nextScreen;
    }

    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(back, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);
        thisFrame.drawImage(choseShip, 300, 80, null);
        Screen.paint(backButtonRec, backButton, thisFrame);
        Screen.paint(esperButtonRec, esperButton, thisFrame);
        Screen.paint(skuttlerButtonRec, skuttlerButton, thisFrame);
        Screen.paint(tiamatButtonRec, tiamatButton, thisFrame);
        thisFrame.drawImage(shipSelectBack, 0, 300+height, null);
        thisFrame.drawImage(selectButton,600, 500+height, 160, 80,null);
        thisFrame.drawImage(currentPlate, 600, 400+height, null);
    }
}