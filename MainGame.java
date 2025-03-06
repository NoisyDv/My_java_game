import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import javax.swing.*;

public class MainGame extends JPanel implements ActionListener, KeyListener {
  int width;
  int height;
  Timer gameloop;
  Player player;
  ImageIcon floorIcon = new ImageIcon("pl/floor.png");
  Image floorImg = floorIcon.getImage();
  Tile floor;
  BufferedImage map;
  Enemy enemy;
  Random random = new Random();
  Coin coin;
  int score = 0;
  JLabel scoretext;
  JLabel gameover = new JLabel();
  JButton play_again = new JButton("Play Again");
  Potion potion;
  boolean spawn_potion;
  int velocity = 50;

  // create panel by constructor
  MainGame(int width, int height) {
    this.width = width;
    this.height = height;

    setPreferredSize(new Dimension(width, height));
    setBackground(Color.BLACK);
    addKeyListener(this);
    setFocusable(true);
    // start gameloop
    gameloop = new Timer(16, this);
    gameloop.start();

    // make gameobject
    player = new Player(5 * Tile.Tile_size, 5 * Tile.Tile_size);
    enemy = new Enemy(random.nextInt(width - Tile.Tile_size), random.nextInt(height - Tile.Tile_size));
    floor = new Tile(floorImg, 0, 0);
    coin = new Coin(random.nextInt(width - Tile.Tile_size), random.nextInt(height - Tile.Tile_size));
    potion = new Potion(random.nextInt(width - Tile.Tile_size), random.nextInt(height - Tile.Tile_size));
    // make game over Label
    gameover.setText("Game Over \n your score is " + score);
    gameover.setForeground(Color.RED);
    gameover.setBounds(450, 250, 500, 500);
    gameover.setFont(new Font("FiraCode Neard Font", Font.BOLD, 32));

    // play again button
    play_again.setBounds(580, 540, 100, 80);
    play_again.addActionListener(this);

    // make Score Label
    scoretext = new JLabel("Score : " + score);
    scoretext.setForeground(Color.RED);
    scoretext.setBounds(width - 200, 10, 300, 50);
    scoretext.setFont(new Font("FiraCode Neard Font", Font.BOLD, 30));
    setLayout(null);
    add(scoretext);
    // create Map
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

    // draw Enemy
    g.drawImage(enemy.enemyImg, enemy.x, enemy.y, this);

    // draw Coin

    g.drawImage(coin.coinImg, coin.x, coin.y, this);

    // draw Potion
    if (spawn_potion) {

      g.drawImage(potion.potionImg, potion.x, potion.y, this);
    }

    // draw player HP
    g.setColor(Color.RED);
    g.fillRect(0, 0, 300, 30);
    g.setColor(Color.GREEN);
    g.fillRect(0, 0, player.hp, 30);
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
    // check collision player with enemey
    if (Tile.colliderRect(player, enemy)) {
      player.hp -= 2;
      System.out.println("colliderect!");
    }
    // check collision player with coin
    if (Tile.colliderRect(player, coin)) {
      score++;
      scoretext.setText("Score : " + score);
      coin = new Coin(random.nextInt(width - Tile.Tile_size), random.nextInt(height - Tile.Tile_size));
      enemy.Evelocity += 1;
      System.out.printf(" score is %d velocity of enemy  %d \n", score, enemy.Evelocity);
      if (potion.spawnChange(random.nextInt(101))) {
        spawn_potion = true;
      }

    }
    // check collision player with potion
    if (Tile.colliderRect(player, potion)) {
      potion = new Potion(random.nextInt(width - Tile.Tile_size), random.nextInt(height - Tile.Tile_size));
      player.hp += potion.recovery;
      spawn_potion = false;
    }

    // enemy follow the player
    enemy.Enemy_move(player.x, player.y);

    // game over condition
    if (player.hp <= 0) {
      gameloop.stop();
      gameover.setText("Game Over \n your score is " + score);
      setFocusable(false);
      add(gameover);
      add(play_again);

    }
    // game restart
    if (e.getSource() == play_again) {
      remove(gameover);
      remove(play_again);
      score = 0;
      enemy.Evelocity = 1;
      scoretext.setText("Score: " + score);
      player.hp = 300;
      velocity = 50;
      player.x = random.nextInt(width - Tile.Tile_size);
      player.y = random.nextInt(height - Tile.Tile_size);
      enemy.x = random.nextInt(width - Tile.Tile_size);
      enemy.y = random.nextInt(height - Tile.Tile_size);
      coin = new Coin(random.nextInt(width - Tile.Tile_size), random.nextInt(height - Tile.Tile_size));
      setFocusable(true);
      requestFocusInWindow();
      gameloop.start();

    }

    repaint();
  }
}
