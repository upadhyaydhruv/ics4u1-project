import java.awt.*;

public class HealthBar {
    Player player;
    float saturation = 0.99f; //saturation
    float brightness = 0.99f; //brightness


    public HealthBar(Player player) {
        this.player = player;
    }

    public void paint(Graphics2D g) {
        double percent = ((double) player.getHealth() / (double) player.getMaxHealth());
        float hue = (float) percent * (100.0f / 255.0f);
        Color myRGBColor = Color.getHSBColor(hue, saturation, brightness);
        g.setColor(Color.black);
        g.fillRect(5, 5, 200, 20);
        g.setColor(myRGBColor);
        g.fillRect(5, 5, (int) (percent * 200), 20);
        if (player.getHealth() <= 0) {
            g.setFont(new Font(null, Font.BOLD, 20));
            g.drawString("DEAD", 75, 22);
        }

    }
}
