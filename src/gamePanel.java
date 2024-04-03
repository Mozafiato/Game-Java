import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gamePanel extends JPanel implements Runnable {
    // Define member variables
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    public TileManager tileM;
    private KeyHandler keyH;
    private Thread gameThread;
    private Player player;
    private Enemy enemy;
    private BattleScreen battleScreen;

    public gamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.player = new Player(100, "Player", this, keyH); // Adjust health as needed
        this.enemy = new Enemy("Enemy", 100); // Adjust health as needed
         this.tileM = new TileManager(this);
        this.keyH = new KeyHandler();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void render() {
        repaint();
    }
    

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
    
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                // Update game logic
                update();
                delta--;
            }
            render(); // Render the game
            frames++;
    
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }

    CollisionChecker Checker = new CollisionChecker(this);

    
    private void update() {
        // Update game entities
        player.update();
        enemy.update();
    
        // Check for collisions or other game events
        if (player.getBounds().intersects(enemy.getBounds())) {
            initiateBattle();
        }
    }
    


    public void initiateBattle() {
        battleScreen = new BattleScreen(player, enemy);
        battleScreen.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        enemy.draw(g2);
        g2.dispose();
    }
}


