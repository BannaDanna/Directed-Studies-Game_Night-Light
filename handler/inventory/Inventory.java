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

    private int invX = 0, invY = 0, invWidth, invHeight, invListCenterX, invListCenterY, invListSpacing;

    private int invImageX, invImageY,
            invImageWidth, invImageHeight;

    private int invCountX, invCountY;

    private int invDescX, invDescY;


    private int selectedItem = 0;

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;
    private long inventoryTimer , lastInventoryTimer, inventoryCooldown = 300;
    private long listTimer, lastListTimer, listCooldown = 200;

    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();
        invWidth = handler.getWidth();
        invHeight = handler.getHeight();
        invListCenterX = (int) invX + (invWidth / 2) - 200;
        invListCenterY = (int) invY + (invHeight / 2);
        invListSpacing = (int)(handler.getWidth() / 10);
        invImageX = (int) (handler.getWidth() / 1.315);
        invImageY = (int) (handler.getHeight() / 7.2);
        invImageWidth = (int) (handler.getWidth() / 6);
        invImageHeight = invImageWidth;
        invCountX = (int) (handler.getWidth() / 1.1777);
        invCountY = (int) (handler.getHeight() / 1.7143);
        invDescX = (int) (handler.getWidth() / 1.1777);
        invDescY = (int) (handler.getHeight() / 1.5);
    }

    public void tick() {
//        inventoryTimer += System.currentTimeMillis() - lastInventoryTimer;
//        lastInventoryTimer = System.currentTimeMillis();


        if (((handler.getKeyManager().keyJustPressed(KeyEvent.VK_I) || handler.getControllerManager().options)/* && inventoryTimer >= inventoryCooldown*/) && !handler.getWorld().getEntityManager().getPlayer().getPauseMenu().isActive())
        {
            active = !active;
//            inventoryTimer = 0;
        }
        if (!active) {
            return;
        }

        listTimer += System.currentTimeMillis() - lastListTimer;
        if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) || handler.getControllerManager().yMovement > 0.15) && listTimer >= listCooldown) {
            selectedItem--;
            listTimer = 0;
        }
        lastListTimer = System.currentTimeMillis();

        if ((handler.getKeyManager().keyJustPressed((KeyEvent.VK_DOWN)) || handler.getControllerManager().yMovement < -0.15)  && listTimer >= listCooldown) {
            selectedItem++;
            listTimer = 0;
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
        drawBrokeString(g, item.getDesc(), invDescX, invDescY, true, Color.BLACK, Assets.font14);
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

    private void drawBrokeString(Graphics g, String text, int x, int y, boolean center, Color color, Font font) {
        for (String line : text.split("\n"))
            Text.drawString(g,line, x, y += g.getFontMetrics().getHeight(), center, color, font);
    }
}
