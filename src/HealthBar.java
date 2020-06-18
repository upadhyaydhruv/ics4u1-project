import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
public class HealthBar {
    Player player;
    float hue = 1.0f; //hue
    float saturation = 0.99f; //saturation
    float brightness = 0.99f; //brightness


    public HealthBar(Player player) {
        this.player = player;
    }

    public void paint(Graphics2D g) {
        double percent = ((double)player.getHealth() / (double)player.getMaxHealth());
        hue = (float)percent * (100.0f / 255.0f);
        Color myRGBColor = Color.getHSBColor(hue, saturation, brightness);
        System.out.println(percent);
        g.setColor(Color.black);
            g.fillRect(5, 5, 200, 20);
        g.setColor(myRGBColor);
            g.fillRect(5,5,(int)(percent * 200),20);

    }
}
