package handler.states;

import handler.Game;
import handler.Handler;
import handler.gfx.Assets;
import handler.ui.ClickListener;
import handler.ui.UIImageButton;
import handler.ui.UIManager;
import handler.Launcher;

import javax.swing.*;
import java.awt.*;

public class MenuState extends State{

    private UIManager uiManager;
    private JFrame frame;
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public MenuState(Handler handler)
    {
        super(handler);
        frame = handler.getGame().getDisplay().getFrame();
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(200, 100, 256, 128, Assets.btn_start, new ClickListener() {

            public void onCLick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(200, 300, 256, 128, Assets.btn_start, new ClickListener() {

            public void onCLick() {
                uiManager.removeObject(uiManager.getObjects().get(0));
                uiManager.addObject(new UIImageButton(200, 100, 256, 128, Assets.btn_start, new ClickListener() {
                    @Override
                    public void onCLick() {
                        frame.dispose();
                        frame.setUndecorated(true);
                        device.setFullScreenWindow(frame);
                        frame.setVisible(true);
                    }
                }));

                uiManager.addObject(new UIImageButton(200, 300, 256, 128, Assets.btn_start, new ClickListener() {
                    @Override
                    public void onCLick() {

                    }
                }));
                uiManager.removeObject(uiManager.getObjects().get(0));

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
