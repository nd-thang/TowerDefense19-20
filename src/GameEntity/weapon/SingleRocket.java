package GameEntity.weapon;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SingleRocket extends Weapon {
    public SingleRocket(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr, double damage, double cost, double attackSpeed, double attackRange) {
        super(layer, image, x, y, r, dx, dy, dr, damage, cost, attackSpeed, attackRange);
    }
}
