package handler.inventory;

import handler.Handler;
import handler.gfx.Assets;
import handler.items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.util.ArrayList;

public class Inventory {

    private int invX = 0, invY = 0, invWidth = 640, invHeight = 360, invListCenterX = (int) invX + (invWidth / 2), invListCenterY = (int) invY + (invHeight / 2);

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;

    public Inventory(Handler handler)
    {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();
    }

    public void tick()
    {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I))
            active = !active;
        if(!active)
        {
            return;
        }

        System.out.println("inventory: ");
        for(Item i : inventoryItems)
        {
            System.out.println(i.getName() + " " + i.getCount());
        }
    }

    public void render(Graphics g)
    {
        if(!active)
        {
            return;
        }

        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);
    }

    //inventory methods

    public void addItem(Item item){
        for(Item i : inventoryItems)
        {
            if(i.getId() == item.getId())
            {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    //Getters and Setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
