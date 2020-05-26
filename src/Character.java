public abstract class Character {
    private int xPos;
    private int yPos;
    private int xVel;
    private int yVel;
    private int health;

    public abstract void hit(int x, int y);
    public int getHealth(){
        return this.health;
    }

    public void increaseHealth(int differential){
        this.health+=differential;
    }
    public void decreaseHealth(int differential){
        this.health-=differential;
    }
}
