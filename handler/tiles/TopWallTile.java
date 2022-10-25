package handler.tiles;

import handler.gfx.Assets;

public class TopWallTile extends Tile{

    public TopWallTile(int id)
    {
        super(Assets.wall, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }

}
