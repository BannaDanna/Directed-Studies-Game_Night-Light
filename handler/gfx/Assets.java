package handler.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Assets {

    private static final int width = 256, height = width;
    //tiles
    public static BufferedImage empty, wall, carpet, leftSideWall, rightSideWall, bottomSideWall, bottomWall;

    //static entities
    public static BufferedImage lamp, couch;

    //player animations
    public static BufferedImage[] player_idle, player_attack_left, player_attack_right, player_attack_up, player_attack_down, player_attack_move_left, player_attack_move_down, player_attack_move_up, player_attack_move_right;
    //UI
    public static BufferedImage[] btn_start;
    //items
    public static BufferedImage batteries;
    //inventory
    public static BufferedImage inventoryScreen;
    //fonts
    public static Font font28, font56, font14;


    public static void init()
    {
        font28 = FontLoader.loadFont("res/fonts/Pixellettersfull-BnJ5.ttf", 28);
        font14 = FontLoader.loadFont("res/fonts/Pixellettersfull-BnJ5.ttf", 14);
        font56 = FontLoader.loadFont("res/fonts/Pixellettersfull-BnJ5.ttf", 56);

        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/squareSheet.png"));

        //inventory
        inventoryScreen = ImageLoader.LoadImage("/textures/inventoryScreen.png");

        //UI
        btn_start = new BufferedImage[2];
        btn_start[0] = sheet.crop(width * 4, 0, width * 2, height);
        btn_start[1] = sheet.crop(width * 6, 0, width * 2, height);

        //player animations
        player_idle = new BufferedImage[2];

        player_idle[0] = sheet.crop(0,0,width,height *2);
        player_idle[1] = sheet.crop(width, 0, width, height * 2);

        player_attack_right = new BufferedImage[6];

        player_attack_right[0] = sheet.crop(0, 0, width, height * 2);
        player_attack_right[1] = sheet.crop(width, 0, width, height * 2);
        player_attack_right[2] = sheet.crop(width * 2, 0, width, height * 2);
        player_attack_right[3] = sheet.crop(width * 3, 0, width, height * 2);
        player_attack_right[4] = sheet.crop(width * 4, 0, width, height * 2);
        player_attack_right[5] = sheet.crop(width * 5, 0, width, height * 2);

        player_attack_left = new BufferedImage[6];

        player_attack_left[0] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_left[1] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_left[2] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_left[3] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_left[4] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_left[5] = sheet.crop(width * 4, height * 5, width, height * 2);

        player_attack_up = new BufferedImage[6];

        player_attack_up[0] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_up[1] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_up[2] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_up[3] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_up[4] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_up[5] = sheet.crop(width * 4, height * 5, width, height * 2);

        player_attack_down = new BufferedImage[6];

        player_attack_down[0] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_down[1] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_down[2] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_down[3] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_down[4] = sheet.crop(width * 4, height * 5, width, height * 2);
        player_attack_down[5] = sheet.crop(width * 4, height * 5, width, height * 2);

        player_attack_move_right = new BufferedImage[6];

        player_attack_move_right[0] = sheet.crop(0, 0, width, height * 2);
        player_attack_move_right[1] = sheet.crop(width, 0, width, height * 2);
        player_attack_move_right[2] = sheet.crop(width * 2, 0, width, height * 2);
        player_attack_right[3] = sheet.crop(width * 3, 0, width, height * 2);
        player_attack_right[4] = sheet.crop(width * 4, 0, width, height * 2);
        player_attack_right[5] = sheet.crop(width * 5, 0, width, height * 2);

        //tiles
        empty = sheet.crop(width*9, height*9, width, height);
        wall = sheet.crop((width * 2), 0, width, height);
        carpet = sheet.crop((width * 3), 0 , width, height);
        leftSideWall = sheet.crop(width * 8, 0, width, height);
        rightSideWall = sheet.crop(width * 3, height, width, height);
        bottomSideWall = sheet.crop(width * 4, height, width, height);
        bottomWall = sheet.crop(width * 2, height, width, height);

        //static entities
        lamp = sheet.crop(width * 9, 0, width, height * 2);
        couch = sheet.crop(width * 6, height, width, height);

        //items
        batteries = sheet.crop(width * 5, height, width, height);
    }
}
