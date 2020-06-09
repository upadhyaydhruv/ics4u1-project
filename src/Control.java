import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static java.awt.event.KeyEvent.*;

import static java.awt.event.MouseEvent.*;
import static java.awt.event.MouseEvent.BUTTON3;
import static java.lang.Character.toLowerCase;
class Keyboard {
    private Boolean W=false,A=false,S=false,D=false,esc=false;
    public void press(KeyEvent event){
        switch(event.getKeyCode()){
            case VK_W: W=true;
                break;
            case VK_A: A=true;
                break;
            case VK_S: S=true;
                break;
            case VK_D: D=true;
                break;
            case VK_ESCAPE: esc=true;
        }
    }
    public void release(KeyEvent event){
        switch(event.getKeyCode()){
            case VK_W : W=false;
                break;
            case VK_A: A=false;
                break;
            case VK_S : S=false;
                break;
            case VK_D : D=false;
                break;
            case VK_ESCAPE: esc=false;
        }
    }
    public Boolean getW(){
        return W;
    }
    public Boolean getA(){
        return A;
    }
    public Boolean getS(){
        return S;
    }
    public Boolean getD(){
        return D;
    }
    public Boolean getEsc(){
        return esc;
    }
}
class Mouse {
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
}