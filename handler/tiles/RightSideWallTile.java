package handler.tiles;

import handler.gfx.Assets;

public class RightSideWallTile extends Tile{

    public RightSideWallTile(int id)
    {
        super(Assets.rightSideWall, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
