import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.*;

public class Settings {

    String nextScreen = "";

    BufferedImage back,backButton,blue,green,orange,pink,gameSpeed,upButton,downButton,numberPlate;
    Rectangle backButtonRec = new Rectangle(60, 60, 80, 80);
    Rectangle gameSpeedRec = new Rectangle(180,180, 360,80);
    Rectangle upButtonRec = new Rectangle(590,100,80,60);
    Rectangle numberPlateRec = new Rectangle(590,150,80,60);
    Rectangle downButtonRec = new Rectangle(590,200,80,60);

    private int Xoffset, Yoffset;
    private boolean mouseToggle;
    Settings() {
        try {
            back = ImageIO.read(new File("res\\menu back.png"));
            backButton = ImageIO.read(new File("res\\back button.png"));
            blue = ImageIO.read(new File("res\\blue bubble.png"));
            green = ImageIO.read(new File("res\\green bubble.png"));
            orange = ImageIO.read(new File("res\\orange bubble.png"));
            pink = ImageIO.read(new File("res\\pink bubble.png"));

            gameSpeed = ImageIO.read(new File("res\\game speed.png"));
            upButton = ImageIO.read(new File("res\\up button.png"));
            downButton = ImageIO.read(new File("res\\down button.png"));
            numberPlate = ImageIO.read(new File("res\\number plate.png"));

        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }

    public void start() {
        nextScreen = "";
        mouseToggle = false;
        Xoffset = Main.mouse.getX() / 4;
        Yoffset = Main.mouse.getY() / 4;
    }

    public String move() {
        if(Main.mouse.isMouseOn()){
            Xoffset = Main.mouse.getX() / 4;
            Yoffset = Main.mouse.getY() / 4;
        }
        if(Main.mouse.getLMB()) {
            if (Main.mouse.intersects(backButtonRec)) {
                nextScreen = "menu";
            }

        }
        return nextScreen;
    }

    public void paint(Graphics2D thisFrame) {

        thisFrame.drawImage(back, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);

        thisFrame.drawImage(blue, 165 , 450+(Yoffset/4) , 198, 198, null);
        thisFrame.drawImage(green, 131 , 250+(Yoffset/2) , 138, 138, null);
        thisFrame.drawImage(orange, 5 , 350+(Yoffset/3) , 126, 126, null);
        thisFrame.drawImage(pink, 10 , 140+(Yoffset) , 90, 90, null);
        //Main.getFrameDelay();
        thisFrame.drawImage(backButton, backButtonRec.x, backButtonRec.y, backButtonRec.width, backButtonRec.height, null);
        thisFrame.drawImage(gameSpeed, gameSpeedRec.x, gameSpeedRec.y, gameSpeedRec.width, gameSpeedRec.height, null);
        thisFrame.drawImage(upButton, upButtonRec.x, upButtonRec.y, upButtonRec.width, upButtonRec.height, null);
        thisFrame.drawImage(numberPlate, numberPlateRec.x, numberPlateRec.y, numberPlateRec.width, numberPlateRec.height, null);
        thisFrame.drawImage(downButton, downButtonRec.x, downButtonRec.y, downButtonRec.width, downButtonRec.height, null);

        //thisFrame.drawString(Integer.toString(Main.getFrameDelay()));
    }
}

