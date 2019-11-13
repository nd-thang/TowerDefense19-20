import GameEntity.enemy.Enemy;
import GameEntity.weapon.Weapon;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Game extends Application {

    Pane backgroundLayer;
    Pane infoLayer;

    List<Weapon> weapons = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();


    private void createBackgroundLayer() {
        final String[][] MAP_SPRITES = new String[][] {
                { "024", "024", "003", "047", "047", "047", "004", "024", "024", "024" },
                { "024", "024", "025", "299", "001", "002", "023", "024", "024", "024" },
                { "024", "024", "025", "023", "024", "025", "023", "024", "024", "024" },
                { "003", "047", "048", "023", "024", "025", "023", "024", "024", "024" },
                { "025", "299", "001", "027", "024", "025", "046", "047", "047", "047" },
                { "025", "023", "024", "024", "024", "026", "001", "001", "001", "001" },
                { "025", "023", "024", "024", "024", "024", "024", "024", "024", "024" },
        };

        for (int row = 0; row < MAP_SPRITES.length; row++) {
            for (int col = 0; col < MAP_SPRITES[0].length; col++) {
                ImageView background = new ImageView(new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile" + MAP_SPRITES[row][col] + ".png"));
                background.setX(col * 64);
                background.setY(row * 64);
                backgroundLayer.getChildren().add(background);
            }
        }

        //test thêm hình xe tăng
        ImageView background = new ImageView(new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile" + "268" + ".png"));
        ImageView background2 = new ImageView(new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile" + "291" + ".png"));
        background.setX(32);
        background2.setX(32);
        background.setY(6 * 64);
        background2.setY(6 * 64);
        background.setRotate(-90);
        background2.setRotate(-90);
        backgroundLayer.getChildren().add(background);
        backgroundLayer.getChildren().add(background2);






    }

    private void createInfoLayer(){

    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();

        backgroundLayer = new Pane();
        root.getChildren().add(backgroundLayer);
        createBackgroundLayer();

        infoLayer = new Pane();
        root.getChildren().add(infoLayer);
        createInfoLayer();

        //ke sọc
        Line l1 = new Line();
        List<Line> l = new ArrayList<>();
        double colStartY = 0;
        double colEndY = 64.0 * 7;
        double rowStartX = 0;
        double rowEndX = 64.0 * 10;

        for (int row = 0; row < 6; row++){
            Line line = new Line();
            line.setStartX(rowStartX);
            line.setStartY(64.0 * (row + 1));
            line.setEndX(rowEndX);
            line.setEndY(64.0 * (row + 1));
            l.add(line);
        }

        for (int col = 0; col < 9; col++){
            Line line = new Line();
            line.setStartY(colStartY);
            line.setStartX(64.0 * (col + 1));
            line.setEndY(colEndY);
            line.setEndX(64.0 * (col + 1));
            l.add(line);
        }
        for (Line line : l){
            root.getChildren().add(line);
        };


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        AnimationTimer gameloop = new AnimationTimer() {
            @Override
            public void handle(long l) {

            }
        };

        gameloop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
