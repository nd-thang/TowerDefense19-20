import gameEntity.EntityBase;
import gameEntity.bullet.Bullet;
import gameEntity.enemy.Enemy;
import gameEntity.enemy.NormalEnemy;
import gameEntity.enemy.SmallerEnemy;
import gameEntity.enemy.TankerEnemy;
import gameEntity.weapon.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import javax.sound.sampled.AudioInputStream;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;

public class Game extends Application {
    private AnimationTimer gameLoop;
    boolean isRunning = true;
    boolean isSelling = false;
    boolean isUpgrading = false;
    boolean isPlayAgain = false;

    Stage stage = new Stage();
    Pane backgroundLayer;
    GridPane infoLayer;
    Pane playfieldLayer;
    Group root = new Group();
    Scene scene = new Scene(root);

    List<Weapon> weapons = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();

    int lives = Settings.STARTING_LIVES;
    int level = 1;
    int levelMax = 10;
    double money = Settings.STARTING_MONEY;

    int timeCount = 0;

    Text levelLabel = new Text();
    Text levelText = new Text("Level: ");
    Text moneyLabel = new Text(Double.toString(money));
    Text livesLabel = new Text(String.format("%d", lives));
    Button pause = new Button("Pause");
    Button nextLevel = new Button("Next Level");
    Button sell = new Button("Sell");
    Button upgrade = new Button("Upgrade");

    ImageView singleCannon = new ImageView(Settings.SCANNON_IMG);
    ImageView singleRocket = new ImageView(Settings.SROCKET_IMG);
    ImageView missile = new ImageView(Settings.MISSILE_IMG);
    ImageView dollarImage = new ImageView("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile287" + ".png");
    ImageView heartImage = new ImageView("file:resources/img/xmas-christmas-december-winter-holdiays_71-256.png");

    ColorAdjust colorAdjust = new ColorAdjust();

    final String[][] MAP_SPRITES = new String[][]{
            {"024", "024", "003", "047", "047", "047", "004", "024", "024", "024"},
            {"024", "024", "025", "299", "001", "002", "023", "024", "024", "024"},
            {"024", "024", "025", "023", "024", "025", "023", "024", "024", "024"},
            {"003", "047", "048", "023", "024", "025", "023", "024", "024", "024"},
            {"025", "299", "001", "027", "024", "025", "046", "047", "047", "047"},
            {"025", "023", "024", "024", "024", "026", "001", "001", "001", "001"},
            {"025", "023", "024", "024", "024", "024", "024", "024", "024", "024"},
    };

    private void createBackgroundLayer() {
        for (int row = 0; row < MAP_SPRITES.length; row++) {
            for (int col = 0; col < MAP_SPRITES[0].length; col++) {
                ImageView background = new ImageView(new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile" + MAP_SPRITES[row][col] + ".png"));
                background.setX(col * 64);
                background.setY(row * 64);
                backgroundLayer.getChildren().add(background);
            }
        }
    }

    private void updateInfoLayer() {
        //update front - end infolayer
        //opacity chuyển từ 0.2 thành 1 và ngược lại mỗi khi thỏa mãn hay không tm điều kiện.
        if (money < Settings.SCANNON_COST) {
            singleCannon.setOpacity(0.2);
        } else {
            singleCannon.setOpacity(1);
        }
        if (money < Settings.SROCKET_COST) {
            singleRocket.setOpacity(0.2);
        } else {
            singleRocket.setOpacity(1);
        }
        if (money < Settings.MISSILE_COST) {
            missile.setOpacity(0.2);
        } else {
            missile.setOpacity(1);
        }
        moneyLabel.setText(Double.toString(money));
        livesLabel.setText(String.format("%d", lives));
        levelLabel.setText(String.format("%d / %d", level, levelMax));
    }

    private void createInfoLayer() {
        infoLayer.add(levelText, 0, 0);
        infoLayer.add(levelLabel, 1, 0);
        infoLayer.add(moneyLabel, 1, 1);
        infoLayer.add(dollarImage, 0, 1);
        infoLayer.add(livesLabel, 1, 2);
        infoLayer.add(heartImage, 0, 2);
        infoLayer.add(pause, 0, 6);
        infoLayer.add(nextLevel, 0, 7);
        infoLayer.add(sell, 1, 6);
        infoLayer.add(upgrade, 1, 7);
        infoLayer.add(singleCannon, 0, 4);
        infoLayer.add(singleRocket, 1, 4);
        infoLayer.add(missile, 2, 4);
        infoLayer.setHgap(5);
        infoLayer.setVgap(10);

        infoLayer.setTranslateX(Settings.PIXS_PER_PIC * Settings.MAP_COL);
        //infoLayer.setLayoutX(Settings.PIXS_PER_PIC * 10);
        //infoLayer.setAlignment(Pos.CENTER);

        colorAdjust.setHue(0.2);
        colorAdjust.setContrast(0.3);

        ImageView pause_img = new ImageView("file:resources/img/imageedit_8_4462462831.png");
        ImageView play_img = new ImageView("file:resources/img/imageedit_10_4305241141.png");
        ImageView upgrade_img = new ImageView("file:resources/img/imageedit_12_3438214095.png");
        ImageView sell_img = new ImageView("file:resources/img/imageedit_14_4597323212.png");
        ImageView next_level_img = new ImageView("file:resources/img/imageedit_16_6201890802.png");

        dollarImage.setFitHeight(32);
        dollarImage.setPreserveRatio(true);
//        infoLayer.setColumnSpan(dollarImage,2);
//        infoLayer.add(dollarImage,1,1,3,1);
        heartImage.setFitHeight(32);
        heartImage.setPreserveRatio(true);
        GridPane.setHalignment(dollarImage, HPos.RIGHT);
        GridPane.setHalignment(levelText, HPos.RIGHT);
        GridPane.setHalignment(heartImage, HPos.RIGHT);

        pause.setGraphic(pause_img);
        upgrade.setGraphic(upgrade_img);
//        upgrade.setDisable(true);
        sell.setGraphic(sell_img);
//        sell.setFont(Font.font("futura", FontWeight.BOLD, FontPosture.REGULAR, 20));
        nextLevel.setGraphic(next_level_img);


        pause.setOnMouseClicked(e -> {
            if (isRunning) {
                gameLoop.stop();
                isRunning = false;
                pause.setGraphic(play_img);
            } else {
                gameLoop.start();
                isRunning = true;
                pause.setGraphic(pause_img);
            }
        });

        sell.setOnMouseClicked(e -> {
            if (isRunning) {
                if (isSelling) {
                    isSelling = false;
                    scene.setCursor(null);
                } else {
                    isSelling = true;
                    Image cursor = new Image("file:resources/img/imageedit_14_4597323212.png");
                    scene.setCursor(new ImageCursor(cursor));
                }
            }

        });

        upgrade.setOnMouseClicked(e -> {
            if (isRunning) {
                if (isUpgrading) {
                    isUpgrading = false;
                    scene.setCursor(null);
                } else {
                    isUpgrading = true;
                    Image cursor = new Image("file:resources/img/imageedit_12_3438214095.png");
                    scene.setCursor(new ImageCursor(cursor));
                }
            }
        });

        //todo: setonMouse moved? hay gì đó để hiện thông số
        singleCannon.setOnMouseClicked(e -> {
            //bấm thử
            //singleCannon.setOpacity(Math.abs(singleCannon.getOpacity() - 1.2));
            if (money > Settings.SCANNON_COST && isRunning) {
                //todo: bấm lại để hủy đặt súng
                scene.setCursor(new ImageCursor(Settings.SCANNON_IMG));
                //hiện tầm đánh
                Circle range = new Circle();
                range.setRadius(Settings.SCANNON_ATKRANGE);
                range.setFill(null);
                playfieldLayer.getChildren().add(range);

                playfieldLayer.setOnMouseMoved(e3 -> {
                    range.setCenterX(e3.getX());
                    range.setCenterY(e3.getY());
                    range.setStroke(Color.BLUE);
                    double centerX = e3.getX() - ((int) e3.getX()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    double centerY = e3.getY() - ((int) e3.getY()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    int c = (int) (centerX / Settings.PIXS_PER_PIC);
                    int r = (int) (centerY / Settings.PIXS_PER_PIC);
                    //"024" là cỏ
                    //if ngoài để tránh IndexOutofBound khi di chuột ngoài sân chơi
                    if (c < Settings.MAP_COL && r < Settings.MAP_ROW) {
                        if (!MAP_SPRITES[r][c].equals("024")) {
                            //Scannon phủ màu đỏ khi không phải cỏ
                            scene.setCursor(new ImageCursor(new Image("file:resources/img/imageedit_1_2727007244.png")));
                        } else {
                            scene.setCursor(new ImageCursor(Settings.SCANNON_IMG));
                        }
                    }
                });

                playfieldLayer.setOnMouseClicked(e2 -> {
                    double centerX = e2.getX() - ((int) e2.getX()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    double centerY = e2.getY() - ((int) e2.getY()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    int c = (int) (centerX / Settings.PIXS_PER_PIC);
                    int r = (int) (centerY / Settings.PIXS_PER_PIC);
                    //"024" là cỏ, chỉ cho đặt súng trên ô này
                    //if ngoài để tránh IndexOutofBound khi di chuột ngoài sân chơi
                    if (c < Settings.MAP_COL && r < Settings.MAP_ROW) {
                        if (MAP_SPRITES[r][c].equals("024")) {
                            createWeapon(centerX, centerY, "SCANNON");
                            scene.setCursor(null);
                            playfieldLayer.getChildren().remove(range);
                            //setonmouseclicked to do nothing
                            playfieldLayer.setOnMouseClicked(e3 -> {
                            });
                            playfieldLayer.setOnMouseMoved(e6 -> {
                            });
                        }
                    }
                });
            }
        });

        singleRocket.setOnMouseClicked(e -> {
            //bấm thử
            //singleCannon.setOpacity(Math.abs(singleCannon.getOpacity() - 1.2));
            if (money > Settings.SROCKET_COST && isRunning) {
                scene.setCursor(new ImageCursor(Settings.SROCKET_IMG));
                //hiện tầm đánh
                Circle range = new Circle();
                range.setRadius(Settings.SROCKET_ATKRANGE);
                range.setFill(null);
                playfieldLayer.getChildren().add(range);

                playfieldLayer.setOnMouseMoved(e3 -> {
                    range.setCenterX(e3.getX());
                    range.setCenterY(e3.getY());
                    range.setStroke(Color.BLUE);
                    double centerX = e3.getX() - ((int) e3.getX()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    double centerY = e3.getY() - ((int) e3.getY()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    int c = (int) (centerX / Settings.PIXS_PER_PIC);
                    int r = (int) (centerY / Settings.PIXS_PER_PIC);
                    //"024" là cỏ
                    //if ngoài để tránh IndexOutofBound khi di chuột ngoài sân chơi
                    if (c < Settings.MAP_COL && r < Settings.MAP_ROW) {
                        if (!MAP_SPRITES[r][c].equals("024")) {
                            //phủ màu đỏ
                            scene.setCursor(new ImageCursor(new Image("file:resources/img/imageedit_5_5449796414.png")));
                        } else {
                            scene.setCursor(new ImageCursor(Settings.SROCKET_IMG));
                        }
                    }
                });

                playfieldLayer.setOnMouseClicked(e2 -> {
                    double centerX = e2.getX() - ((int) e2.getX()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    double centerY = e2.getY() - ((int) e2.getY()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    int c = (int) (centerX / Settings.PIXS_PER_PIC);
                    int r = (int) (centerY / Settings.PIXS_PER_PIC);
                    //if ngoài để tránh IndexOutofBound khi di chuột ngoài sân chơi
                    if (c < Settings.MAP_COL && r < Settings.MAP_ROW) {
                        if (MAP_SPRITES[r][c].equals("024")) {
                            createWeapon(centerX, centerY, "SROCKET");
                            scene.setCursor(null);
                            playfieldLayer.getChildren().remove(range);
                            //setonmouseclicked to do nothing
                            playfieldLayer.setOnMouseClicked(e3 -> {
                            });
                            playfieldLayer.setOnMouseMoved(e6 -> {
                            });
                        }
                    }
                });
            }
        });

        missile.setOnMouseClicked(e -> {
            //bấm thử
            //singleCannon.setOpacity(Math.abs(singleCannon.getOpacity() - 1.2));
            if (money > Settings.MISSILE_COST && isRunning) {
                scene.setCursor(new ImageCursor(Settings.MISSILE_IMG));
                //hiện tầm đánh
                Circle range = new Circle();
                range.setRadius(Settings.MISSILE_ATKRANGE);
                range.setFill(null);
                playfieldLayer.getChildren().add(range);

                playfieldLayer.setOnMouseMoved(e3 -> {
                    range.setCenterX(e3.getX());
                    range.setCenterY(e3.getY());
                    range.setStroke(Color.BLUE);
                    double centerX = e3.getX() - ((int) e3.getX()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    double centerY = e3.getY() - ((int) e3.getY()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    int c = (int) (centerX / Settings.PIXS_PER_PIC);
                    int r = (int) (centerY / Settings.PIXS_PER_PIC);
                    //"024" là cỏ
                    //if ngoài để tránh IndexOutofBound khi di chuột ngoài sân chơi
                    if (c < Settings.MAP_COL && r < Settings.MAP_ROW) {
                        if (!MAP_SPRITES[r][c].equals("024")) {
                            //Scannon phủ màu đỏ
                            scene.setCursor(new ImageCursor(new Image("file:resources/img/imageedit_3_5130557468.png")));
                        } else {
                            scene.setCursor(new ImageCursor(Settings.MISSILE_IMG));
                        }
                    }
                });

                playfieldLayer.setOnMouseClicked(e2 -> {
                    double centerX = e2.getX() - ((int) e2.getX()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    double centerY = e2.getY() - ((int) e2.getY()) % (int) Settings.PIXS_PER_PIC + Settings.PIXS_PER_PIC / 2;
                    int c = (int) (centerX / Settings.PIXS_PER_PIC);
                    int r = (int) (centerY / Settings.PIXS_PER_PIC);
                    //if ngoài để tránh IndexOutofBound khi di chuột ngoài sân chơi
                    if (c < Settings.MAP_COL && r < Settings.MAP_ROW) {
                        if (MAP_SPRITES[r][c].equals("024")) {
                            createWeapon(centerX, centerY, "MISSILE");
                            scene.setCursor(null);
                            playfieldLayer.getChildren().remove(range);
                            //setonmouseclicked to do nothing
                            playfieldLayer.setOnMouseClicked(e3 -> {
                            });
                            playfieldLayer.setOnMouseMoved(e6 -> {
                            });
                        }
                    }
                });
            }
        });
    }

    private void createWeapon(double centerX, double centerY, String type) {
        switch (type) {
            case "SCANNON":
                SingleCannon newSCannon = new SingleCannon(playfieldLayer, Settings.SCANNON_IMG, centerX, centerY, 0, 0, 0, 0, Settings.SCANNON_DAMAGE, Settings.SCANNON_COST, Settings.SCANNON_ATKSPEED, Settings.SCANNON_ATKRANGE, Settings.SCANNON_BASE_IMG);
                weapons.add(newSCannon);
                money -= Settings.SCANNON_COST;
                break;
            case "DCANNON":
                DoubleCannon newDCannon = new DoubleCannon(playfieldLayer, Settings.DCANNON_IMG, centerX, centerY, 0, 0, 0, 0, Settings.DCANNON_DAMAGE, Settings.DCANNON_COST, Settings.DCANNON_ATKSPEED, Settings.DCANNON_ATKRANGE, Settings.DCANNON_BASE_IMG);
                weapons.add(newDCannon);
                money -= Settings.DCANNON_COST;
                break;
            case "DROCKET":
                DoubleRocket newDRocket = new DoubleRocket(playfieldLayer, Settings.DROCKET_IMG, centerX, centerY, 0, 0, 0, 0, Settings.DROCKET_DAMAGE, Settings.DROCKET_COST, Settings.DROCKET_ATKSPEED, Settings.DROCKET_ATKRANGE, Settings.DROCKET_BASE_IMG);
                weapons.add(newDRocket);
                money -= Settings.DROCKET_COST;
                break;
            case "SROCKET":
                SingleRocket newSRocket = new SingleRocket(playfieldLayer, Settings.SROCKET_IMG, centerX, centerY, 0, 0, 0, 0, Settings.SROCKET_DAMAGE, Settings.SROCKET_COST, Settings.SROCKET_ATKSPEED, Settings.SROCKET_ATKRANGE, Settings.SROCKET_BASE_IMG);
                weapons.add(newSRocket);
                money -= Settings.SROCKET_COST;
                break;
            case "MISSILE":
                Missile newMissile = new Missile(playfieldLayer, Settings.MISSILE_IMG, centerX, centerY, 0, 0, 0, 0, Settings.MISSILE_DAMAGE, Settings.MISSILE_COST, Settings.MISSILE_ATKSPEED, Settings.MISSILE_ATKRANGE, Settings.MISSILE_BASE_IMG);
                weapons.add(newMissile);
                money -= Settings.MISSILE_COST;
                break;
        }
    }

    private void spawnEnemies() {
        //đọc file level
//        try {
//            File x = new File("C:\\sololearn\\test.txt");
//            Scanner sc = new Scanner(x);
//            while(sc.hasNext()) {
//                System.out.println(sc.next());
//            }
//            sc.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("Error");
//        }

        //**//

        // for  abc giây, {đọc số tiếp: loại quái, abc, spawn loại quái đó},

//        Timeline timeline = new Timeline(new KeyFrame(
//                Duration.millis(2000),
//                ae -> {
//                    spawnEnemies();
//                }));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();

        int type = (int) (Math.random() * 3 + 1);
//        int type = 3;
        switch (type) {
            //type = 1 là smaller, 2 là normal, 3 là tanker.
            case 1:
                //Enemy abc = new Enemy(playfieldLayer, Settings.SMALLER_ENEMY_IMG, 64 * (int) (Math.random() * 10), 64 * (int) (Math.random() * 7),0,0,0,0, Settings.SMALLER_ENEMY_HEALTH,Settings.SMALLER_ENEMY_REWARD, Settings.SMALLER_ENEMY_SPEED);
                Enemy smallerEnemy = new SmallerEnemy(playfieldLayer, Settings.SMALLER_ENEMY_IMG, Settings.X_OF_STARTING_POINT, Settings.Y_OF_STARTING_POINT, -90, 0, 0, 0, Settings.SMALLER_ENEMY_HEALTH, Settings.SMALLER_ENEMY_REWARD, Settings.SMALLER_ENEMY_SPEED);
//        Enemy abc = new Enemy(playfieldLayer, Settings.SMALLER_ENEMY_IMG, 64 * 3, 64 * 3,90,0,0,0, Settings.SMALLER_ENEMY_HEALTH,Settings.SMALLER_ENEMY_REWARD, Settings.SMALLER_ENEMY_SPEED);
                enemies.add(smallerEnemy);
                break;
            case 2:
                Enemy normalEnemy = new NormalEnemy(playfieldLayer, Settings.NORMAL_ENEMY_IMG, Settings.X_OF_STARTING_POINT, Settings.Y_OF_STARTING_POINT, -90, 0, 0, 0, Settings.NORMAL_ENEMY_HEALTH, Settings.NORMAL_ENEMY_REWARD, Settings.NORMAL_ENEMY_SPEED);
                normalEnemy.getImageView().setFitWidth(96);
                normalEnemy.getImageView().setPreserveRatio(true);
                enemies.add(normalEnemy);
                break;
            case 3:
                Enemy tankerEnemy = new TankerEnemy(playfieldLayer, Settings.TANKER_ENEMY_IMG, Settings.X_OF_STARTING_POINT, Settings.Y_OF_STARTING_POINT, -90, 0, 0, 0, Settings.TANKER_ENEMY_HEALTH, Settings.TANKER_ENEMY_REWARD, Settings.TANKER_ENEMY_SPEED);
                enemies.add(tankerEnemy);
                break;
        }
    }

    //https://stackoverflow.com/questions/17279519/removing-items-from-a-list
    private void removeEntities(List<? extends EntityBase> entities) {
        Iterator<? extends EntityBase> it = entities.iterator();
        while (it.hasNext()) {
            EntityBase entity = it.next();
            if (entity.isRemovable()) {
                //remove display
                entity.removeFromLayer();
                //remove from list
                it.remove();
            }
        }
    }

    private void resetGame() {
        weapons.clear();
        enemies.clear();
        bullets.clear();
        level = 1;
        money = Settings.STARTING_MONEY;
        lives = Settings.STARTING_LIVES;
        playfieldLayer.getChildren().clear();
//        infoLayer.getChildren().clear();
    }

    private void askToPlayAgain() {
        Alert gameOver = new Alert(AlertType.CONFIRMATION);
        gameOver.setTitle("Game over!");
        gameOver.setHeaderText(null);
        gameOver.setContentText("Do you want to play again ?");
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        // Loại bỏ các ButtonType mặc định
        gameOver.getButtonTypes().clear();
        gameOver.getButtonTypes().addAll(yes, no);
        Optional<ButtonType> option = gameOver.showAndWait();
        if (option.get() == null || option.get() == no) {
            isPlayAgain = false;
            System.exit(0);
        }
        if (option.get() == yes) {
            isPlayAgain = true;
            resetGame();
            gameLoop.start();
//            introAgain();
        }
    }

    private void introAgain() {
        ImageView imageView = new ImageView(new Image("file:resources/img/Sample.png"));
        Button play = new Button();
        play.setGraphic(new ImageView(new Image("file:resources/img/imageedit_7_2021092016.png")));
        play.setLayoutX(320);
        play.setLayoutY(330);
        backgroundLayer.getChildren().addAll(imageView, play);
        stage.sizeToScene();
        //setonaction thi nhấn enter hoặc spacebar được chứ ko click được
        //seton mouse CLicked ko click được
        play.setOnAction(e9 -> {
            backgroundLayer.getChildren().removeAll(imageView, play);
//            infoLayer.setVisible(false);
            createBackgroundLayer();
            createInfoLayer();
            stage.sizeToScene();
            gameLoop.start();
        });
    }


    @Override
    public void start(Stage stage) throws Exception {


        backgroundLayer = new Pane();
        root.getChildren().add(backgroundLayer);
        infoLayer = new GridPane();
        root.getChildren().add(infoLayer);
        playfieldLayer = new Pane();
        root.getChildren().add(playfieldLayer);
        scene.setFill(Color.web("#b69b15"));
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Tower Defense");
        stage.getIcons().add(new Image("file:resources/img/0042_083_favorite_star_rate-256.png"));
        stage.show();

        do {
//           first intro
            ImageView imageView = new ImageView(new Image("file:resources/img/Sample.png"));
            Button play = new Button();
            play.setGraphic(new ImageView(new Image("file:resources/img/imageedit_7_2021092016.png")));
            play.setLayoutX(320);
            play.setLayoutY(330);
            backgroundLayer.getChildren().addAll(imageView, play);
            stage.sizeToScene();
            AudioClip ac = new AudioClip(new File("resources/audio/opening.mp3").toURI().toString());
            ac.play();
            ac.setCycleCount(AudioClip.INDEFINITE);
            play.setOnMouseClicked(e -> {
                ac.stop();
                backgroundLayer.getChildren().removeAll(imageView, play);
                createBackgroundLayer();
                createInfoLayer();
                stage.sizeToScene();
                playfieldLayer.setPrefSize(Settings.PIXS_PER_PIC * 10, Settings.PIXS_PER_PIC * 7);
                //nhạc nhẽo
//                Media media = new Media(new File("resources/audio/opening.mp3").toURI().toString());
//                MediaPlayer mediaPlayer = new MediaPlayer(media);
//                mediaPlayer.play();
////              mediaPlayer.setVolume(2);
//                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//                mediaPlayer.setAutoPlay(true);
                AudioClip ac2 = new AudioClip(new File("resources/audio/inGame.mp3").toURI().toString());
                ac2.play();
                ac2.setCycleCount(AudioClip.INDEFINITE);

//        spawnEnemies();

//        //kẻ sọc
//        Line l1 = new Line();
//        List<Line> l = new ArrayList<>();
//        double colStartY = 0;
//        double colEndY = 64.0 * 7;
//        double rowStartX = 0;
//        double rowEndX = 64.0 * 10;
//
//        for (int row = 0; row < 6; row++){
//            Line line = new Line();
//            line.setStartX(rowStartX);
//            line.setStartY(64.0 * (row + 1));
//            line.setEndX(rowEndX);
//            line.setEndY(64.0 * (row + 1));
//            l.add(line);
//        }
//        for (int col = 0; col < 9; col++){
//            Line line = new Line();
//            line.setStartY(colStartY);
//            line.setStartX(Settings.PIXS_PER_PIC * (col + 1));
//            line.setEndY(colEndY);
//            line.setEndX(Settings.PIXS_PER_PIC * (col + 1));
//            l.add(line);
//        }
//        for (Line line : l){
//            root.getChildren().add(line);
//        };

//        //nhạc nhẽo
//        Media media = new Media(new File("resources/abc.mp3").toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();
//        mediaPlayer.setAutoPlay(true);


//        Timeline timeline2 = new Timeline(new KeyFrame(
//                Duration.millis(1000 / 60),
//                ae -> {
//                    enemies.forEach(enemy ->{
//                        enemy.move();
//                        if (Double.compare(enemy.getX(), Settings.PIXS_PER_PIC * Settings.MAP_COL) > 0 || Double.compare(enemy.getY(), Settings.PIXS_PER_PIC * Settings.MAP_ROW) > 0){
//                            enemy.setRemovable(true);
//                        }
//                    });
//                }));
//        timeline2.setCycleCount(Animation.INDEFINITE);
//        timeline2.play();

                gameLoop = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
//              another idea for periodically run a function in animationTimer
//              1 second animationTimer runs 60 times (60 FPS)
                        timeCount++;
                        if (timeCount > 120) timeCount = 0;
                        if (timeCount == 120) {
                            spawnEnemies();
                        }

                        weapons.forEach(weapon -> {
                            if (isSelling) {
                                weapon.getImageView().setOnMouseClicked(e -> {
                                    weapon.setRemovable(true);
                                    //bán thì nhận được 1/2 giá mua.
                                    money += weapon.getCost() / 2;
                                });
                            }
                            if (isUpgrading) {
                                if (weapon instanceof SingleCannon && money > Settings.SCANNON_UPGRADE_COST) {
                                    weapon.getImageView().setEffect(colorAdjust);
                                    weapon.getImageView().setOnMouseClicked(e -> {
                                        createWeapon(weapon.getX(), weapon.getY(), "DCANNON");
                                        weapon.setRemovable(true);
                                    });
                                } else {
                                    if (weapon instanceof SingleRocket && money > Settings.SROCKET_UPGRADE_COST) {
                                        weapon.getImageView().setEffect(colorAdjust);
                                        weapon.getImageView().setOnMouseClicked(e -> {
                                            createWeapon(weapon.getX(), weapon.getY(), "DROCKET");
                                            weapon.setRemovable(true);
                                        });
                                    }
                                }
                            } else {
                                weapon.getImageView().setEffect(null);
                            }
                            if (weapon.getTarget() == null) {
                                weapon.findTarget(enemies);
                            } else {
                                weapon.checkTarget();
                                //fire();
                                if (weapon.shoot()) {
                                    Bullet newBullet = null;
                                    if (weapon instanceof SingleCannon) {
                                        newBullet = new Bullet(playfieldLayer, Settings.SCANNON_BULLET_IMG, weapon.getX(), weapon.getY(), weapon.getR(), 0, 0, 0, Settings.SCANNON_BULLET_EXPLODE_IMG, Settings.SCANNON_BULLET_VELOCITY);
                                    }
                                    if (weapon instanceof DoubleCannon) {
                                        newBullet = new Bullet(playfieldLayer, Settings.DCANNON_BULLET_IMG, weapon.getX(), weapon.getY(), weapon.getR(), 0, 0, 0, Settings.DCANNON_BULLET_EXPLODE_IMG, Settings.DCANNON_BULLET_VELOCITY);
                                    }
                                    if (weapon instanceof SingleRocket) {
                                        newBullet = new Bullet(playfieldLayer, Settings.SROCKET_BULLET_IMG, weapon.getX(), weapon.getY(), weapon.getR(), 0, 0, 0, Settings.SROCKET_BULLET_EXPLODE_IMG, Settings.SROCKET_BULLET_VELOCITY);
                                    }
                                    if (weapon instanceof DoubleRocket) {
                                        newBullet = new Bullet(playfieldLayer, Settings.DROCKET_BULLET_IMG, weapon.getX(), weapon.getY(), weapon.getR(), 0, 0, 0, Settings.DROCKET_BULLET_EXPLODE_IMG, Settings.DROCKET_BULLET_VELOCITY);
                                    }
                                    if (weapon instanceof Missile) {
                                        newBullet = new Bullet(playfieldLayer, Settings.MISSILE_BULLET_IMG, weapon.getX(), weapon.getY(), weapon.getR(), 0, 0, 0, Settings.MISSILE_BULLET_EXPLODE_IMG, Settings.MISSILE_BULLET_VELOCITY);
                                    }
                                    newBullet.setTarget(weapon.getTarget());
                                    newBullet.setDamage(weapon.getDamage());
                                    bullets.add(newBullet);
                                    //nhạc nhẽo
                                    AudioClip ac = new AudioClip(new File("resources/audio/414886__mattix__retro-laser-shot-02.wav").toURI().toString());
                                    ac.setVolume(0.15);
                                    ac.play();
                                }
                            }
                            weapon.move();
                        });

                        enemies.forEach(enemy -> {
                            enemy.move();
                            if (Double.compare(enemy.getX(), Settings.PIXS_PER_PIC * Settings.MAP_COL) > 0 || Double.compare(enemy.getY(), Settings.PIXS_PER_PIC * Settings.MAP_ROW) > 0) {
                                enemy.setRemovable(true);
                                lives--;
                            }
                            if (enemy.getHealth() <= 0) {
                                enemy.setRemovable(true);
                                money += enemy.getReward();
                            }
                            enemy.updateHealthBar();
                        });

                        bullets.forEach(bullet -> {
                            bullet.move();
                            //if này tránh NullPointerException
                            if (bullet.getTarget() != null) {
                                //khi đạn trong bán kính 1 pixel từ tâm địch thì coi như bắn trúng
                                if ((bullet.getX() - bullet.getTarget().getX()) < 1 && (bullet.getY() - bullet.getTarget().getY()) < 1) {
                                    bullet.getTarget().setHealth(bullet.getTarget().getHealth() - bullet.getDamage());
                                    bullet.setRemovable(true);
                                }
                            }
                            if (bullet.getX() > Settings.PIXS_PER_PIC * Settings.MAP_COL || bullet.getY() > Settings.PIXS_PER_PIC * Settings.MAP_ROW) {
                                bullet.setRemovable(true);
                            }
                        });

                        removeEntities(enemies);
                        removeEntities(weapons);
                        removeEntities(bullets);
                        updateInfoLayer();
                        if (lives <= 0) {
                            gameLoop.stop();
                            Platform.runLater(Game.this::askToPlayAgain);
                        }
                    }
                };
                gameLoop.start();
            });
        } while (isPlayAgain);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
