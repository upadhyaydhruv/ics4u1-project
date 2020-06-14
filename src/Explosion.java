import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Explosion {
    BufferedImage pic;
    int[] x = new int[5];
    int[] y = new int[5];
    Explosion(){
        try {
            pic = ImageIO.read(Menu.class.getResourceAsStream("menu\\menu back.png"));
    }catch (IOException e) {
            System.out.println("image not found!");
        }
    }
    public void trigger(int x,int y){
        for(int a=0; a<5; a++){
            this.x[a]=(int) (Math.random()*50);
            this.x[a]=(int) (Math.random()*50);
        }
    }
    public void paint(){
        for(int a=0; a<5; a++){

        }
    }
}