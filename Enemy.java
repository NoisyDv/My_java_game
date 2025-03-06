import java.awt.Image;
import javax.swing.ImageIcon;

public class Enemy extends Tile {
  ImageIcon enemyIcon = new ImageIcon("pl/enemy.gif");
  Image enemyImg = enemyIcon.getImage();
  int Evelocity = 1;

  Enemy(int x, int y) {
    super(x, y);
    this.x = x;
    this.y = y;
    this.img = enemyImg;
  }

  public void Enemy_move(int x, int y) {
    if (this.x < x) {
      this.x += Evelocity;
    }
    if (this.x > x) {
      this.x -= Evelocity;
    }
    if (this.y < y) {
      this.y += Evelocity;
    }
    if (this.y > y) {
      this.y -= Evelocity;
    }

  }
}
