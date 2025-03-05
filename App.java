

import javax.swing.*;

public class App {
  public static void main(String[] args) {
    int window_height = 800;
    int window_width = 800;
    JFrame window = new JFrame("Game");
    window.setSize(window_width, window_height);
    window.setLocationRelativeTo(null);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setVisible(true);
    MainGame game = new MainGame(window_width, window_height);
    window.add(game);
    window.pack();
    game.requestFocus(); 
  }

}
