package GameEntity.enemy;

import GameEntity.EntityBase;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Enemy extends EntityBase {
    double health;
    double reward;
    double speed;

    public Enemy(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr, double health, double reward, double speed) {
        super(layer, image, x, y, r, dx, dy, dr);
        this.health = health;
        this.reward = reward;
        this.speed = speed;
    }
}
