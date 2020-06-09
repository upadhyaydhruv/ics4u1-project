import java.awt.*;
class Rainbow {
    Color color = new Color(255,0,0);
    int frameSkip=0,state=0;
    void move() {
        if (frameSkip == 300000) {
            frameSkip=0;
            if (state < 255) {
                color = new Color(color.getRed() - 1, color.getGreen() + 1, 0);
                state++;
            } else if (state < 510) {
                color = new Color(0, color.getGreen() - 1, color.getBlue() + 1);
                state++;
            } else if (state < 765) {
                color = new Color(color.getRed() + 1, 0, color.getBlue() - 1);
                state++;
            } else {
                state = 0;
            }
        }
        else {
            frameSkip++;
        }
    }
        Color get(){
        return color;
    }
}
class Glow {
    Color color = new Color(0,255,0);
    int frameSkip=0,state=0;
    void move() {
        if (frameSkip == 300000) {
            frameSkip=0;
            if (state < 255) {
                color = new Color(0, color.getGreen() - 1, color.getBlue() + 1);
                state++;
            } else if (state < 510) {
                color = new Color(0, color.getGreen() + 1, color.getBlue() - 1);
                state++;
            } else {
                state = 0;
            }
        }
        else {
            frameSkip++;
        }
    }
    Color get(){
        return color;
    }
}