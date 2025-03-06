import java.awt.*;
import javax.swing.*;

public class Player extends Tile {
  ImageIcon player_icon = new ImageIcon("pl/player.gif");
  Image playerImg = player_icon.getImage();
  int hp=300;

  Player(int x, int y) {
    super(x, y);
    this.x = x;
    this.y = y;
    this.img = playerImg;

  }

}
