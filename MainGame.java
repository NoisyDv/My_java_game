import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGame extends JPanel implements ActionListener {
  private class TileMap {
    int x;
    int y;

    TileMap(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  int height;
  int width;
  int tile_size = 20;
  int velocity_x = 1;
  int velocity_y = 1;
  int speed = 6;
  // snake
  TileMap snake;

  // apple
  TileMap apple;

  // random
  Random random;

  // gameloop
  Timer gameloop;

  MainGame(int width, int height) {
    this.height = height;
    this.width = width;
    setPreferredSize(new Dimension(this.width, this.height));
    setBackground(Color.green);
    snake = new TileMap(10, 10);
    apple = new TileMap(5, 5);
    random = new Random();
    random_apple();
    gameloop = new Timer(100, this);
    gameloop.start();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }

  public void draw(Graphics g) {
    for (int i = 0; i < width / tile_size; i++) {
      g.drawLine(i * tile_size, 0, i * tile_size, height);
      g.drawLine(0, i * tile_size, width, i * tile_size);
    }
    g.setColor(Color.blue);
    g.fillRect(apple.x * tile_size, apple.y * tile_size, tile_size, tile_size);

    g.setColor(Color.red);
    g.fillRect(snake.x * tile_size, snake.y * tile_size, tile_size, tile_size);
  }

  public void random_apple() {
    apple.x = random.nextInt(width / tile_size);
    apple.y = random.nextInt(height / tile_size);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    repaint();
    move();

  }

  public void move() {
    snake.x += velocity_x;
    snake.y += velocity_y;
    if (snake.x < 0) {
      velocity_x = -velocity_x;
    }
    if (snake.x > width / tile_size) {
      velocity_x = -velocity_x;
    }
    if (snake.y < 0) {
      velocity_y = -velocity_y;
    }
    if (snake.y > width / tile_size) {
      velocity_y = -velocity_y;
    }

  }

}
