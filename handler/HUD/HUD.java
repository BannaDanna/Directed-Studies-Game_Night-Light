package handler.HUD;

import handler.Handler;
import handler.gfx.Assets;
import handler.gfx.Text;

import java.awt.*;

public class HUD {

    int HPIconX = 0, HPIconY = 0, HPIconWidth, HPIconHeight, HPx, HPy;
    int staminaX, staminaY;

    private Handler handler;
    private boolean active = true;

    public HUD(Handler handler)
    {
        this.handler = handler;
        HPIconHeight = handler.getHeight() / 10;
        HPIconWidth = HPIconHeight;
        HPIconX = (int) (handler.getWidth() * 0.005);
        HPIconY = (int) (handler.getHeight() - HPIconHeight - (handler.getHeight() * 0.009));
        HPx = HPIconX + (HPIconWidth / 2);
        HPy = HPIconY + (HPIconHeight / 2);
        staminaX = handler.getWidth() / 2;
        staminaY = handler.getHeight() - 56;
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        g.drawImage(Assets.batteries, HPIconX, HPIconY, HPIconWidth, HPIconHeight, null);
        Text.drawString(g, Integer.toString(handler.getWorld().getEntityManager().getPlayer().getHealth()), HPx, HPy, true, Color.BLACK, Assets.font56);
        Text.drawString(g, Integer.toString(handler.getWorld().getEntityManager().getPlayer().getStamina()), staminaX, staminaY, true, Color.blue, Assets.font56);
    }
}
