public class Player {
    private int health;
    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private int mouseX;
    private int mouseY;
    private int ticker = 0;

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

    public void move(){
        if (Main.keyboard.getA()){
            ticker++;
            if (ticker%10000==0) {
                xPos -= xVel;
                ticker = 0;
            }
        }
        if (Main.keyboard.getD()){
            ticker++;
            if (ticker%10000==0){
                xPos+=xVel;
                ticker = 0;
            }
        }
        if (Main.keyboard.getW()) {
            ticker++;
            if (ticker%10000==0){
                yPos-=yVel;
                ticker = 0;
            }
        }
        if (Main.keyboard.getS()){
            ticker++;
            if (ticker%10000==0){
                yPos+=yVel;
                ticker = 0;
            }
        }
    }
}
