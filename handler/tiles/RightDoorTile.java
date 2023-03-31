package handler.tiles;

import handler.gfx.Assets;

import java.awt.image.BufferedImage;

public class RightDoorTile extends Tile{
    public RightDoorTile(int id) {
        super(Assets.rightDoor, id);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
