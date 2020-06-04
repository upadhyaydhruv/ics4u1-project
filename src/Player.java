public class Player {
    private int health;
    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private int mouseX;
    private int mouseY;
    private int ticker = 0;
    private double previousState = 0;
    private double currentState = Math.PI/2;

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

    public double[] getStates() {
        double[] arr = {previousState, currentState};
        return arr;
    }

    public void move(){ //The delay (10000) prevents the spaceships from zipping through the arena too fast
        if (Main.keyboard.getA()&&Main.keyboard.getW()){
            previousState = currentState;
            currentState = (7*Math.PI)/4;
            ticker++;
            if (ticker%10000==0){
                xPos-=xVel;
                yPos-=yVel;
                ticker = 0;
            }
        }

        if (Main.keyboard.getD()&&Main.keyboard.getW()){
            previousState = currentState;
            currentState = Math.PI/4;
            //state = 8;
            ticker++;
            if (ticker%10000==0){
                xPos+=xVel;
                yPos-=yVel;
                ticker = 0;
            }
        }

        if (Main.keyboard.getS()&&Main.keyboard.getD()){
            previousState = currentState;
            currentState = (3*Math.PI)/4;
            //state = 2;
            ticker++;
            if (ticker%10000==0){
                xPos+=xVel;
                yPos+=yVel;
                ticker = 0;
            }
        }


        if (Main.keyboard.getS()&&Main.keyboard.getA()){
            previousState = currentState;
            currentState = (5*Math.PI)/4;
            //state = 4;
            ticker++;
            if (ticker%10000==0){
                xPos-=xVel;
                yPos+=yVel;
                ticker = 0;
            }
        }

        if (Main.keyboard.getA()){
            previousState = currentState;
            currentState = (3*Math.PI)/2;
            //state = 5;
            ticker++;
            if (ticker%10000==0) {
                xPos -= xVel;
                ticker = 0;
            }
        }
        if (Main.keyboard.getD()){
            previousState = currentState;
            currentState = Math.PI/2;
            //state = 1;
            ticker++;
            if (ticker%10000==0){
                xPos+=xVel;
                ticker = 0;
            }
        }
        if (Main.keyboard.getW()) {
            previousState = currentState;
            currentState = 0;
            //state = 7;
            ticker++;
            if (ticker%10000==0){
                yPos-=yVel;
                ticker = 0;
            }
        }
        if (Main.keyboard.getS()){
            previousState = currentState;
            currentState = Math.PI;
            //state = 3;
            ticker++;
            if (ticker%10000==0){
                yPos+=yVel;
                ticker = 0;
            }
        }
    }
}
