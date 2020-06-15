import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class ShipSelect {
    String nextScreen ="", shipName = "";
    int state=0, Xoffset, Yoffset;
    BufferedImage back,backButton, shipSelectBack, choseShip, esperPlate, skuttlerPlate, tiamatPlate,currentPlate, selectButton, esperButton, skuttlerButton, tiamatButton;
    Rectangle backButtonRec = new Rectangle(60, 60, 80, 80);
    Rectangle selectButtonRec = new Rectangle(600, 500, 160, 80);
    Rectangle esperButtonRec = new Rectangle(100, 200, 160, 80);
    Rectangle skuttlerButtonRec = new Rectangle(300, 200, 160, 80);
    Rectangle tiamatButtonRec = new Rectangle(500, 200, 160, 80);
    boolean mousetoggle=false;
    ShipSelect() {
        try {
            back = ImageIO.read(Menu.class.getResourceAsStream("menu\\menu back.png"));
            backButton = ImageIO.read(Menu.class.getResourceAsStream("button\\back button.png"));
            shipSelectBack = ImageIO.read(Menu.class.getResourceAsStream("menu\\ship select back.png"));
            choseShip = ImageIO.read(Menu.class.getResourceAsStream("menu\\chose a ship.png"));
            esperPlate = ImageIO.read(Menu.class.getResourceAsStream("menu\\esper nameplate.png"));
            skuttlerPlate = ImageIO.read(Menu.class.getResourceAsStream("menu\\skuttler nameplate.png"));
            tiamatPlate = ImageIO.read(Menu.class.getResourceAsStream("menu\\tiamat nameplate.png"));
            selectButton = ImageIO.read(Menu.class.getResourceAsStream("button\\select button.png"));
            esperButton = ImageIO.read(Menu.class.getResourceAsStream("button\\esper button.png"));
            skuttlerButton = ImageIO.read(Menu.class.getResourceAsStream("button\\skuttler button.png"));
            tiamatButton = ImageIO.read(Menu.class.getResourceAsStream("button\\tiamat button.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }
    public void start() {
        nextScreen = "";
        Xoffset = Main.mouse.getX() / 4;
        Yoffset = Main.mouse.getY() / 4;
        mousetoggle=false;
        state = 0;
    }
    public String move() {
        if(Main.mouse.isMouseOn()){
            Xoffset = Main.mouse.getX() / 4;
            Yoffset = Main.mouse.getY() / 4;
        }
        if(!Main.mouse.getLMB()&&!mousetoggle) { mousetoggle=true;}
        if(Main.mouse.getLMB()&&mousetoggle) {
            mousetoggle = false;
            if (Main.mouse.intersects(backButtonRec)) nextScreen = "menu";
            else if (Main.mouse.intersects(esperButtonRec)) if (state != 1){
                state = 1;
                currentPlate=esperPlate;
            }
            else state = 0;
            else if (Main.mouse.intersects(skuttlerButtonRec)) if (state != 2){
                state = 2;
                currentPlate=skuttlerPlate;
            }
            else state = 0;
            else if (Main.mouse.intersects(tiamatButtonRec)) if (state != 3){
                state = 3;
                currentPlate=tiamatPlate;
            }
            else state = 0;

            else if (Main.mouse.intersects(selectButtonRec)) if(state!=0){
                switch (state){
                    case 1:
                        Main.player.setCurrentShip("esper");
                    break;
                    case 2:
                        Main.player.setCurrentShip("skuttler");
                    break;
                    case 3:
                        Main.player.setCurrentShip("tiamat");
                }
                Main.player.setCurrentShip(shipName);
                System.out.print(shipName);
                nextScreen = "levelSelect";
            }
        }
        return nextScreen;
    }

    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(back, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);

        thisFrame.drawImage(choseShip,300,80,null);

        Screen.paint(backButtonRec,backButton,thisFrame);
        Screen.paint(esperButtonRec,esperButton,thisFrame);
        Screen.paint(skuttlerButtonRec,skuttlerButton,thisFrame);
        Screen.paint(tiamatButtonRec,tiamatButton,thisFrame);

        if(state!=0){
            thisFrame.drawImage(shipSelectBack,0,300, null);
            Screen.paint(selectButtonRec,selectButton,thisFrame);
            thisFrame.drawImage(currentPlate,600,400,null);
        }
    }
}