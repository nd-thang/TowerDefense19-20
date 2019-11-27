import GameEntity.enemy.Enemy;
import GameEntity.weapon.SingleCannon;
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
import java.util.Set;

public class Game extends Application {
//    private Timeline t;
    AnimationTimer gameloop;
    boolean isRunning = true;

    Pane backgroundLayer;
    GridPane infoLayer;
    Pane playfiledLayer;

    List<Weapon> weapons = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();

    int lives = 19;
    double money = 100;

    Text levelLabel = new Text("Level: ");
    Text moneyLabel = new Text(Double.toString(money));
    Text livesLabel = new Text("Lives");
    Button pause = new Button("Pause");
    Button nextLevel = new Button("Next Level");
    Button sell = new Button("Sell");

    ImageView singleCannon = new ImageView(Settings.SCANNON_IMG);
    ImageView singleRocket = new ImageView(Settings.SROCKET_IMG);
    ImageView missile = new ImageView(Settings.MISSILE_IMG);


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
        //update front - end infolayer
        if (money < Settings.SCANNON_COST) singleCannon.setOpacity(0.2);
        if (money < Settings.SROCKET_COST) singleRocket.setOpacity(0.2);
        if (money < Settings.MISSILE_COST) missile.setOpacity(0.2);
        moneyLabel.setText(Double.toString(money));
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
        infoLayer.setTranslateX(Settings.PIXS_PER_PIC * Settings.MAP_COL);
        //infoLayer.setLayoutX(Settings.PIXS_PER_PIC * 10);
        //infoLayer.setAlignment(Pos.CENTER);

        pause.setOnMouseClicked(e ->{
//           [làm sau]các nút vẫn hoạt động bình thường vì chỉ dừng phần trong gameloop
//           (tiền vẫn trừ và hiển thị lại sau khi nhấn pause)
            if(isRunning) {
                gameloop.stop();
                isRunning = false;
            } else {
                gameloop.start();
                isRunning = true;
            }
        });

        sell.setOnMouseClicked(e ->{
            money -= 10;
        });

        //test
        singleCannon.setOnMouseClicked(e -> {
            //bấm thử
            //singleCannon.setOpacity(Math.abs(singleCannon.getOpacity() - 1.2));
            if(money > Settings.SCANNON_COST) {
                playfiledLayer.setOnMouseClicked(e2 ->{
                    double centerX = e2.getX() - ((int) e2.getX()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    double centerY = e2.getY() - ((int) e2.getY()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    createWeapon(centerX, centerY, "SCANNON");
                    //setonmouseclicked to do nothing
                    playfiledLayer.setOnMouseClicked(e3 ->{});
                });
            }
        });

        singleRocket.setOnMouseClicked(e -> {
            //bấm thử
            //singleCannon.setOpacity(Math.abs(singleCannon.getOpacity() - 1.2));
            if(money > Settings.SROCKET_COST) {
                playfiledLayer.setOnMouseClicked(e2 ->{
                    double centerX = e2.getX() - ((int) e2.getX()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    double centerY = e2.getY() - ((int) e2.getY()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    createWeapon(centerX, centerY, "SROCKET");
                    //setonmouseclicked to do nothing
                    playfiledLayer.setOnMouseClicked(e3 ->{});
                });
            }
        });

        missile.setOnMouseClicked(e -> {
            //bấm thử
            //singleCannon.setOpacity(Math.abs(singleCannon.getOpacity() - 1.2));
            if(money > Settings.MISSILE_COST) {
                playfiledLayer.setOnMouseClicked(e2 ->{
                    double centerX = e2.getX() - ((int) e2.getX()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    double centerY = e2.getY() - ((int) e2.getY()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    createWeapon(centerX, centerY, "MISSILE");
                    //setonmouseclicked to do nothing
                    playfiledLayer.setOnMouseClicked(e3 ->{});
                });
            }
        });
    }

    private void loadBasement(double centerX, double centerY, Image img){
        ImageView boGView = new ImageView(img);
        boGView.setX(centerX - img.getWidth() / 2);
        boGView.setY(centerY - img.getHeight() / 2);
        playfiledLayer.getChildren().add(boGView);
    }

    private void createWeapon(double centerX, double centerY, String type) {
        switch (type){
            case "SCANNON":
                loadBasement(centerX, centerY, Settings.SCANNON_BASE_IMG);
                SingleCannon newSCannon = new SingleCannon(playfiledLayer, Settings.SCANNON_IMG, centerX, centerY, 0,0,0,0,Settings.SCANNON_DAMAGE,Settings.SCANNON_COST, Settings.SCANNON_ATKSPEED);
                weapons.add(newSCannon);
                money -= Settings.SCANNON_COST;
                break;
            case "SROCKET":
                loadBasement(centerX, centerY, Settings.SROCKET_BASE_IMG);
                SingleCannon newSRocket = new SingleCannon(playfiledLayer, Settings.SROCKET_IMG, centerX, centerY, 0,0,0,0,Settings.SROCKET_DAMAGE,Settings.SROCKET_COST, Settings.SROCKET_ATKSPEED);
                weapons.add(newSRocket);
                money -= Settings.SROCKET_COST;
                break;
            case "MISSILE":
                loadBasement(centerX, centerY, Settings.MISSILE_BASE_IMG);
                SingleCannon newMissile = new SingleCannon(playfiledLayer, Settings.MISSILE_IMG, centerX, centerY, 0,0,0,0,Settings.MISSILE_DAMAGE,Settings.MISSILE_COST, Settings.MISSILE_ATKSPEED);
                weapons.add(newMissile);
                money -= Settings.MISSILE_COST;
                break;
        }

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

        playfiledLayer = new Pane();
        root.getChildren().add(playfiledLayer);
        playfiledLayer.setPrefSize(Settings.PIXS_PER_PIC * 10, Settings.PIXS_PER_PIC * 7);


        //kẻ sọc
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
            line.setStartX(Settings.PIXS_PER_PIC * (col + 1));
            line.setEndY(colEndY);
            line.setEndX(Settings.PIXS_PER_PIC * (col + 1));
            l.add(line);
        }
        for (Line line : l){
            root.getChildren().add(line);
        };


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
