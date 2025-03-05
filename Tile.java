import java.awt.*;
import javax.swing.*;

public class Tile extends JPanel {
  static int Tile_size = 50;
  Image img;
  int x;
  int y;

  Tile(Image img, int x, int y) {
    this.img = img;
    this.x = x;
    this.y = y;

  }

  Tile(int x, int y) {
    this.x = x;
    this.y = y;

  }
  //check collision between Tile
  public static boolean colliderRect(Tile  t1, Tile t2){
    if(t1.x<t2.x+Tile_size && t1.x+Tile_size>t2.x && t1.y<t2.y+Tile_size && t1.y+Tile_size>t2.y){
      return true;
    }
    return false;


  }

}
