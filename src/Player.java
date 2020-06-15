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

    public void setHealth(int healthIn){
        this.health = healthIn;
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

    public void move(){

        ticker++;
        if (ticker==10000) {
            if (Main.keyboard.getA()) {
                if (xPos-xVel<0){

                }else {
                    xPos -= xVel;
                }
            }
            if (Main.keyboard.getD()) {
                if (xPos+xVel>870) {
                } else {
                    xPos += xVel;
                }
            }
            if (Main.keyboard.getW()) {
                if (yPos-yVel<0){

                }else {
                    yPos -= yVel;
                }
            }
            if (Main.keyboard.getS()) {
                if (yPos+yVel>610){

                } else {
                    yPos += yVel;
                }
            }
            ticker = 0;
        }
    }
}
class CurrentPlayer {
    Esper esper = new Esper(0,0);
    Skuttler skuttler = new Skuttler(0,0);
    Tiamat tiamat = new Tiamat(0,0);
    int currentShip=0;
    int health=5;
    void setCurrentShip(String shipName){

        switch (shipName){
            case "esper":
                currentShip=1;
                break;
            case "skuttler":
                currentShip=2;
                break;
            case "tiamat":
                currentShip=3;

        }


    }
    void newPlayer(int x, int y){
        health=5;
        switch (currentShip){
            case 1:
                esper = new Esper(x,y);
                break;
            case 2:
                skuttler = new Skuttler(x,y);
                break;
            case 3:
                tiamat = new Tiamat(x,y);
        }
    }
    void move(){
        switch (currentShip){
            case 1:
                esper.move();
                break;
            case 2:
                skuttler.move();
                break;
            case 3:
                tiamat.move();
        }
    }
    void addHealth(int a){
        health+=a;
    }
    void setHealth(){

    }
    void paint(Graphics2D thisFrame){
        switch (currentShip){
            case 1:
                esper.paint(thisFrame);
                break;
            case 2:
                skuttler.paint(thisFrame);
                break;
            case 3:
                tiamat.paint(thisFrame);
        }
        //thisFrame.drawRect(400,400,health*10,40);
    }
}