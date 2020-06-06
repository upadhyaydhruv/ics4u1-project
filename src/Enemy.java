public class Enemy {
    private int health, xPos, yPos, xVel, yVel;
    private Player p;

    public Enemy(Player p){
        this.xPos = xPos;
        this.yPos = yPos;
        this.p = p;
        xVel = 1;
        yVel = 1;
    }

    public Enemy(){
        //Default constructor required when coding subclass
    }

    public void decreaseHealth(int differential){
        health -= differential;
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

    public int getxVel(){
        return this.xVel;
    }
    public int getyVel(){
        return this.yVel;
    }

    public void setxPos(int xPos){
        this.xPos = xPos;
    }
    public void setYPos(int yPos){
        this.yPos = yPos;
    }

    public void setyVel(int yVel) {
        this.yVel = yVel;
    }

    public void setxVel(int xVel) {
        this.xVel = xVel;
    }

    public void move(){
    }
}
