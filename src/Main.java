import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

// bobby's notes
// you guys can use commands to access the mouses current position and buttons
// Main.mouse.getX() gives you the x value
// Main.mouse.getY() gives you the y value
// LMB= left mouse button
// SWB= scroll wheel button
// RMB= right mouse button
// use Main.mouse.getLMB()/Main.mouse.getSWB()/Main.mouse.getRMB() to test if a button is currently being pressed
//                  (they will return a Boolean value)
// these methods can be run anywhere due to the mouse object being public and inside the Main class
//
//i put together a quick keyboard class in the same style
//it only looks for the WASD and Esc keys right now
// to test if a key is down use the Main.keyboard.getW()/Main.keyboard.getA()/Main.keyboard.getS()/Main.keyboard.getD()
//                                                       (they will return a Boolean value)
// this works anywhere just like the mouse class
//
// note that ive updated the levels so that you can use the escape key to backout to the level select screen

public class Main extends JPanel {

    //this needs to be public so that everything can use it without re-directing it
    public static Mouse mouse=new Mouse();
    public static Screen currentScreen = new Screen("menu");
    public static Keyboard keyboard=new Keyboard();

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("Game");
        frame.add(new Main());
        frame.setSize(960, 720);
        //720p 4:3 or standard HD (you guys should change this now if you don't like it)
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        while (true) {

            //moves current screen and updates nextScreen
            String nextScreen = currentScreen.move();
            if (!nextScreen.equals("")) {
                currentScreen.changeScreen(nextScreen);
            }
            frame.repaint();

        }
    }

    Main() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent event) {

            }

            @Override
            public void keyReleased(KeyEvent event) {
                keyboard.release(event);
            }

            @Override
            public void keyPressed(KeyEvent event) {
                keyboard.press(event);
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {
            }

            @Override
            public void mousePressed(MouseEvent event) {
                Main.mouse.press(event);
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                Main.mouse.release(event);
            }

            @Override
            public void mouseEntered(MouseEvent event) {
                Main.mouse.entered();
            }

            @Override
            public void mouseExited(MouseEvent event) {
                Main.mouse.exited();
            }

        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent event) {
                mouse.update(event);
            }
            public void mouseDragged(MouseEvent event){
                mouse.update(event);
            }
        });
        setFocusable(true);
    }



    //Dhruv: The methods below aid in rotating art assets, allowing them to point in the direction they are travelling in
    // bobby: can we move this somewhere else for the sake of keeping the Main clean?
    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }
    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }


    //bobby: that try and catch is used to allow the screen class to stop and start the move class (its a long story)
    public void paint (Graphics lastFrame) {
        super.paint(lastFrame);
        Graphics2D thisFrame = (Graphics2D) lastFrame;
        try {
            currentScreen.paint(thisFrame);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
class uselessClass extends JPanel {
    MouseMotion() {
        System.out.print("burn the communists!");
        System.out.print("can't be delusional if you don't subscribe the a concrete reality, am i right?");
        System.out.print("id like to think covid is the manifestation of universe stopping Hatsune Miku from going to coachella");
        System.out.print("no one can stop me from listening to kill-me-baby while i code");
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent event) {
                System.out.print("whose been drawing nicks?");
                Main.mouse.update(event);
            }
        });
    }
}
*/