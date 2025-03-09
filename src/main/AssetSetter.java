package src.main;

import src.object.BootsObject;
import src.object.ChestObject;
import src.object.DoorObject;
import src.object.KeyObject;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        // KeyObject
        gamePanel.objects[0] = new KeyObject();
        gamePanel.objects[0].worldX = 23 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 7 * gamePanel.tileSize;
        
        gamePanel.objects[1] = new KeyObject();
        gamePanel.objects[1].worldX = 23 * gamePanel.tileSize;
        gamePanel.objects[1].worldY = 40 * gamePanel.tileSize;
        
        gamePanel.objects[2] = new KeyObject();
        gamePanel.objects[2].worldX = 38 * gamePanel.tileSize;
        gamePanel.objects[2].worldY = 8 * gamePanel.tileSize;

        gamePanel.objects[3] = new DoorObject();
        gamePanel.objects[3].worldX = 10 * gamePanel.tileSize;
        gamePanel.objects[3].worldY = 11 * gamePanel.tileSize;

        gamePanel.objects[4] = new DoorObject();
        gamePanel.objects[4].worldX = 8 * gamePanel.tileSize;
        gamePanel.objects[4].worldY = 28 * gamePanel.tileSize;

        gamePanel.objects[5] = new DoorObject();
        gamePanel.objects[5].worldX = 12 * gamePanel.tileSize;
        gamePanel.objects[5].worldY = 22 * gamePanel.tileSize;
    
        gamePanel.objects[6] = new ChestObject();
        gamePanel.objects[6].worldX = 10 * gamePanel.tileSize;
        gamePanel.objects[6].worldY = 7 * gamePanel.tileSize;

        gamePanel.objects[7] = new BootsObject();
        gamePanel.objects[7].worldX = 37 * gamePanel.tileSize;
        gamePanel.objects[7].worldY = 42 * gamePanel.tileSize;
    }
}
