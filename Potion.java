import java.util.Random;

import javax.swing.ImageIcon;

import java.awt.*;

public class Potion extends Item {
  Random random = new Random();
  Image potionImg = new ImageIcon("pl/potion1.gif").getImage();

  int recovery = random.nextInt(40, 51);

  Potion(int x, int y) {
    super(x, y);
    this.x = x;
    this.y = y;
    this.img = potionImg;

  }

  @Override
  boolean spawnChange(int change) {
    if (change > 60) {
      return true;
    }
    return false;

  }

}
