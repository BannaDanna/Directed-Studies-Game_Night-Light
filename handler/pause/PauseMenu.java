package handler.pause;

import handler.Handler;
import handler.gfx.Assets;
import handler.gfx.Text;
import handler.items.Item;
import handler.ui.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PauseMenu {

    private int PauseX = 0, PauseY = 0, PauseWidth, PauseHeight, PauseListCenterX, PauseListCenterY, PauseListSpacing;

    private int selectedItem = 0;

    private Handler handler;

    private boolean active = false;

//    private long pauseTimer, lastPauseTimer, pauseCooldown = 300;
    private long listTimer, lastListTimer, listCooldown = 200;

    private int UIWidth, UIHeight, numUIImageButton = 7;

    private UIManager uiManager;

    public PauseMenu (Handler handler){
        this.handler = handler;
        uiManager = new UIManager(handler);
        PauseWidth = handler.getWidth();
        PauseHeight = handler.getHeight();
        PauseListCenterX = handler.getWidth()/5;
        PauseListCenterY = handler.getHeight()/2;
        PauseListSpacing = handler.getHeight()/9;
        UIHeight = handler.getHeight()/10;
        UIWidth = UIHeight * 2;
        uiManager.addObject(new UIImageButton((float) PauseListCenterX, (float) handler.getHeight() / 5, UIWidth, UIHeight, Assets.btn_start, new ClickListener() {
            @Override
            public void onCLick() {
                active = !active;
            }
        }));

        for(int i = 1; i < numUIImageButton; i++)
        {
                uiManager.addObject(new UIImageButton((float) PauseListCenterX, ((uiManager.getObjects().get(0).getY()) + i * PauseListSpacing), UIWidth, UIHeight, Assets.btn_start, new ClickListener() {
                    @Override
                    public void onCLick() {

                    }
                }) );
        }
         uiManager.getObjects().get(numUIImageButton - 1).setClicker(new ClickListener() {
             @Override
             public void onCLick()
             {
                 handler.getGame().getDisplay().getFrame().setVisible(false);
                 handler.getGame().getDisplay().getFrame().dispose();
                 System.exit(0);
             }
         });
    }

    public void tick() {
        uiManager.tick();
//        pauseTimer += System.currentTimeMillis() - lastPauseTimer;
//        lastPauseTimer = System.currentTimeMillis();


        if (((handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE) || handler.getControllerManager().share) /* && pauseTimer >= pauseCooldown */) && !handler.getWorld().getEntityManager().getPlayer().getInventory().isActive())
        {
//            System.out.println(active);
            active = !active;
//            pauseTimer = 0;
        }
        if (!active) {
            return;
        } else {
            handler.getMouseManager().setUIManager(uiManager);
        }

        listTimer += System.currentTimeMillis() - lastListTimer;
        lastListTimer = System.currentTimeMillis();

        if ((handler.getControllerManager().yMovement > 0.15) && listTimer >= listCooldown) {
            selectedItem--;
            listTimer = 0;
        }
        if ((handler.getControllerManager().yMovement < -0.15)  && listTimer >= listCooldown) {
            selectedItem++;
            listTimer = 0;
        }
        if (selectedItem < 0) {
            selectedItem = uiManager.getObjects().size() - 1;
        } else if (selectedItem >= uiManager.getObjects().size())
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

        g.drawImage(Assets.inventoryScreen, PauseX, PauseY, PauseWidth, PauseHeight, null);


            int len = uiManager.getObjects().size();
            if(len == 0)
            {
                return;
            }
            if(handler.getControllerManager().connected)
            {
                for(int i = -5; i < 6; i++)
                {
                    if(selectedItem + i < 0 || selectedItem + i >= len)
                        continue;
                    if(i == 0)
                    {
                        uiManager.getObjects().get(selectedItem + i).setHovering(true);
                    } else {
                        uiManager.getObjects().get(selectedItem + i).setHovering(false);
                    }

                }
            }

        uiManager.render(g);
    }

    public boolean isActive() {
        return active;
    }
}
