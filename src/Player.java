import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player {
    private int xPos, yPos, xVel, yVel, ticker = 0;
    private int health = 5;


    public Player(int xOrig, int yOrig){
        this.xPos = xOrig;
        this.yPos = yOrig;
    }

    public Player(){
        //Default constructor required when coding subclass
    }

    public void increaseHealth(int differential){
        health+=differential;
    }
    public void decreaseHealth(int differential){
        health-=differential;
    }

    public void hit(int x, int y){
        //TODO
    }

    public int getHealth(){
        return this.health;
    }

    public int getxPos(){
        return this.xPos;
    }

    public int getyPos(){
        return this.yPos;
    }

    public void setxPos(int xPos){
        this.xPos = xPos;
    }
    public void setYPos(int yPos){
        this.yPos = yPos;
    }

    public void setXVel(int xVel){this.xVel = xVel;}
    public void setYVel(int yVel) {this.yVel = yVel;}

    public void move(){ //The delay (10000) prevents the spaceships from zipping through the arena too fast
        ticker++;
        if (ticker==10000) {
            if (Main.keyboard.getA()) {
                xPos -= xVel;
            }
            if (Main.keyboard.getD()) {
                xPos += xVel;
            }
            if (Main.keyboard.getW()) {
                yPos -= yVel;
            }
            if (Main.keyboard.getS()) {
                yPos += yVel;
            }
            ticker = 0;
        }
    }
}
