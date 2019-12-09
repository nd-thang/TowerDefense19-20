package gameEntity.enemy;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class NormalEnemy extends Enemy {
    public NormalEnemy(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr, double health, double reward, double speed) {
        super(layer, image, x, y, r, dx, dy, dr, health, reward, speed);
    }
}
