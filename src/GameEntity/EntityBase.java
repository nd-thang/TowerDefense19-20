package GameEntity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class EntityBase {
    Pane layer;
    Image image;
    ImageView imageView;

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

    public void move(){}
    public void updateUI(){

    }




}
