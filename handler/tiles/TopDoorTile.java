package handler.tiles;

import handler.gfx.Assets;

public class TopDoorTile extends Tile{
    public TopDoorTile(int id) {
        super(Assets.rightDoor, id);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
