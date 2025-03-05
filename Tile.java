import javax.swing.*;
import java.awt.*;

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

}
