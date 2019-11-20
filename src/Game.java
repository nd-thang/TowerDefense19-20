import GameEntity.enemy.Enemy;
import GameEntity.weapon.Weapon;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Game extends Application {
//    private Timeline t;
    AnimationTimer gameloop;

    Pane backgroundLayer;
    GridPane infoLayer;
    GridPane playfiledLayer;

    List<Weapon> weapons = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();

    int lives = 19;
    public double money = 100;

    Text levelLabel = new Text("Level: ");
    Text moneyLabel = new Text(Double.toString(money));
    Text livesLabel = new Text("Lives");
    Button pause = new Button("Pause");
    Button nextLevel = new Button("Next Level");
    Button sell = new Button("Sell");

    ImageView singleCannon = new ImageView(new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile249" + ".png"));
    ImageView singleRocket = new ImageView(new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile206" + ".png"));
    ImageView missile = new ImageView(new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile204" + ".png"));




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
        /*ImageView background = new ImageView(new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile" + "268" + ".png"));
        ImageView background2 = new ImageView(new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile" + "291" + ".png"));
        background.setX(32);
        background2.setX(32);
        background.setY(6 * 64);
        background2.setY(6 * 64);
        background.setRotate(-90);
        background2.setRotate(-90);
        backgroundLayer.getChildren().add(background);
        backgroundLayer.getChildren().add(background2);*/






    }

    private void updateInfoLayer(){
        if (money < Settings.SINGLE_CANNON_COST) singleCannon.setOpacity(0.2);
        if (money < Settings.SINGLE_ROCKET_COST) singleRocket.setOpacity(0.2);
        if (money < Settings.MISSILE_COST) missile.setOpacity(0.2);
    }

    private void createInfoLayer(){
        infoLayer.add(levelLabel,0,0);
        infoLayer.add(moneyLabel,0,1);
        infoLayer.add(livesLabel,3,1);
        infoLayer.add(pause,0,5);
        infoLayer.add(nextLevel,0,6);
        infoLayer.add(sell,0,7);
        infoLayer.add(singleCannon,0,3);
        infoLayer.add(singleRocket,1,3);
        infoLayer.add(missile,2,3);
        infoLayer.setHgap(5);
        infoLayer.setVgap(10);
        Pane pane = new Pane();

        pause.setOnMouseClicked(e ->{
            gameloop.stop();
        });


        sell.setOnMouseClicked(e ->{
            money -= 10;
            moneyLabel.setText(Double.toString(money));
        });

        //test
        singleCannon.setOnMouseClicked(e -> {
            singleCannon.setOpacity(Math.abs(singleCannon.getOpacity() - 1.2));
        });


        //infoLayer.setFillHeight(new Pane());

        infoLayer.setTranslateX(Settings.PIXS_PER_PIC * Settings.MAP_COL);
        //infoLayer.setLayoutX(Settings.PIXS_PER_PIC * 10);
        //infoLayer.setAlignment(Pos.CENTER);


    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();

        backgroundLayer = new Pane();
        root.getChildren().add(backgroundLayer);
        createBackgroundLayer();

        infoLayer = new GridPane();
        root.getChildren().add(infoLayer);
        createInfoLayer();

        //kẻ sọc
        /*Line l1 = new Line();
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
        };*/


        Scene scene = new Scene(root);
//        scene.setFill(Color.BLUE);
        stage.setScene(scene);
        stage.show();

        gameloop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                updateInfoLayer();


            }
        };

        gameloop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
