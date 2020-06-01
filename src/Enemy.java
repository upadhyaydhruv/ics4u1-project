public class Enemy {
    private int health;
    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private int mouseX;
    private int mouseY;

    public Enemy(Keyboard kb, int xOrig, int yOrig){
        this.xPos = xOrig;
        this.yPos = yOrig;
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

    public void setxPos(int xPos){
        this.xPos = xPos;
    }
    public void setYPos(int yPos){
        this.yPos = yPos;
    }

    public void move(){
    }
}
