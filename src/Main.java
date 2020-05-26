import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JPanel {
    private static final int frameDelay = 10;

    private static final Screen currentScreen = new Screen("menu");
    private static String nextScreen = "";

    //this needs to be public so that everything can use it
    public static Mouse mouse=new Mouse();

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("doctor sex");
        frame.add(new Main());
        frame.setSize(960, 720);
        //720p 4:3 or standard HD (you guys should change this now if you don't like it)
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //you guys can use commands to access the mouses current position
        // Main.mouse.getEvent().getX();
        // Main.mouse.getEvent().getY();


        while (true) {

            //moves current screen and updates nextScreen
            nextScreen = currentScreen.move();
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

            }

            @Override
            public void keyReleased(KeyEvent event) {

            }

            @Override
            public void keyPressed(KeyEvent event) {

            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {
            }

            @Override
            public void mousePressed(MouseEvent event) {
                mouse.press(event);
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                mouse.release(event);
            }

            @Override
            public void mouseEntered(MouseEvent event) {
                mouse.entered();
            }

            @Override
            public void mouseExited(MouseEvent event) {
                mouse.exited();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent event) {
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