package src.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.main.GamePanel;
import src.main.KeyHandler;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    int standCounter = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = gamePanel.tileSize - 16; // 48 - 16 = 32
        solidArea.height = gamePanel.tileSize - 16; // 48 - 16 = 32

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/assets/Player/Walking/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        
        if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true)
        {
            if (keyHandler.upPressed == true) {
                direction = "up";
            }
            if (keyHandler.downPressed == true) {
                direction = "down";
            }
            if (keyHandler.leftPressed == true) {
                direction = "left";
            }
            if (keyHandler.rightPressed == true) {
                direction = "right";
            }
            
            // Check Tile Collision
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            // If Collision Is False Player Can Move
            if (collisionOn == false) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            // Animation
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNumber == 1) spriteNumber = 2;
                else if (spriteNumber == 2) spriteNumber = 1;
                spriteCounter = 0;
            }
        }
        else {
            standCounter++;
            if (standCounter == 20) {
                spriteNumber = 1;
                standCounter = 0;
            }
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = gamePanel.objects[index].name;
            switch (objectName) {
                case "Key" -> {
                    gamePanel.playVSX(1);
                    hasKey++;
                    gamePanel.objects[index] = null;
                    gamePanel.ui.showMessage("You Got A Key!");
                }
                case "Door" -> {
                    if (hasKey > 0) {
                        gamePanel.playVSX(3);
                        gamePanel.objects[index] = null;
                        hasKey--;
                        gamePanel.ui.showMessage("You Opened A Door!");
                    } else {
                        gamePanel.ui.showMessage("You Need A Key!");
                    }
                }
                case "Boots" -> {
                    gamePanel.playVSX(2);
                    speed += 1;
                    gamePanel.objects[index] = null;
                    gamePanel.ui.showMessage("Speed Up");
                }
                case "Chest" -> {
                    gamePanel.ui.gameFinished = true;
                    gamePanel.stopMusic();
                    gamePanel.playVSX(4);
                }
            }            
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1)
                    image = up1;
                if (spriteNumber == 2)
                    image = up2;
            }
            case "down" -> {
                if (spriteNumber == 1)
                    image = down1;
                if (spriteNumber == 2)
                    image = down2;
            }
            case "left" -> {
                if (spriteNumber == 1)
                    image = left1;
                if (spriteNumber == 2)
                    image = left2;
            }
            case "right" -> {
                if (spriteNumber == 1)
                    image = right1;
                if (spriteNumber == 2)
                    image = right2;
            }
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
