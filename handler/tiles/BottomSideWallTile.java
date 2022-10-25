package handler.tiles;

import handler.gfx.Assets;

public class BottomSideWallTile extends Tile{

    public BottomSideWallTile (int id)
    {
        super(Assets.bottomSideWall, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
