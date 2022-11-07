package handler.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 256, height = width;
    //tiles
    public static BufferedImage empty, wall, carpet, leftSideWall, rightSideWall, bottomSideWall, bottomWall;

    //static entities
    public static BufferedImage lamp, couch;

    //player animations
    public static BufferedImage[] player_idle;
    //UI
    public static BufferedImage[] btn_start;
    //items
    public static BufferedImage batteries;
    //inventory
    public static BufferedImage inventoryScreen;

    public static void init()
    {
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
        batteries = sheet.crop(width * 5, height * 5, width, height);
    }
}
