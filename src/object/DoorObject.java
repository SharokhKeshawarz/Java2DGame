package src.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class DoorObject extends SuperObject {
    public DoorObject() {
        this.name = "Door";
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/assets/Object/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.collision = true;
    }
}
