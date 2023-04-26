package handler.tiles;

import handler.gfx.Assets;

public class RightTopDoorTile extends Tile{
    public RightTopDoorTile(int id) {
        super(Assets.rightDoor, id);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
