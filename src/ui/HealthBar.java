package ui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class HealthBar extends Pane {

    Rectangle outerHealthRect;
    Rectangle innerHealthRect;
    double outerWidth;

    public HealthBar(double outerWidth) {

        double height = 5;

        this.outerWidth = outerWidth;
        double innerWidth = 0;

        double x = 0.0;
        double y = 0.0;

        outerHealthRect = new Rectangle(x, y, outerWidth, height);
//        outerHealthRect.setStroke(Color.BLACK);
//        outerHealthRect.setStrokeWidth(2);
//        outerHealthRect.setStrokeType(StrokeType.OUTSIDE);
        outerHealthRect.setFill(Color.RED);

        innerHealthRect = new Rectangle(x, y, innerWidth, height);
//        innerHealthRect.setStrokeType(StrokeType.OUTSIDE);
        innerHealthRect.setFill(Color.LIMEGREEN);

        getChildren().addAll(outerHealthRect, innerHealthRect);

    }

    public double getOuterWidth() {
        return outerWidth;
    }

    public void setOuterWidth(double outerWidth) {
        this.outerWidth = outerWidth;
    }

    public void setValue(double value) {
        innerHealthRect.setWidth(outerHealthRect.getWidth() * value);
    }

    public Rectangle getOuterHealthRect() {
        return outerHealthRect;
    }

    public void setOuterHealthRect(Rectangle outerHealthRect) {
        this.outerHealthRect = outerHealthRect;
    }

    public Rectangle getInnerHealthRect() {
        return innerHealthRect;
    }

    public void setInnerHealthRect(Rectangle innerHealthRect) {
        this.innerHealthRect = innerHealthRect;
    }
}
