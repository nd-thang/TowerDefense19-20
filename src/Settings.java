import javafx.scene.image.Image;

public class Settings {
    public static double STARTING_MONEY = 100;
    public static int STARTING_LIVES = 9;

    public static double PIXS_PER_PIC = 64;
    public static double MAP_ROW = 7;
    public static double MAP_COL = 10;

    public static double X_OF_STARTING_POINT = PIXS_PER_PIC * 1;
    public static double Y_OF_STARTING_POINT = PIXS_PER_PIC * 7;
    public static double X_OF_ENDING_POINT = PIXS_PER_PIC * 10;
    public static double Y_OF_ENDING_POINT = PIXS_PER_PIC * 5;

    public static Image SCANNON_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile249" + ".png");
    public static Image SCANNON_BASE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile180" + ".png");
    public static Image SCANNON_BULLET_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile272" + ".png");
    public static Image SCANNON_BULLET_EXPLODE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile295" + ".png");

    public static Image DCANNON_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile250" + ".png");
    public static Image DCANNON_BULLET_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile273" + ".png");
    public static Image DCANNON_BASE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile181" + ".png");
    public static Image DCANNON_BULLET_EXPLODE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile296" + ".png");

    public static Image DROCKET_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile205" + ".png");
    public static Image DROCKET_BULLET_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile251" + ".png");
    public static Image DROCKET_BASE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile182" + ".png");
    public static Image DROCKET_BULLET_EXPLODE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile297" + ".png");

    public static Image SROCKET_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile206" + ".png");
    public static Image SROCKET_BASE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile183" + ".png");
    public static Image SROCKET_BULLET_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile252" + ".png");
    public static Image SROCKET_BULLET_EXPLODE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile298" + ".png");

    public static Image MISSILE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile204" + ".png");
    public static Image MISSILE_BASE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile181" + ".png");
    public static Image MISSILE_BULLET_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile251" + ".png");
    public static Image MISSILE_BULLET_EXPLODE_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile297" + ".png");

    public static double SCANNON_DAMAGE = 2;
    public static double DCANNON_DAMAGE = 6;
    public static double SROCKET_DAMAGE = 8;
    public static double DROCKET_DAMAGE = 12;
    public static double MISSILE_DAMAGE = 15;

    //tốc bắn trên giây
    public static double SCANNON_ATKSPEED = 1.3;
    public static double DCANNON_ATKSPEED = 1.9;
    public static double SROCKET_ATKSPEED = 1.1;
    public static double DROCKET_ATKSPEED = 1.6;
    public static double MISSILE_ATKSPEED = 1;

    public static double SCANNON_ATKRANGE = 130;
    public static double DCANNON_ATKRANGE = 200;
    public static double SROCKET_ATKRANGE = 200;
    public static double DROCKET_ATKRANGE = 220;
    public static double MISSILE_ATKRANGE = 250;

    public static double SCANNON_BULLET_VELOCITY = 3;
    public static double DCANNON_BULLET_VELOCITY = 4;
    public static double SROCKET_BULLET_VELOCITY = 3;
    public static double DROCKET_BULLET_VELOCITY = 4;
    public static double MISSILE_BULLET_VELOCITY = 5;

    public static double SCANNON_COST = 5;
    public static double DCANNON_COST = 1;
    public static double SROCKET_COST = 15;
    public static double DROCKET_COST = 1;
    public static double MISSILE_COST = 25;

    public static double SCANNON_UPGRADE_COST = 8;
    public static double SROCKET_UPGRADE_COST = 20;

    public static Image SMALLER_ENEMY_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Default size/towerDefense_tile245" + ".png");
    public static Image NORMAL_ENEMY_IMG = new Image("file:resources/img/imageedit_1_7446007812.png");
    public static Image TANKER_ENEMY_IMG = new Image("file:resources/img/AssetsKit_2/PNG/Retina/towerDefense_tile247" + ".png");

    public static double SMALLER_ENEMY_HEALTH = 12;
    public static double NORMAL_ENEMY_HEALTH = 25;
    public static double TANKER_ENEMY_HEALTH = 60;

    public static double SMALLER_ENEMY_REWARD = 3;
    public static double NORMAL_ENEMY_REWARD = 8;
    public static double TANKER_ENEMY_REWARD = 20;

    public static double SMALLER_ENEMY_SPEED = 1.4;
    public static double NORMAL_ENEMY_SPEED = 1.1;
    public static double TANKER_ENEMY_SPEED = 0.8;


}
