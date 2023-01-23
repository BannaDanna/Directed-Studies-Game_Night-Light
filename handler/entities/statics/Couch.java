package handler.entities.statics;

import handler.Handler;
import handler.gfx.Assets;
import handler.tiles.Tile;

import java.awt.*;

public class Couch extends StaticEntity{

    public Couch(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 192;
        bounds.height = 192;
        killable = false;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
    g.drawImage(Assets.couch, (int) (x- handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }

    @Override
    public void die() {

    }

}
