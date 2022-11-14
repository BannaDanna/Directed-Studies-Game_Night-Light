package handler.inventory;

import handler.Handler;
import handler.gfx.Assets;
import handler.gfx.Text;
import handler.items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.util.ArrayList;

public class Inventory {

    private int invX = 0, invY = 0, invWidth = 640, invHeight = 360, invListCenterX = (int) invX + (invWidth / 2) - 100, invListCenterY = (int) invY + (invHeight / 2), invListSpacing = 50;

    private int invImageX = 490, invImageY = 50,
            invImageWidth = 100, invImageHeight = 100;

    private int invCountX = 545, invCountY = 210;


    private int selectedItem = 0;

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;

    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();

    }

    public void tick() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_I))
            active = !active;
        if (!active) {
            return;
        }


        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) {
            selectedItem--;
        }
        if (handler.getKeyManager().keyJustPressed((KeyEvent.VK_DOWN))) {
            selectedItem++;
        }
        if (selectedItem < 0) {
            selectedItem = inventoryItems.size() - 1;
        } else if (selectedItem >= inventoryItems.size())
        {
            selectedItem = 0;
        }
    }

    public void render(Graphics g)
    {
        if(!active)
        {
            return;
        }

        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

        int len = inventoryItems.size();
        if(len == 0)
        {
            return;
        }

        for(int i = -5; i < 6; i++)
        {
            if(selectedItem + i < 0 || selectedItem + i >= len)
                continue;
            if(i == 0)
            {
                Text.drawString(g,"> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, (invListCenterY + i * invListSpacing), true, Color.YELLOW, Assets.font28);
            } else {
                Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, (invListCenterY + i * invListSpacing), true, Color.WHITE, Assets.font28);
            }
        }

        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
        Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.BLACK, Assets.font28);
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

    public boolean isActive() {
        return active;
    }
}
