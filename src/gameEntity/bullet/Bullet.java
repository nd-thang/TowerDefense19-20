package gameEntity.bullet;

import gameEntity.EntityBase;
import gameEntity.enemy.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Bullet extends EntityBase {
    Image explode;
    ImageView explodeView;
    Enemy target;
    double rotationEasing = 10;
    double damage;
    double velocity;

    public Bullet(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr, Image explode, double velocity) {
        super(layer, image, x, y, r, dx, dy, dr);
        this.explode = explode;
        this.velocity = velocity;

//        this.explodeView = new ImageView(explode);
//        double topleftX = x - explode.getWidth() / 2;
//        double topleftY = y - explode.getHeight() / 2;
//
//        this.explodeView.relocate(topleftX, topleftY);
//        this.explodeView.setRotate(r);
//
//        layer.getChildren().add(explodeView);
    }

    public void move() {
        if (target != null) {
            double deltaX = target.getX() - this.getX();
            double deltaY = target.getY() - this.getY();
            double angle = Math.abs(Math.atan(deltaY / deltaX) / Math.PI * 180);
            double targetAngle = 0;
            if (deltaX >= 0 && deltaY <= 0) {
                targetAngle = 90 - angle;
                this.setX(this.getX() + velocity * Math.cos(angle / 180 * Math.PI));
                this.setY(this.getY() - velocity * Math.sin(angle / 180 * Math.PI));
            }
            if (deltaX <= 0 && deltaY <= 0) {
                targetAngle = 270 + angle;
                this.setX(this.getX() - velocity * Math.cos(angle / 180 * Math.PI));
                this.setY(this.getY() - velocity * Math.sin(angle / 180 * Math.PI));
            }
            if (deltaX <= 0 && deltaY >= 0) {
                targetAngle = 270 - angle;
                this.setX(this.getX() - velocity * Math.cos(angle / 180 * Math.PI));
                this.setY(this.getY() + velocity * Math.sin(angle / 180 * Math.PI));
            }
            if (deltaX >= 0 && deltaY >= 0) {
                targetAngle = 90 + angle;
                this.setX(this.getX() + velocity * Math.cos(angle / 180 * Math.PI));
                this.setY(this.getY() + velocity * Math.sin(angle / 180 * Math.PI));
//                System.out.println(this.getX());
//                System.out.println(this.getY());
//                System.out.println(Math.cos(angle / 180 * Math.PI));
//                System.out.println(Math.sin(angle / 180 * Math.PI));
//                System.out.println(targetAngle);
            }
            if (Math.abs(targetAngle - this.getR()) > 180) {
                this.setR(this.getR() + -(360 - Math.abs(targetAngle - this.getR())) / rotationEasing);
            } else {
                this.setR(this.getR() + (targetAngle - this.getR()) / rotationEasing);
            }
        } else {
            //tạm thời nếu viên đạn bắn ra mà dịch chết trước rồi thì cũng biến mất luôn.
            // nếu để nó bay tiếp với tọa độ địch ở chỗ đó thì chưa xử lí được nếu trúng con khác(bắn trượt)
            this.setRemovable(true);
        }
        super.move();
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public Image getExplode() {
        return explode;
    }

    public void setExplode(Image explode) {
        this.explode = explode;
    }
}
