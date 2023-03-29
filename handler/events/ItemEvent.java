package handler.events;

import handler.Handler;
import handler.items.Item;
import handler.items.ItemManager;

public class ItemEvent extends EventManager{

    public ItemManager manager;
    public Item item;
    public int num;

    public ItemEvent(Handler handler, ItemManager manager, Item item, int num) {
        super(handler);
        this.manager = manager;
        this.item = item;
        this.num = num;
    }

    @Override
    public void playEvent()
    {
        for (int i = 0; i < num + 1; i++) {
            manager.addItem(item);
        }
    }
}
