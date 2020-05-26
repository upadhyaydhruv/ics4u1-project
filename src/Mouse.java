import java.awt.*;
import java.awt.event.MouseEvent;
public class Mouse {
    private int X, Y;
    private MouseEvent event;
    public void update(MouseEvent event) {
        X = event.getX();
        Y = event.getY();
    }
    public Boolean intersects(Rectangle rec) {
        return rec.intersects(X,Y,1,1);
    }
    public int getX(){
        return X;
    }
    public int getY(){
        return Y;
    }
    public void press(MouseEvent event){
        this.event=event;
    }
    public void release(){
        event=null;
    }
    public MouseEvent getEvent(){
        return event;
    }
}