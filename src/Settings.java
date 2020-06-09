import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.*;
public class Settings {
    String nextScreen = "";
    BufferedImage back,backButton,blue,green,orange,pink,frameDelay,upButton,downButton,numberPlate;
    Rectangle backButtonRec = new Rectangle(60, 60, 80, 80);
    Rectangle frameDelayRec = new Rectangle(180,180, 360,80);
    Rectangle upButtonRec = new Rectangle(590,100,80,60);
    Rectangle numberPlateRec = new Rectangle(590,150,80,60);
    Rectangle downButtonRec = new Rectangle(590,200,80,60);
    private int Xoffset, Yoffset;
    private boolean mouseToggle;
    Settings() {
        try {
            back = ImageIO.read(new File("res\\menu\\menu back.png"));
            backButton = ImageIO.read(new File("res\\button\\back button.png"));
            blue = ImageIO.read(new File("res\\menu\\blue bubble.png"));
            green = ImageIO.read(new File("res\\menu\\green bubble.png"));
            orange = ImageIO.read(new File("res\\menu\\orange bubble.png"));
            pink = ImageIO.read(new File("res\\menu\\pink bubble.png"));
            frameDelay = ImageIO.read(new File("res\\menu\\frame delay.png"));
            upButton = ImageIO.read(new File("res\\button\\up button.png"));
            downButton = ImageIO.read(new File("res\\button\\down button.png"));
            numberPlate = ImageIO.read(new File("res\\menu\\number plate.png"));
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
            if (Main.mouse.intersects(upButtonRec)&&!mouseToggle&&Main.currentScreen.getFrameDelay()!=20) {
                Main.currentScreen.setFrameDelay(Main.currentScreen.getFrameDelay()+1);
                mouseToggle = true;
            }
            if (Main.mouse.intersects(downButtonRec)&&!mouseToggle&&Main.currentScreen.getFrameDelay()!=1) {
                Main.currentScreen.setFrameDelay(Main.currentScreen.getFrameDelay()-1);
                mouseToggle = true;
            }
        }
        else {
            mouseToggle = false;
        }
        return nextScreen;
    }
    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(back, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);
        thisFrame.drawImage(blue, 165 , 450+(Yoffset/4) , 198, 198, null);
        thisFrame.drawImage(green, 131 , 250+(Yoffset/2) , 138, 138, null);
        thisFrame.drawImage(orange, 5 , 350+(Yoffset/3) , 126, 126, null);
        thisFrame.drawImage(pink, 10 , 140+(Yoffset) , 90, 90, null);
        Screen.paint(backButtonRec,backButton,thisFrame);
        Screen.paint(frameDelayRec,frameDelay,thisFrame);
        Screen.paint(upButtonRec,upButton,thisFrame);
        Screen.paint(numberPlateRec,numberPlate,thisFrame);
        Screen.paint(downButtonRec,downButton,thisFrame);
        Font lastFont = thisFrame.getFont();
        Font newFont = new Font ("GB18030 Bitmap", Font.BOLD, 50);
        thisFrame.setFont(newFont);
        thisFrame.drawString(Integer.toString(Main.currentScreen.getFrameDelay()),600,195);
        thisFrame.setFont(lastFont);
    }
}