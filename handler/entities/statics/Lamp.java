package handler.entities.statics;

import handler.Handler;
import handler.gfx.Assets;
import handler.items.Item;
import handler.tiles.Tile;

import java.awt.*;

public class Lamp extends StaticEntity{

    public Lamp(Handler handler, float x, float y)
    {
        super(handler, x, y,Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);
        bounds.x = (int)(handler.getWidth() * 0.02);
        bounds.y = (int) (handler.getHeight() * 0.27);
        bounds.width = (int)(handler.getWidth() * 0.06);
        bounds.height = (int) (handler.getHeight() * 0.0859);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.lamp,(int) (x- handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
//        g.setColor(Color.red);
//        g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);

    }

    @Override
    public void die()
    {
        System.out.println("Batteries dropped");
        handler.getWorld().getItemManager().addItem(Item.batteriesItem.createNew((int) (x + bounds.width / 2), (int) (y + bounds.y + bounds.height)));
    }
}
