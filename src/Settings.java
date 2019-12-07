import javafx.scene.image.Image;

public class Settings {

    public static double PIXS_PER_PIC = 64;
    public static double MAP_ROW = 7;
    public static double MAP_COL = 10;

    public static double X_OF_STARTING_POINT = PIXS_PER_PIC * 1;
    public static double Y_OF_STARTING_POINT = PIXS_PER_PIC * 7;
    public static double X_OF_ENDING_POINT = PIXS_PER_PIC * 10;
    public static double Y_OF_ENDING_POINT = PIXS_PER_PIC * 5;

    public static Image SCANNON_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile249" + ".png");
    public static Image SCANNON_BASE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile180" + ".png");

    public static Image SROCKET_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile206" + ".png");
    public static Image SROCKET_BASE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile183" + ".png");

    public static Image MISSILE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile204" + ".png");
    public static Image MISSILE_BASE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile181" + ".png");

    public static double SCANNON_DAMAGE = 1;
    public static double DCANNON_DAMAGE = 1;
    public static double SROCKET_DAMAGE = 1;
    public static double DROCKET_DAMAGE = 1;
    public static double MISSILE_DAMAGE = 1;

    public static double SCANNON_ATKSPEED = 1;
    public static double DCANNON_ATKSPEED = 1;
    public static double SROCKET_ATKSPEED = 1;
    public static double DROCKET_ATKSPEED = 1;
    public static double MISSILE_ATKSPEED = 1;

    public static double SCANNON_ATKRANGE = 150;
    public static double DCANNON_ATKRANGE = 100;
    public static double SROCKET_ATKRANGE = 100;
    public static double DROCKET_ATKRANGE = 100;
    public static double MISSILE_ATKRANGE = 100;

    public static double SCANNON_COST = 1;
    public static double DCANNON_COST = 1;
    public static double SROCKET_COST = 1;
    public static double DROCKET_COST = 1;
    public static double MISSILE_COST = 1;

    public static Image SMALLER_ENEMY_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile245" + ".png");
    public static Image NORMAL_ENEMY_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile246" + ".png");
    public static Image TANKER_ENEMY_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile247" + ".png");

    public static double SMALLER_ENEMY_HEALTH = 10;
    public static double NORMAL_ENEMY_HEALTH = 10;
    public static double TANKER_ENEMY_HEALTH = 10;

    public static double SMALLER_ENEMY_REWARD = 10;
    public static double NORMAL_ENEMY_REWARD = 10;
    public static double TANKER_ENEMY_REWARD = 10;

    public static double SMALLER_ENEMY_SPEED = 1;
    public static double NORMAL_ENEMY_SPEED = 10;
    public static double TANKER_ENEMY_SPEED = 10;



}
