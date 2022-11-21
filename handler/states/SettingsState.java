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
        JFrame frame = handler.getGame().getDisplay().getFrame();
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(200, 100, UIWidth, UIHeight, Assets.btn_start, new ClickListener() {
            @Override
            public void onCLick() {
                frame.dispose();
                frame.setUndecorated(true);
                device.setFullScreenWindow(frame);
                frame.setVisible(true);
            }

            uiManager.addObject(new UIImageButton(200, 100, ))
        }));
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        //g.drawImage(Assets.dos, 0,0,null);
    }
}
