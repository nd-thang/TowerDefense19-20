package gameEntity.weapon;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class DoubleCannon extends Weapon {
    public DoubleCannon(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr, double damage, double cost, double attackSpeed, double attackRange, Image basement) {
        super(layer, image, x, y, r, dx, dy, dr, damage, cost, attackSpeed, attackRange, basement);
    }
}
