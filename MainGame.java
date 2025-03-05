import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class MainGame extends JPanel implements ActionListener, KeyListener {

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  // class of Tile Map
  private class TileMap {
    int x;
    int y;
    int w;
    int h;

    TileMap(int x, int y, int w, int h) {
      this.x = x;
      this.y = y;
      this.h = h;
      this.w = w;
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
    snake = new TileMap(10, 10, tile_size, tile_size);
    apple = new TileMap(5, 5, tile_size, tile_size);
    random = new Random();
    random_apple();
    gameloop = new Timer(100, this);
    gameloop.start();
    addKeyListener(this);
    setFocusable(true);
  }

  // start draw game in window
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }

  public void draw(Graphics g) {
    // make grid line
    for (int i = 0; i < width / tile_size; i++) {
      g.drawLine(i * tile_size, 0, i * tile_size, height);
      g.drawLine(0, i * tile_size, width, i * tile_size);
    }
    // make apple
    g.setColor(Color.blue);
    g.fillRect(apple.x * tile_size, apple.y * tile_size, apple.w, apple.h);

    // make snake
    g.setColor(Color.red);
    g.fillRect(snake.x * tile_size, snake.y * tile_size, snake.w, snake.h);

  }

  // ramdom spawn apple in map
  public void random_apple() {
    apple.x = random.nextInt(width / tile_size);
    apple.y = random.nextInt(height / tile_size);
  }

  // make animation
  @Override
  public void actionPerformed(ActionEvent e) {
    repaint();
    move();
    if (apple.x < snake.x) {
      apple.x++;
    }
    if (apple.x > snake.x) {
      apple.x--;
    }
    if (apple.y < snake.y) {
      apple.y++;
    }
    if (apple.y > snake.y) {
      apple.y--;
    }

  }

  // snake movement
  public void move() {
    snake.x += velocity_x;
    snake.y += velocity_y;
    if (snake.x < 1) {  
      velocity_x = -velocity_x;
    }
    if (snake.x > width / tile_size) {
      velocity_x = -velocity_x;
    }
    if (snake.y < 1) {
      velocity_y = -velocity_y;
    }
    if (snake.y > width / tile_size) {
      velocity_y = -velocity_y;
    }

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      velocity_x = 0;
      velocity_y = -1;

    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      velocity_x = 0;
      velocity_y = 1;

    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      velocity_x = -1;
      velocity_y = 0;

    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      velocity_x = 1;
      velocity_y = 0;
    }
  }

  public boolean colliderect(TileMap m1, TileMap m2) {
    return m1.x == m2.x && m2.y == m1.y;

  }

}
