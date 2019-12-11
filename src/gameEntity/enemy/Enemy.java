package gameEntity.enemy;

import gameEntity.EntityBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ui.HealthBar;

public class Enemy extends EntityBase {
    double health;
    double reward;
    double speed;

    HealthBar healthBar;
    double healthMax;

    int movingPhase = 0;// pha di chuyển (hardcode)

    public Enemy(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr, double health, double reward, double speed) {
        super(layer, image, x, y, r, dx, dy, dr);
        this.health = health;
        this.reward = reward;
        this.speed = speed;
        healthMax = health;

        healthBar = new HealthBar(image.getWidth() / 2);
        this.getLayer().getChildren().add(this.healthBar);
    }

    public void move2() {
        switch (movingPhase) {
            case 0:
                this.setDx(0);
                this.setDy(-speed);
                this.setR(-90);
                break;
            case 1:
                this.setDx(speed);
                this.setDy(0);
                this.setR(0);
                break;
            case 2:
                this.setDx(0);
                this.setDy(speed);
                this.setR(90);
                break;
            case 3:
                this.setDx(-speed);
                this.setDy(0);
                this.setR(0);
                break;

        }
        //chuyển hướng di chuyển
        if (movingPhase == 0 && Double.compare(this.getY(), 64 * 1) <= 0) movingPhase++;
        if (movingPhase == 1 && Double.compare(this.getX(), 64 * 6) >= 0) movingPhase++;
        if (movingPhase == 2 && Double.compare(this.getY(), 64 * 4) >= 0) movingPhase++;
        if (movingPhase == 3 && Double.compare(this.getX(), 64 * 3) <= 0) movingPhase = 0;
        super.move();
    }

    @Override
    public void move() {
        switch (movingPhase) {
            case 0:

            case 2:
                this.setDx(0);
                this.setDy(-speed);
                this.setR(-90);
                break;
            case 1:

            case 3:

            case 5:
                this.setDx(speed);
                this.setDy(0);
                this.setR(0);
                break;

            case 4:
                this.setDx(0);
                this.setDy(speed);
                this.setR(90);
                break;
        }
        //chuyển hướng di chuyển
        if (movingPhase == 0 && Double.compare(this.getY(), 64 * 4) <= 0) movingPhase++;
        if (movingPhase == 1 && Double.compare(this.getX(), 64 * 3) >= 0) movingPhase++;
        if (movingPhase == 2 && Double.compare(this.getY(), 64 * 1) <= 0) movingPhase++;
        if (movingPhase == 3 && Double.compare(this.getX(), 64 * 6) >= 0) movingPhase++;
        if (movingPhase == 4 && Double.compare(this.getY(), 64 * 5) >= 0) movingPhase++;
        super.move();
    }

    public void updateHealthBar() {
        healthBar.setValue(health / healthMax);
        healthBar.relocate(this.getX() - this.getImage().getWidth() / 4, this.getY() - this.getImage().getHeight() / 4 - 6);
    }

    @Override
    public void removeFromLayer() {
        super.removeFromLayer();
        this.getLayer().getChildren().remove(healthBar);
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
