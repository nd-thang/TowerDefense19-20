package gameEntity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class EntityBase {
    Pane layer;
    Image image;
    ImageView imageView;

    boolean removable = false;

    double x;
    double y;
    double r;
    double dx;
    double dy;
    double dr;

    public EntityBase(Pane layer, Image image, double x, double y, double r, double dx, double dy, double dr) {
        this.layer = layer;
        this.image = image;
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
        this.dr = dr;

        this.imageView = new ImageView(image);
        double topleftX = x - image.getWidth() / 2;
        double topleftY = y - image.getHeight() / 2;

        this.imageView.relocate(topleftX, topleftY);
        this.imageView.setRotate(r);

        layer.getChildren().add(imageView);
    }

    public void move(){
        x += dx;
        y += dy;
        r += dr;

        double topleftX = x - image.getWidth() / 2;
        double topleftY = y - image.getHeight() / 2;

        this.imageView.relocate(topleftX, topleftY);
        this.imageView.setRotate(r);
    }

    public void updateUI(){
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    public Pane getLayer() {
        return layer;
    }

    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDr() {
        return dr;
    }

    public void setDr(double dr) {
        this.dr = dr;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }
}
