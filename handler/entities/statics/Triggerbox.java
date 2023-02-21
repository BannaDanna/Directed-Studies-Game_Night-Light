package handler.entities.statics;

import handler.Handler;
import handler.tiles.Tile;

import java.awt.*;

public class Triggerbox extends StaticEntity{
    public Triggerbox(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = (int) (handler.getWidth() * 0.0988);
        bounds.height = bounds.width;
        killable = false;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void die() {

    }
}
