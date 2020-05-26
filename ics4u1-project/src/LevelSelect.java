import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LevelSelect {
    String nextScreen = "";

    BufferedImage back, green, blue, orange, pink, backButton;
    Rectangle backButtonRec = new Rectangle(60, 60, 80, 80);

    int Xoffset, Yoffset;
    boolean mouseOn = true;

    LevelSelect() {
        try {
            back = ImageIO.read(new File("res\\menu back.png"));
            green = ImageIO.read(new File("res\\green shard.png"));
            blue = ImageIO.read(new File("res\\blue shard.png"));
            orange = ImageIO.read(new File("res\\orange shard.png"));
            pink = ImageIO.read(new File("res\\pink shard.png"));
            backButton = ImageIO.read(new File("res\\back button.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }

    public void start(int lastX, int lastY) {
        nextScreen = "";
        Xoffset = lastX / 4;
        Yoffset = lastY / 4;
    }

    public String move() {

        return nextScreen;
    }

    public void paint(Graphics2D thisFrame) {

        thisFrame.drawImage(back, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);
        thisFrame.drawImage(green, 225 - (Xoffset / 4), 50 + (Yoffset / 4), 100, 500, null);
        thisFrame.drawImage(blue, 375 - (Xoffset / 3), 50 + (Yoffset / 3), 100, 500, null);
        thisFrame.drawImage(orange, 525 - (Xoffset / 2), 50 + (Yoffset / 2), 100, 500, null);
        thisFrame.drawImage(pink, 750 - (Xoffset), 50 + (Yoffset), 100, 500, null);

        thisFrame.drawImage(backButton, backButtonRec.x, backButtonRec.y, backButtonRec.width, backButtonRec.height,
                null);

    }

    public void keyTyped(KeyEvent event) {

    }

    public void keyReleased(KeyEvent event) {

    }

    public void keyPressed(KeyEvent event) {

    }

    public void mouseClicked(MouseEvent event) {
        if (backButtonRec.intersects(event.getX(), event.getY(), 1, 1)) {
            nextScreen = "menu";
        }

    }

    public void mousePressed(MouseEvent event) {

    }

    public void mouseReleased(MouseEvent event) {

    }

    public void mouseEntered(MouseEvent event) {
        mouseOn = true;
    }

    public void mouseExited(MouseEvent event) {
        mouseOn = false;
    }

    public void mouseMoved(MouseEvent event) {
        if (mouseOn) {
            Xoffset = event.getX() / 4;
            Yoffset = event.getY() / 4;
        }
    }
}