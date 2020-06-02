public class Player {
    private Keyboard keyboard;
    private int health;
    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private int mouseX;
    private int mouseY;

    public Player(Keyboard kb, int xOrig, int yOrig){
        keyboard = kb;
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

    public void move(){
        if (Main.keyboard.getA()){
            xPos-=xVel;
        }
        if (Main.keyboard.getD()){
            xPos+=xVel;
        }
        if (Main.keyboard.getW()) {
            yPos-=yVel;
        }
        if (Main.keyboard.getS()){
            yPos+=yVel;
        }
    }
}
