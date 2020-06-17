import java.awt.*;

public abstract class Player implements Hittable {
    private int xPos, yPos, xVel, yVel, ticker = 0;
    private int health = 5;

    public Player(int xOrig, int yOrig) {
        this.xPos = xOrig;
        this.yPos = yOrig;
    }

    public Player() {
        //Default constructor required when coding subclass
    }

    public void decreaseHealth(int differential) {
        health -= differential;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int healthIn) {
        this.health = healthIn;
    }

    public int getxPos() {
        return this.xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return this.yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public void setXVel(int xVel) {
        this.xVel = xVel;
    }

    public void setYVel(int yVel) {
        this.yVel = yVel;
    }

    public void move() {
        ticker++;
        if (ticker == 1000) {
            if (Main.keyboard.getA()) {
                if (xPos - xVel < 0) {

                } else {
                    xPos -= xVel;
                }
            }
            if (Main.keyboard.getD()) {
                if (xPos + xVel > 870) {
                } else {
                    xPos += xVel;
                }
            }
            if (Main.keyboard.getW()) {
                if (yPos - yVel < 0) {

                } else {
                    yPos -= yVel;
                }
            }
            if (Main.keyboard.getS()) {
                if (yPos + yVel > 610) {

                } else {
                    yPos += yVel;
                }
            }
            ticker = 0;
        }
    }

    public void newPlayer(int x, int y) {
        this.setxPos(x);
        this.setYPos(y);
        this.health = 5;
    }

    abstract void paint(Graphics2D frame);
}
