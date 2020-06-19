import java.awt.*;
import java.awt.image.BufferedImage;

public class Credits {
    String nextScreen = "";
    BufferedImage back, monitor, backButton;
    Rectangle backButtonRec = new Rectangle(60, 60, 80, 80);
    int Xoffset, Yoffset;

    // Patrick also helped with a few things:
    // - Fixing a few hard-to-find bugs:
    //   - Null pointer exceptions due to race conditions (repaint is async, so it may execute at the same time as a move function).
    //   - List modification errors due to race conditions (same reason as above, solved by using a CopyOnWriteArrayList instead of an ArrayList).
    //   - AffineTransforms randomly glitching (same reason as above, solved by copying them where necessary, and not splitting logic between paint/move).
    //   - AffineTransforms acting unusual (due to the fact that the order of rotate/translate/scale matters).
    //   - Fixed slow-moving objects at an angle getting stuck due to the trig functions being rounded down as an int (annoying Java stuff).
    //   - Intermittent image loading issues for some people (fixed by using resources rather than raw File IO).
    //   - Compilation issues due to mismatched Java versions between group members and due to build *.class files being committed to the Git repository.
    // - Adding some debugging code (added code to draw the hitboxes, showed how to print the function which called another one, helped give ideas for where to add print statements).
    // - Improving the class layout to allow for dynamically adding/removing enemies/players (because before, instead of using a list, there were a bunch of static public variables for each item, not leaving room for adding/removing them, and breaking encapsulation) and detecting hits (but I didn't change any behaviour or fix any problems while doing this, only restructured things and removed duplicated code):
    //   - Idea for Hittable and HittableThing class (John and Dhruv did the implementation after I gave the idea for the structure and methods).
    //   - Gave a starting point for an abstract Level class (which uses a bit of reflection to make things easier to handle) (I gave the list of methods and variables, assisted with implementation, but didn't touch the actual levels at all).
    //   - Removed a few unnecessary classes (i.e. a CurrentPlayer class whose only purpose was to have 1 variable for each type of character) (only what was made irrelevant by the abstract Player class and the Thing interface).
    // - I only did the bare minimum to be able to help with these things in the most unobtrusive way possible, hence why I left as-is:
    //   - the somewhat unnecessary switch statements in the Screens
    //   - the duplicated code between the different Player subclasses
    //   - the suboptimal painting
    //   - the leftover unused variables
    //   - the unformatted code
    //   - the still not-completely-encapsulated classes
    //   - the overly-complex nested if statements
    //   - the unusual naming conventions for certain things
    //   - etc
    //   - that's also why Player doesn't implement Thing (it wasn't strictly needed for what they asked) and why Thing is an interface rather than an abstract class (so I wouldn't have to rewrite parts of each enemy, and so I would only have to rename and change the arguments of functions and not the functionality)

    Credits() {
        back = Thing.loadImage("res/menu/menu back.png");
        monitor = Thing.loadImage("res/menu/credit monitor.png");
        backButton = Thing.loadImage("res/button/back button.png");
    }

    public void start() {
        nextScreen = "";
        Xoffset = Main.mouse.getX() / 4;
        Yoffset = Main.mouse.getY() / 4;
    }

    public String move() {
        if (Main.mouse.isMouseOn()) {
            Xoffset = Main.mouse.getX() / 4;
            Yoffset = Main.mouse.getY() / 4;
        }
        if (Main.mouse.getLMB()) {
            if (Main.mouse.intersects(backButtonRec)) {
                nextScreen = "menu";
            }
        }
        return nextScreen;
    }

    public void paint(Graphics2D thisFrame) {
        thisFrame.drawImage(back, (Xoffset / 8) - 60, (Yoffset / 8) - 60, 1010, 1010, null);
        thisFrame.drawImage(monitor, (Xoffset / 4) + 225, 50 + (Yoffset / 4), 500, 600, null);
        Screen.paint(backButtonRec, backButton, thisFrame);
    }
}