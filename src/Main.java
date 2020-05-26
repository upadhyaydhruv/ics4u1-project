import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JPanel {
    private static final int frameDelay = 10;

    private static final Screen currentScreen = new Screen("menu");
    private static String nextScreen = "";

    public static Mouse mouse=new Mouse();

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("doctor sex");
        frame.add(new Main());
        frame.setSize(960, 720);
        //720p 4:3 or standard HD (you guys should change this now if you don't like it)
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        while (true) {

            //moves current screen and updates nextScreen
            nextScreen = currentScreen.move(mouse);
            if (nextScreen != "") {
                currentScreen.changeScreen(nextScreen);
            }
            frame.repaint();
            Thread.sleep(frameDelay);
        }
    }

    Main() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent event) {
                currentScreen.keyTyped(event);
            }

            @Override
            public void keyReleased(KeyEvent event) {
                currentScreen.keyReleased(event);
            }

            @Override
            public void keyPressed(KeyEvent event) {
                currentScreen.keyPressed(event);
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {
                currentScreen.mouseClicked(event);
            }

            @Override
            public void mousePressed(MouseEvent event) {
                currentScreen.mousePressed(event);
                mouse.press(event);
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                mouse.press(event);
                currentScreen.mouseReleased(event);
            }

            @Override
            public void mouseEntered(MouseEvent event) {
                currentScreen.mouseEntered(event);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                currentScreen.mouseExited(event);
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent event) {
                currentScreen.mouseMoved(event);
                mouse.update(event);
            }
        });
        setFocusable(true);
    }

    public void paint(Graphics lastFrame) {
        super.paint(lastFrame);
        Graphics2D thisFrame = (Graphics2D) lastFrame;
        currentScreen.paint(thisFrame);
    }
}