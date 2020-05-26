import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu {
    String nextScreen = "";
    BufferedImage bullets, slice, back, splat, startButton, creditsButton;
    Rectangle startButtonRec = new Rectangle(375, 220, 200, 80);
    Rectangle creditsButtonRec = new Rectangle(375, 520, 200, 80);
    int Xoffset, Yoffset;
    boolean mouseOn = false;

    Menu() {
        try {
            bullets = ImageIO.read(new File("res\\bullets.png"));
            slice = ImageIO.read(new File("res\\green slice.png"));
            back = ImageIO.read(new File("res\\menu back.png"));
            splat = ImageIO.read(new File("res\\pink splat.png"));
            startButton = ImageIO.read(new File("res\\start button.png"));
            creditsButton = ImageIO.read(new File("res\\credits button.png"));
        } catch (IOException e) {
            System.out.println("image not found!");
        }
    }

    public void start(int lastX, int lastY) {
        nextScreen = "";
        Xoffset = lastX / 24;
        Yoffset = lastY / 24;
    }

    public String move() {

        // stuff that moves on its own goes here

        return nextScreen;
    }

    public void paint(Graphics2D thisFrame) {

        thisFrame.drawImage(back, Xoffset - 60, Yoffset - 60, 1010, 1010, null);
        thisFrame.drawImage(splat, (Xoffset / 2) + 80, (Yoffset / 2) + 50, 400, 500, null);
        thisFrame.drawImage(slice, 380 - (Xoffset / 2), 50 - (Yoffset / 2), 500, 300, null);
        thisFrame.drawImage(bullets, 120 - Xoffset, -50 - Yoffset, 800, 800, null);

        thisFrame.drawImage(startButton, startButtonRec.x, startButtonRec.y, startButtonRec.width,
                startButtonRec.height, null);
        thisFrame.drawImage(creditsButton, creditsButtonRec.x, creditsButtonRec.y, creditsButtonRec.width,
                creditsButtonRec.height, null);
    }

    public void keyTyped(KeyEvent event) {

    }

    public void keyReleased(KeyEvent event) {

    }

    public void keyPressed(KeyEvent event) {

    }

    public void mouseClicked(MouseEvent event) {

        if (startButtonRec.intersects(event.getX(), event.getY(), 1, 1)) {
            nextScreen = "levelSelect";
        }
        if (creditsButtonRec.intersects(event.getX(), event.getY(), 1, 1)) {
            nextScreen = "credits";
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
            Xoffset = event.getX() / 24;
            Yoffset = event.getY() / 24;
        }
    }
}