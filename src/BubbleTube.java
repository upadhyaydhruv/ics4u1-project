import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class BubbleTube {
    Rectangle bubbleTubeRec;
    BufferedImage bubbleTube;
    BubblePart[] bubbleParts= new BubblePart[4];
    BubbleTube(int x,int y){
        bubbleTubeRec = new Rectangle(x, y, 108, 220);
        for (int a=0; a<bubbleParts.length; a++){
            bubbleParts[a]= new BubblePart(x,y,a*44);
        }
    }
    public BubbleTube(){
        try {
            bubbleTube = ImageIO.read(new File("res\\bubbles\\bubble tube skeleton.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }
    static BufferedImage newBubble(){
        try {
            switch ((int)(Math.random()*9)){
                case 0: return ImageIO.read(new File("res\\bubbles\\bubbles1.png"));
                case 1: return ImageIO.read(new File("res\\bubbles\\bubbles2.png"));
                case 2: return ImageIO.read(new File("res\\bubbles\\bubbles3.png"));
                case 3: return ImageIO.read(new File("res\\bubbles\\bubbles4.png"));
                case 4: return ImageIO.read(new File("res\\bubbles\\bubbles5.png"));
                case 5: return ImageIO.read(new File("res\\bubbles\\bubbles6.png"));
                case 6: return ImageIO.read(new File("res\\bubbles\\bubbles7.png"));
                case 7: return ImageIO.read(new File("res\\bubbles\\bubbles8.png"));
                case 8: return ImageIO.read(new File("res\\bubbles\\bubbles9.png"));
            }
        } catch (IOException e) {
            System.out.println("image not found!");
        }
        return null;
    }
    void move() {
        for (BubblePart bubblePart : bubbleParts) {
            bubblePart.move();
        }
    }
    void paint(Graphics2D thisFrame) {
        for (BubblePart bubblePart : bubbleParts){
            bubblePart.paint(thisFrame);
        }
        Screen.paint(bubbleTubeRec,bubbleTube,thisFrame);
    }
}