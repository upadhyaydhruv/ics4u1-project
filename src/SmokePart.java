import java.awt.*;
import java.awt.image.BufferedImage;
public class SmokePart {
    int x,y,height,frame;
    BufferedImage currentSmoke;
    SmokePart(int x, int y,int startHeight) {
        this.x=x;
        this.y=y;
        height=startHeight;
        currentSmoke=Smoke.newSmoke();
    }
    void move(){
        frame++;
        if(frame==1000000) {
            frame=0;
            height++;
            if (height > 150) reset();
        }
    }
    void paint(Graphics2D thisFrame){
        thisFrame.drawImage(currentSmoke, x, y-height,80,80, null);
    }
    void reset(){
        height=0;
        currentSmoke=Smoke.newSmoke();
    }
}