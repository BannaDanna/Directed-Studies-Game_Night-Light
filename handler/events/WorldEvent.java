package handler.events;

import handler.Handler;
import handler.worlds.World;

public class WorldEvent extends EventManager{
    public World world;



    public WorldEvent(Handler handler, World world) {
        super(handler);
        this.world = world;
    }

    @Override
    public void playEvent()
    {
        handler.setWorld(world);
    }
}
