import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
public class HealthBar {
    int health, previousHealth, ticker = 0;
    Player player;
    float hue = 1.0f; //hue
    float saturation = 0.99f; //saturation
    float brightness = 0.99f; //brightness

    Color myRGBColor = Color.getHSBColor(hue, saturation, brightness);
    public HealthBar() {
    }
    public void changeHealth() {
        this.health = player.getHealth();
        if (ticker == 100) {
            if (health < previousHealth) {
                previousHealth -= 1;
                ticker = 0;
                hue -= 0.02;
            }
            ticker++;
        }
    }

    public void paint(Graphics2D g) {
        g.setColor(myRGBColor);
            g.fillRect(5, 5, previousHealth * 10, 20);

    }
}
