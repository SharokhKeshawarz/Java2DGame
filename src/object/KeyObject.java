package src.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class KeyObject extends SuperObject {
    public KeyObject() {
        this.name = "Key";
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/assets/Object/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
