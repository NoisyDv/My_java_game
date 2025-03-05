import java.awt.Image;
import javax.swing.ImageIcon;

public class Coin extends Item{
    Image coinImg=new ImageIcon("pl/coin.gif").getImage();
    Coin(int x ,int y){
        super(x, y);
        this.x=x;
        this.y=y;
        this.img=coinImg;
    }


    @Override
    boolean spawnChange(int change) {
        return true;
    }
    
}
