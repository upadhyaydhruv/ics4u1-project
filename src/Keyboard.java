import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

import static java.lang.Character.toLowerCase;
public class Keyboard {
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