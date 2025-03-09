package src.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import src.entity.Player;
import src.object.SuperObject;
import src.tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48*48

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    final int FPS = 60;
    
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    public KeyHandler keyHandler = new KeyHandler();
    Sound music = new Sound();
    Sound vsx = new Sound();
    TileManager tileManager = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    public SuperObject objects[] = new SuperObject[10];
    public UI ui = new UI(this);
    Thread gameThread;
    


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long timer = 0;
        int drawCount = 0;
    
        while (gameThread != null) {
            long currentTime = System.nanoTime();
            double elapsedTime = (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            delta += elapsedTime;
            timer += (currentTime - lastTime);
    
            while (delta >= 1) { // Ensures update is called the correct number of times
                update();
                delta--;
            }
    
            repaint(); // Always repaint to avoid visual stuttering
    
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }            
        }
    }
    
    public void update() {
        player.update();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        tileManager.draw(g2);
        for (int i = 0; i < objects.length; i++)
        if (objects[i] != null)
        objects[i].draw(g2, this);
        player.draw(g2);
        ui.draw(g2);

        g2.dispose();
        Toolkit.getDefaultToolkit().sync();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playVSX(int i) {
        vsx.setFile(i);
        vsx.play();
    }
}
