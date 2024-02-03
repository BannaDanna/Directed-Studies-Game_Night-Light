package handler.entities.statics;

import handler.Handler;
import handler.entities.EntityManager;
import handler.events.EntityEvent;
import handler.events.EventManager;
import handler.events.ItemEvent;
import handler.events.WorldEvent;
import handler.items.ItemManager;
import handler.sounds.SoundManager;

import java.awt.*;

public class GameTriggerBox extends StaticEntity{

    private SoundManager audiomanager;
    private String path;

    private EventManager event;

    public GameTriggerBox(Handler handler, float x, float y, int width, int height, String filepath) {
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

    public GameTriggerBox(Handler handler, float x, float y, int width, int height, EntityEvent event) {
        super(handler, x, y, width, height);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
        killable = false;
        solid = false;
        this.event = event;
//        System.out.println("Entity event created");
    }

    public GameTriggerBox(Handler handler, float x, float y, int width, int height, ItemEvent event) {
        super(handler, x, y, width, height);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
        killable = false;
        solid = false;
        this.event = event;
//        System.out.println("Item event created");
    }

    public GameTriggerBox(Handler handler, float x, float y, int width, int height, WorldEvent event) {
        super(handler, x, y, width, height);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
        killable = false;
        solid = false;
        this.event = event;
//        System.out.println("Item event created");
    }

    @Override
    public void tick() {
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(getCollisionBounds(0,0)))
        {
            if(path != null)
            {
                System.out.println("Playing Sound");
                audiomanager.playSound(path, -15.0f);
            } else {
                System.out.println("Playing Event");
                event.playEvent();
            }
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
