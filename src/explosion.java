public class explosion {
    int[] x = new int[5];
    int[] y = new int[5];
    public void trigger(int x,int y){
        for(int a=0; a<5; a++){
            this.x[a]=1;
        }
    }
}