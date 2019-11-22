package GameEntity.weapon;

import GameEntity.EntityBase;
import GameEntity.enemy.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Weapon extends EntityBase {
    double damage;
    double cost ;
    double attackSpeed;

    public Weapon(Pane layer, Image img, double x, double y, double r, double dx, double dy, double dr, double damage, double cost, double attackSpeed){
        super(layer, img, x, y, r, dx, dy, dr);
        this.damage = damage;
        this.cost = cost;
        this.attackSpeed = attackSpeed;
    }

    public Enemy findTarget(){return null;};
    public void setTarget(){};





}
