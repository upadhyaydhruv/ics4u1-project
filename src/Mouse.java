import java.awt.*;
import java.awt.event.MouseEvent;
import static java.awt.event.MouseEvent.*;

public class Mouse {
    private int X, Y;
    private boolean LMB,SWB,RMB,mouseOn;
    private MouseEvent event;
    public void update(MouseEvent event) {
        this.event=event;
        X = event.getX();
        Y = event.getY();
    }
    public void press(MouseEvent event){
        switch(event.getButton()){
            case BUTTON1: LMB=true;
                break;
            case BUTTON2: SWB=true;
                break;
            case BUTTON3: RMB=true;
        }
    }
    public void release(MouseEvent event){
        switch(event.getButton()){
            case BUTTON1: LMB=false;
                break;
            case BUTTON2: SWB=false;
                break;
            case BUTTON3: RMB=false;
        }
    }
    public void entered() {
        mouseOn = true;
    }

    public void exited() {
        mouseOn = false;
    }

    public int getX(){
        return X;
    }
    public int getY(){
        return Y;
    }
    public Boolean intersects(Rectangle rec) {
        return rec.intersects(X,Y,1,1);
    }
    public boolean isMouseOn(){
        return mouseOn;
    }
    public boolean getLMB(){
        return LMB;
    }
    public boolean getSWB(){
        return SWB;
    }
    public boolean getRMB(){
        return RMB;
    }
    public MouseEvent getEvent(){
        return event;
    }
}