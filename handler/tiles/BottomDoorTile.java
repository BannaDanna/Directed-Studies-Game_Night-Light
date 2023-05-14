package handler.tiles;

import handler.gfx.Assets;

public class BottomDoorTile extends Tile{
    public BottomDoorTile(int id) {
        super(Assets.rightDoor, id);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
