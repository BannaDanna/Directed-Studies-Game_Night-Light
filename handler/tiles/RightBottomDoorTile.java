package handler.tiles;

import handler.gfx.Assets;

public class RightBottomDoorTile extends Tile{
    public RightBottomDoorTile(int id) {
        super(Assets.rightDoor, id);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
