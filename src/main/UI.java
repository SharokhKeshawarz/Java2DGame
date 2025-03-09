package src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import src.object.KeyObject;

public class UI {
    GamePanel gamePanel;

    Font arialRegularFont, arialBoldFont;

    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public boolean gameFinished = false;

    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arialRegularFont = new Font("Arial", Font.PLAIN, 40);
        arialBoldFont = new Font("Arial", Font.BOLD, 40);
        KeyObject key = new KeyObject();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished == true ) {
            
            g2.setFont(arialRegularFont);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x, y;

            text = "YOU FOUND THE TREASURE!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 - (gamePanel.tileSize * 3);

            g2.drawString(text, x, y);

            text = "YOUR TIME IS: " + decimalFormat.format(playTime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 - (gamePanel.tileSize * 3);

            g2.drawString(text, x, y);

            g2.setFont(arialBoldFont);
            g2.setColor(Color.YELLOW);
        
            text = "CONGRATULATIONS!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 + (gamePanel.tileSize * 2);

            g2.drawString(text, x, y);
            
            gamePanel.gameThread = null;
        }

        g2.setFont(arialRegularFont);
        g2.setColor(Color.WHITE);
        g2.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize, gamePanel.tileSize, null);
        g2.drawString("x " + gamePanel.player.hasKey, 74, 65);

        // TIME
        playTime += (double)1/60;
        g2.drawString("Time: " + decimalFormat.format(playTime), gamePanel.tileSize * 13, 65);

        // MESSAGE
        if (messageOn == true) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gamePanel.tileSize / 2, gamePanel.tileSize * 5);
            
            messageCounter++;
        
            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
