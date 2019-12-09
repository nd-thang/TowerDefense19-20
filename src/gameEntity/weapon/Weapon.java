package gameEntity.weapon;

import gameEntity.EntityBase;
import gameEntity.enemy.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class Weapon extends EntityBase {
    double damage;
    double cost ;
    double attackSpeed;
    double attackRange;
    Enemy target;
    int timeForShoot;
    Image basement;
    ImageView basementView;

    double rotationEasing = 10; // the higher the value, the slower the rotation



    public Weapon(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr, double damage, double cost, double attackSpeed, double attackRange, Image basement) {
        super(layer, image, x, y, r, dx, dy, dr);
        this.damage = damage;
        this.cost = cost;
        this.attackSpeed = attackSpeed;
        this.attackRange = attackRange;
        timeForShoot = (int) (60 / attackSpeed) ;
        this.basement = basement;

        this.basementView = new ImageView(basement);
        double topleftX = x - basement.getWidth() / 2;
        double topleftY = y - basement.getHeight() / 2;

        this.basementView.relocate(topleftX, topleftY);
        //add first to the root (nếu không thì hiện đè lên hình khẩu súng)
        layer.getChildren().add(0, basementView);
    }

    public void move(){
        if(target != null){
            double deltaX = target.getX() - this.getX();
            double deltaY = target.getY() - this.getY();
            double angle = Math.abs(Math.atan(deltaY / deltaX) / Math.PI * 180);
            double targetAngle = 0;
            if (deltaX >= 0 && deltaY <= 0) targetAngle = 90 - angle;
            if (deltaX <= 0 && deltaY <= 0) targetAngle = 270 + angle;
            if (deltaX <= 0 && deltaY >= 0) targetAngle = 270 - angle;
            if (deltaX >= 0 && deltaY >= 0) targetAngle = 90 + angle;
            //xử lí khi xoay qua gốc 0 độ cho mượt, số 5 và 355 giới hạn lại trường hợp đi từ từ qua,
            // nếu để 90, 270 thì trường hợp đang không có target mà 1 con địch đi vào thì xoay không mượt.
            //vẫn còn trường hợp góc xoay đột ngột lớn thì vẫn chưa xoay được theo vòng gần hơn VD: cur = 5, target = 350 !!
//            if ((this.getR() < 5 && targetAngle > 355) || (this.getR() > 355 && targetAngle < 5)) {
//                this.setR(this.getR() + (targetAngle - this.getR()) / 1);
//            } else {
//            this.setR(this.getR() + (targetAngle - this.getR()) / roatationEasing);
//            }

            //reset khi góc lớn 360
            if (this.getR() > 360) this.setR(this.getR() - 360);
            //quay theo cách quay gần nhất(nếu góc quay > 180 thì quay với góc bù và ngươc lại)
            if(Math.abs(targetAngle - this.getR()) > 180) {
                this.setR(this.getR() - (360 - Math.abs(targetAngle - this.getR())) / rotationEasing);
            } else {
                this.setR(this.getR() + (targetAngle - this.getR()) / rotationEasing);
            }
        }
        super.move();
    }

    public void findTarget(List<Enemy> enemies){
        double distance, minDistance = 0;
        Enemy closetTarget = null;
        for (Enemy enemy: enemies){
            double a = this.getX() - enemy.getX();
            double b = this.getY() - enemy.getY();
            distance = Math.sqrt(a * a + b * b);
            if (Double.compare(distance, this.attackRange) > 0) {
                continue;
            } else {
                if (closetTarget == null) {
                    minDistance = distance;
                    closetTarget = enemy;
                } else {
                    if (Double.compare(distance, minDistance) < 0){
                        closetTarget = enemy;
                        minDistance = distance;
                    }
                }
            }
        }
        this.target = closetTarget;
        //reset timeForShoot;
//       this.timeForShoot = (int) (60 / attackSpeed) - 1;
    };

    public void checkTarget(){
        if(Double.compare(target.getHealth(), 0) < 0 || target.isRemovable()) {
            setTarget(null);
            return;
        }
        double a = this.getX() - target.getX();
        double b = this.getY() - target.getY();
        double distance = Math.sqrt(a * a + b * b);
        if (Double.compare(distance, this.attackRange) > 0) {
            setTarget(null);
        }
    }

    public boolean shoot(){
        if(timeForShoot == (int) (60 / attackSpeed)) {
            timeForShoot = 0;
            return true;
        }
        timeForShoot++;
        return false;
    }

    @Override
    public void removeFromLayer() {
        super.removeFromLayer();
        this.getLayer().getChildren().remove(basementView);
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(double attackRange) {
        this.attackRange = attackRange;
    }
}
