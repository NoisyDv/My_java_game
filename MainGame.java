import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import javax.swing.*;
import java.util.Arrays;

public class MainGame extends JPanel implements ActionListener, KeyListener {
  int width;
  int height;
  Timer gameloop;

  Player player;
  ImageIcon floorIcon = new ImageIcon("pl/floor.png");
  Image floorImg = floorIcon.getImage();
  Tile floor;
  BufferedImage map;

  int velocity = 50;

  // create panel by constructor
  MainGame(int width, int height) {
    this.width = width;
    this.height = height;

    setPreferredSize(new Dimension(width, height));
    setBackground(Color.BLACK);
    addKeyListener(this);
    setFocusable(true);
    gameloop = new Timer(33, this);
    gameloop.start();
    player = new Player(5 * Tile.Tile_size, 5 * Tile.Tile_size);
    floor = new Tile(floorImg, 0, 0);
    createMap();

  }

  // paint all panel to window
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    // create grid Line
    for (int i = 0; i <= width; i++) {
      g.drawLine(i * Tile.Tile_size, 0, i * Tile.Tile_size, height);
      g.drawLine(0, i * Tile.Tile_size, width, i * Tile.Tile_size);
    }

    // drawmap
    g.drawImage(map, 0, 0, this);

    // draw Player
    g.drawImage(player.playerImg, player.x, player.y, this);

  }

  // create Map
  public void createMap() {
    map = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D m = map.createGraphics();
    for (int i = 0; i <= width; i++) {
      for (int j = 0; j < height; j++) {
        m.drawImage(floor.img, i * Tile.Tile_size, j * Tile.Tile_size, null);
      }
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // no use
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // no use
  }

  // manage key event
  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_W) {
      player.y -= velocity;

    }
    if (e.getKeyCode() == KeyEvent.VK_S) {

      player.y += velocity;

    }
    if (e.getKeyCode() == KeyEvent.VK_A) {

      player.x -= velocity;

    }
    if (e.getKeyCode() == KeyEvent.VK_D) {

      player.x += velocity;

    }
    // check limit x,y
    if (player.x >= width - Tile.Tile_size) {
      player.x = width - Tile.Tile_size;
    }
    if (player.x < 0) {
      player.x = 0;
    }
    if (player.y >= height - Tile.Tile_size) {
      player.y = height - Tile.Tile_size;
    }
    if (player.y < 0) {
      player.y = 0;
    }

  }

  // manage event and gameloop
  @Override
  public void actionPerformed(ActionEvent e) {

    repaint();

  }
}
