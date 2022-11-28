package handler.states;

import com.sun.tools.javac.Main;
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
    private double xScaleFactor = 1, yScaleFactor = 1;
    private ArrayList<UIObject> MainMenu;

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

        uiManager.addObject(new UIImageButton(200, 100, 256, 128, Assets.btn_start, new ClickListener() {

            public void onCLick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(200, 300, 256, 128, Assets.btn_start, new ClickListener() {

            public void onCLick() {
                play = uiManager.getObjects().get(0);
                settings = uiManager.getObjects().get(1);
                MainMenu = new ArrayList<>();
                MainMenu.add(play);
                MainMenu.add(settings);
                System.out.println(xScaleFactor);
                System.out.println(yScaleFactor);
                System.out.println(play.getX());
                System.out.println(play.getY());
                System.out.println(play.getWidth());
                System.out.println(play.getHeight());

                for (int i = 1; i > -1; i--)
                {
                    uiManager.removeObject(uiManager.getObjects().get(i));
                }

                uiManager.addObject(new UIImageButton((float) (260 * xScaleFactor), (float) (50 * yScaleFactor), (int)(128 * xScaleFactor), (int)(64 * yScaleFactor), Assets.btn_start, new ClickListener() {
                    @Override
                    public void onCLick() {
                        ArrayList<UIObject> temp = new ArrayList<>();
                        for (UIObject object : uiManager.getObjects()) {
                            temp.add(object);
                            uiManager.getObjects().remove(object);
                        }
                        frame.dispose();
                        frame.setUndecorated(true);
                        frame.setVisible(true);
                        device.setFullScreenWindow(frame);
                        yScaleFactor = (double) handler.getGame().getDisplay().getFrame().getHeight() / (double)handler.getGame().getHeight();
                        xScaleFactor = (double) handler.getGame().getDisplay().getFrame().getWidth() / (double) handler.getGame().getWidth();
                        for(UIObject object : MainMenu)
                        {
                            object.setWidth((int)(object.getWidth() * xScaleFactor));
                            object.setHeight((int)(object.getHeight() * yScaleFactor));
                            object.setX((float)(object.getX() * xScaleFactor));
                            object.setY((float)(object.getY() * yScaleFactor));
                        }
                        for(UIObject object : temp)
                        {
                            uiManager.addObject(new UIImageButton(object.getX(), object.getY(), object.getWidth(), object.getHeight(),));
                        }
                    }
                }));

                uiManager.addObject(new UIImageButton((float)(260 * xScaleFactor), (float)(120 * yScaleFactor), (int)(128 * xScaleFactor), (int)(64 * yScaleFactor), Assets.btn_start, new ClickListener() {
                    @Override
                    public void onCLick() {
                        ArrayList<UIObject> temp = new ArrayList<>();
                        for (UIObject object : uiManager.getObjects()) {
                            temp.add(object);
                        }
                        frame.dispose();
                        frame.setUndecorated(false);
                        device.setFullScreenWindow(null);
                        frame.setVisible(true);
                        for(UIObject object : MainMenu)
                        {
                            object.setWidth((int)((double)object.getWidth() / xScaleFactor));
                            object.setHeight((int)((double)object.getHeight() / yScaleFactor));
                            object.setX((float)((double)object.getX() / xScaleFactor));
                            object.setY((float)((double)object.getY() / yScaleFactor));
                        }
                        for (UIObject object : temp)
                        {
                            object.setWidth((int)((double)object.getWidth() / xScaleFactor));
                            object.setHeight((int)((double)object.getHeight() / yScaleFactor));
                            object.setX((float)((double)object.getX() / xScaleFactor));
                            object.setY((float)((double)object.getY() / yScaleFactor));
                        }
                        System.out.println(xScaleFactor);
                        System.out.println(yScaleFactor);
                        System.out.println(play.getX());
                        System.out.println(play.getY());
                        System.out.println(play.getWidth());
                        System.out.println(play.getHeight());
                    }
                }));
                uiManager.addObject(new UIImageButton((float)(200 * xScaleFactor), (float)(190 * yScaleFactor), (int)(256 * xScaleFactor), (int)(128 * yScaleFactor), Assets.btn_start, new ClickListener() {
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
