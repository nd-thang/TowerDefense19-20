package GameEntity.weapon;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SingleCannon extends Weapon {
    public SingleCannon(Pane layer, Image img, double x, double y, double r, double dx, double dy, double dr, double damage, double cost, double attackSpeed) {
        super(layer, img, x, y, r, dx, dy, dr, damage, cost, attackSpeed);
    }
}
