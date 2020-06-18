import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
public class HealthBar {
    int health;
    Player player;
    private int healthMax;
    float hue = 1.0f; //hue
    float saturation = 0.99f; //saturation
    float brightness = 0.99f; //brightness

    Color myRGBColor = Color.getHSBColor(hue, saturation, brightness);

    public HealthBar(Player player) {
        this.player = player;
        this.healthMax = player.getMaxHealth();
    }

    public void paint(Graphics2D g) {
        double percent = ((double)player.getHealth() / (double)player.getMaxHealth());
        hue = (float)percent;
        g.setColor(Color.black);
            g.fillRect(5, 5, 200, 20);
        g.setColor(myRGBColor);
            g.fillRect(5,5,(int)(percent * 200),20);

    }
}
