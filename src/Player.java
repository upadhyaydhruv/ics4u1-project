public class Player {
    private Keyboard keyboard;
    private int health;
    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private int enemyType;
    public Player(Keyboard kb, int xOrig, int yOrig){
        keyboard = kb;
        this.xPos = xOrig;
        this.yPos = yOrig;
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
}
