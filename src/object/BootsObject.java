package src.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class BootsObject extends SuperObject {
    public BootsObject() {
        this.name = "Boots";
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/assets/Object/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
