import java.awt.event.KeyEvent;
import static java.lang.Character.toLowerCase;
public class Keyboard {
    private Boolean W,A,S,D;
    public void press(KeyEvent event){
        switch(toLowerCase(event.getKeyChar())){
            case 'w': W=true;
                break;
            case 'a': A=true;
                break;
            case 's': S=true;
                break;
            case 'd': D=true;
        }
    }
    public void release(KeyEvent event){
        switch(toLowerCase(event.getKeyChar())){
            case 'w': W=false;
                break;
            case 'a': A=false;
                break;
            case 's': S=false;
                break;
            case 'd': D=false;
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
}