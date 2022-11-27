package handler.states;

import handler.Game;
import handler.Handler;
import handler.gfx.Assets;
import handler.ui.ClickListener;
import handler.ui.UIImageButton;
import handler.ui.UIManager;
import handler.Launcher;
import handler.ui.UIObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuState extends State{
    private int xScaleFactor = 1, yScaleFactor = 1;

    private UIManager uiManager;
    private JFrame frame;
    private UIObject play;
    private UIObject settings;
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public MenuState(Handler handler)
    {
        super(handler);
        frame = handler.getGame().getDisplay().getFrame();
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(200 * xScaleFactor, 100 * yScaleFactor, 256, 128, Assets.btn_start, new ClickListener() {

            public void onCLick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(200 * xScaleFactor, 300 * yScaleFactor, 256, 128, Assets.btn_start, new ClickListener() {

            public void onCLick() {
                play = uiManager.getObjects().get(0);
                settings = uiManager.getObjects().get(1);

                for (int i = 1; i > -1; i--)
                {
                    uiManager.removeObject(uiManager.getObjects().get(i));
                }
                uiManager.addObject(new UIImageButton(260 * xScaleFactor, 50 * yScaleFactor, 128, 64, Assets.btn_start, new ClickListener() {
                    @Override
                    public void onCLick() {
                        ArrayList<UIObject> temp = new ArrayList<>();
                        for (int i = 2; i > -1; i--) {
                            temp.add(uiManager.getObjects().get(i));
                        }
                        frame.dispose();
                        frame.setUndecorated(true);
                        frame.setVisible(true);
                        device.setFullScreenWindow(frame);
                        yScaleFactor = handler.getGame().getDisplay().getFrame().getHeight() / handler.getGame().getHeight();
                        xScaleFactor = handler.getGame().getDisplay().getFrame().getWidth() / handler.getGame().getWidth();
                        for (int i = 2; i > -1; i--) {
                            uiManager.addObject(temp.get(i));
                        }
                    }
                }));

                uiManager.addObject(new UIImageButton(260 * xScaleFactor, 120 * yScaleFactor, 128, 64, Assets.btn_start, new ClickListener() {
                    @Override
                    public void onCLick() {
                        frame.dispose();
                        frame.setUndecorated(false);
                        device.setFullScreenWindow(null);
                        frame.setVisible(true);
                    }
                }));
                uiManager.addObject(new UIImageButton(200 * xScaleFactor, 190 * yScaleFactor, 256, 128, Assets.btn_start, new ClickListener() {
                    @Override
                    public void onCLick() {
                        for (int i = 2; i > -1; i--) {
                            uiManager.removeObject(uiManager.getObjects().get(i));
                        }
                        uiManager.addObject(play);
                        uiManager.addObject(settings);
                    }
                }));

            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();

//        if(handler.getMouseManager().isLeftPressed() )
//        {
//            State.setState(handler.getGame().gameState);
//        }

        //Directly to game state
        //REMOVE in final product
//        State.setState(handler.getGame().gameState);
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
//        g.drawImage(Assets.empty, 0, 0, null);
//        g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10,10);
    }
}
