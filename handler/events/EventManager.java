package handler.events;

import handler.Handler;
import handler.entities.Entity;
import handler.entities.EntityManager;
import handler.items.Item;
import handler.items.ItemManager;

public abstract class EventManager {

    private Handler handler;

    private EntityManager entityManager;

    private ItemManager itemManager;

    public EventManager(Handler handler)
    {
        this.handler = handler;

    }

    public void playEvent()
    {

    }
}
