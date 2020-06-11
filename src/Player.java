import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player {
    private int health,xPos, yPos, xVel, yVel, mouseX, mouseY, ticker = 0;
    //private double previousState = 0;
    //private double currentState = Math.PI/2;


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

    /*
    public double[] getStates() {
        double[] arr = {previousState, currentState};
        return arr;
    }

    public void setCurrentState(double state){
        this.currentState = state;
    }

    public void setPreviousState(double state){
        this.previousState = state;
    }
    */

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        return new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
    }

    public void move(){ //The delay (10000) prevents the spaceships from zipping through the arena too fast
        /*
        if (Main.keyboard.getA()&&Main.keyboard.getW()){

            ticker++;
            if (ticker==10000){
                xPos-=xVel/2;
                yPos-=yVel/2;
                ticker = 0;
            }
        }

        if (Main.keyboard.getD()&&Main.keyboard.getW()){

            ticker++;
            if (ticker==10000){
                xPos+=xVel/2;
                yPos-=yVel/2;
                ticker = 0;
            }
        }

        if (Main.keyboard.getS()&&Main.keyboard.getD()){

            ticker++;
            if (ticker==10000){
                xPos+=xVel/2;
                yPos+=yVel/2;
                ticker = 0;
            }
        }


        if (Main.keyboard.getS()&&Main.keyboard.getA()){

            ticker++;
            if (ticker==10000){
                xPos-=xVel/2;
                yPos+=yVel/2;
                ticker = 0;
            }
        }

        */
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
