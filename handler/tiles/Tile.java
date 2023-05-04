package handler.tiles;

import handler.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public static Handler handler;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static Tile[] tiles = new Tile[256];
    public static Tile carpetTile = new CarpetTile(0);
    public static Tile topWallTile = new TopWallTile(1);
    public static Tile emptyTile = new EmptyTile(2);
    public static Tile leftSideWallTile = new LeftSideWallTile(3);
    public static Tile rightSideWallTile = new RightSideWallTile(4);
    public static Tile bottomSideWallTile = new BottomSideWallTile(5);
    public static Tile bottomWallTile = new BottomWallTile(6);
    public static Tile rightTopDoor = new TopDoorTile(7);
    public static Tile rightBottomDoor = new BottomDoorTile(8);

    protected BufferedImage texture;
    protected final int id;

    public static int TILEWIDTH = screenSize.width / 10, TILEHEIGHT = TILEWIDTH;

    public Tile(BufferedImage texture, int id)
    {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick()
    {

    }

    public void render(Graphics g, int x, int y)
    {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid()
    {
        return false;
    }

    public int getId()
    {
        return id;
    }

    public static int getTILEWIDTH() {
        return TILEWIDTH;
    }

    public static void setTILEWIDTH(int TILEWIDTH) {
        Tile.TILEWIDTH = TILEWIDTH;
    }
}
