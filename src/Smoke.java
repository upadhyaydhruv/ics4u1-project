import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Smoke {
    int x,y;
    SmokePart[] smokeParts= new SmokePart[10];
    Smoke(int x,int y){
        this.x=x;
        this.y=y;
        for (int a=0; a<smokeParts.length; a++){
            smokeParts[a]= new SmokePart(x,y,a*15);
        }
    }
    public Smoke() {
    }
    static BufferedImage newSmoke(){
        try {
            switch ((int)(Math.random()*9)){
                case 0: return ImageIO.read(new File("res\\smoke\\smoke1.png"));
                case 1: return ImageIO.read(new File("res\\smoke\\smoke2.png"));
                case 2: return ImageIO.read(new File("res\\smoke\\smoke3.png"));
                case 3: return ImageIO.read(new File("res\\smoke\\smoke4.png"));
                case 4: return ImageIO.read(new File("res\\smoke\\smoke5.png"));
                case 5: return ImageIO.read(new File("res\\smoke\\smoke6.png"));
                case 6: return ImageIO.read(new File("res\\smoke\\smoke7.png"));
                case 7: return ImageIO.read(new File("res\\smoke\\smoke8.png"));
                case 8: return ImageIO.read(new File("res\\smoke\\smoke9.png"));
            }
        } catch (IOException e) {
            System.out.println("image not found!");
        }
            return null;
    }
        void move() {
        for (SmokePart smokePart : smokeParts) {
            smokePart.move();
        }
    }
    void paint(Graphics2D thisFrame) {
        for (SmokePart smokePart : smokeParts){
            smokePart.paint(thisFrame);
        }
    }
}