package handler.states;

import handler.Handler;
import handler.display.Display;
import handler.gfx.Assets;
import handler.ui.ClickListener;
import handler.ui.UIImageButton;
import handler.ui.UIManager;

import javax.swing.*;
import java.awt.*;

public class SettingsState extends State{
    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];
    private UIManager uiManager;
    private int UIWidth = (int)(handler.getWidth() / 2.5);
     private int UIHeight = UIWidth;

    public SettingsState(Handler handler)
    {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(200, 100, 256, 128, Assets.btn_start, new ClickListener() {

            public void onCLick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().menuState);
            }
        }));
    }

    @Override
    public void tick() {
    uiManager.tick();
    }

    @Override
    public void render(Graphics g) {

        uiManager.render(g);
        //g.drawImage(Assets.dos, 0,0,null);
    }
}
