
import java.awt.Image;


public abstract class Item extends Tile{
    Image img;
    int x;    
    int y;
    Item(int x,int y){
        super(x,y);

    }
    abstract boolean spawnChange(int change);
    


    
}
