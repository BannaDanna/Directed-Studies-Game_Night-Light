package handler.entities.statics;

import handler.Handler;
import handler.sounds.SoundManager;

import java.awt.*;

public class AudioTriggerBox extends StaticEntity{

    private SoundManager audiomanager;
    private String path;
    public AudioTriggerBox(Handler handler, float x, float y, int width, int height, String filepath) {
        super(handler, x, y, width, height);
        audiomanager = new SoundManager();
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
        killable = false;
        solid = false;
        path = filepath;
    }

    @Override
    public void tick() {
        if(checkEntityCollisions(0,0))
        {
            audiomanager.playSound(path);
            active = false;
        }
    }

    @Override
    public void render(Graphics g) {
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }

    @Override
    public void die() {

    }


}
