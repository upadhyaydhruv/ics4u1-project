public abstract class Player implements HittableThing {
    private int xPos, yPos, xVel, yVel, ticker = 0, shootTicker = 0;
    private int health;
    private int shootRate = 1000;
    boolean oldLMB;

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

    public void setShootRate(int rate) {
        this.shootRate = rate;
    }

    @Override
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

        boolean curLMB = Main.mouse.getLMB();

        shootTicker++;
        if ((oldLMB != curLMB && curLMB) || shootTicker >= shootRate * 100) {
            shootTicker = 0;
            if (Main.mouse.getLMB()) {
                System.out.println("test");
                this.shoot();
            }
        }

        if (oldLMB != curLMB) {
            oldLMB = curLMB;
        }
    }

    public void newPlayer(int x, int y) {
        this.setxPos(x);
        this.setYPos(y);
        this.health = 50;
    }

    public abstract void shoot();
}
