package handler.events;

import handler.Handler;
import handler.entities.Entity;
import handler.entities.EntityManager;
import handler.items.ItemManager;

public class EntityEvent extends EventManager{

    private EntityManager manager;
    private Entity entity;

    private int num;

    public EntityEvent(Handler handler, EntityManager manager, Entity entity, int num)
    {
        super(handler);
        this.manager = manager;
        this.entity = entity;
        this.num = num;
    }

    @Override
    public void playEvent()
    {
//        System.out.println(entity.toString() + " added");
        for (int i = 0; i < num + 1; i++) {

            manager.activelyAddEntity(entity);
//            System.out.println(entity.toString() + " added");
        }
        handler.getControllerManager().vibrate();
    }
}
