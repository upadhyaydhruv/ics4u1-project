import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class BubbleTube implements Thing {
    Rectangle bubbleTubeRec;
    BufferedImage bubbleTube;
    BubblePart[] bubbleParts= new BubblePart[4];
    Rainbow color = new Rainbow();
    BubbleTube(int x,int y){
        bubbleTubeRec = new Rectangle(x, y, 108, 220);
        for (int a=0; a<bubbleParts.length; a++){
            bubbleParts[a]= new BubblePart(x+50,y+165,(a*44)+16);
        }
        try {
            bubbleTube = ImageIO.read(new File("res/bubbles/bubble tube.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }
    static BufferedImage newBubble(){
        try {
            switch ((int)(Math.random()*9)) {
                case 0:
                    return ImageIO.read(new File("res/bubbles/bubbles1.png"));
                case 1:
                    return ImageIO.read(new File("res/bubbles/bubbles2.png"));
                case 2:
                    return ImageIO.read(new File("res/bubbles/bubbles3.png"));
                case 3:
                    return ImageIO.read(new File("res/bubbles/bubbles4.png"));
                case 4:
                    return ImageIO.read(new File("res/bubbles/bubbles5.png"));
                case 5:
                    return ImageIO.read(new File("res/bubbles/bubbles6.png"));
                case 6:
                    return ImageIO.read(new File("res/bubbles/bubbles7.png"));
                case 7:
                    return ImageIO.read(new File("res/bubbles/bubbles8.png"));
                case 8:
                    return ImageIO.read(new File("res/bubbles/bubbles9.png"));
            }
        } catch (IOException e) {
            System.out.println("image not found!");
        }
        return null;
    }
    @Override
    public void move() {
        color.move();
        for (BubblePart bubblePart : bubbleParts) {
            bubblePart.move();
        }
    }
    @Override
    public void paint(Graphics2D thisFrame) {
        thisFrame.setColor(color.get());
        thisFrame.fillRect(bubbleTubeRec.x+48,bubbleTubeRec.y+60,44,98);
        for (BubblePart bubblePart : bubbleParts){
            bubblePart.paint(thisFrame);
        }
        Screen.paint(bubbleTubeRec,bubbleTube,thisFrame);
    }

    Level currentLevel;

    @Override
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
}
class BubblePart {
    int x,y,height,frame;
    BufferedImage currentBubble;
    BubblePart(int x, int y,int startHeight) {
        this.x=x;
        this.y=y;
        height=startHeight;
        currentBubble=BubbleTube.newBubble();
    }
    void move(){
        frame++;
        if(frame==1000000) {
            frame=0;
            height++;
            if (height == 170) reset();
        }
    }
    void paint(Graphics2D thisFrame){
        thisFrame.drawImage(currentBubble, x, y-height,44,44, null);
    }
    void reset(){
        height=0;
        currentBubble=BubbleTube.newBubble();
    }
}