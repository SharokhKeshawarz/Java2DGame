package src.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ChestObject extends SuperObject {
    public ChestObject() {
        this.name = "Chest";
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/assets/Object/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
