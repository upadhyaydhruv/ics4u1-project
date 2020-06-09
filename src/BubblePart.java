import java.awt.*;
import java.awt.image.BufferedImage;
public class BubblePart {
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