package src.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import src.main.GamePanel;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int mapTileNumber[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNumber = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap("/assets/Maps/world01.txt");
    }

    public void getTileImage() {
        try {
           tiles[0] = new Tile();
           tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/OldVersion/grass.png"));
        
           tiles[1] = new Tile();
           tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/OldVersion/wall.png"));
           tiles[1].collision = true;

           tiles[2] = new Tile();
           tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/OldVersion/water.png"));
           tiles[2].collision = true;

           tiles[3] = new Tile();
           tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/OldVersion/earth.png"));

           tiles[4] = new Tile();
           tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/OldVersion/tree.png"));
           tiles[4].collision = true;

           tiles[5] = new Tile();
           tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/OldVersion/sand.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapFilePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(mapFilePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                String line = bufferedReader.readLine();
                while (col < gamePanel.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = number;
                    col++;
                }
                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
            int tileNumber = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
                    g2.drawImage(tiles[tileNumber].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }

            worldCol++;

            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
