package handler.tiles;

import handler.gfx.Assets;

public class LeftSideWallTile extends Tile{

    public LeftSideWallTile(int id)
    {
        super(Assets.leftSideWall, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
