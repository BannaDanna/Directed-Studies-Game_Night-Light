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

import static handler.tiles.Tile.setTILEWIDTH;

public class MenuState extends State{
    private double xScaleFactor = 1, yScaleFactor = 1;
    private ArrayList<UIObject> MainMenu;

    private UIManager uiManager;
    private JFrame frame;
    private UIObject play;
    private UIObject settings;
    private static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public MenuState(Handler handler)
    {
        super(handler);
        frame = handler.getGame().getDisplay().getFrame();
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton((int) (handler.getWidth() / 3.2), (int) (handler.getHeight() / 3.6), (int)(handler.getWidth() / 2.5), (int) (handler.getHeight() / 2.8125), Assets.btn_start, new ClickListener() {

            public void onCLick() {
                handler.getMouseManager().setUIManager(null);
//                handler.getWorld().getTile(4,4).setTILEWIDTH(handler.getGame().getDisplay().getFrame().getWidth() / 10);
                State.setState(handler.getGame().gameState);
            }
        }));


//        uiManager.addObject(new UIImageButton(200, 300, 256, 128, Assets.btn_start, new ClickListener() {
//
//            public void onCLick() {
//                play = uiManager.getObjects().get(0);
//                settings = uiManager.getObjects().get(1);
//                MainMenu = new ArrayList<>();
//                MainMenu.add(play);
//                MainMenu.add(settings);
//
//
//                for (int i = 1; i > -1; i--)
//                {
//                    uiManager.removeObject(uiManager.getObjects().get(i));
//                }
//
//                uiManager.addObject(new UIImageButton((float) (260 * xScaleFactor), (float) (50 * yScaleFactor), (int)(128 * xScaleFactor), (int)(64 * yScaleFactor), Assets.btn_start, new ClickListener() {
//                    @Override
//                    public void onCLick() {
//                        UIObject fullscreen = uiManager.getObjects().get(0);
//                        UIObject exitFullscreen = uiManager.getObjects().get(1);
//                        UIObject back = uiManager.getObjects().get(2);
//                        frame.dispose();
//                        frame.setUndecorated(true);
//                        frame.setVisible(true);
//                        device.setFullScreenWindow(frame);
//                        yScaleFactor = (double) handler.getGame().getDisplay().getFrame().getHeight() / (double)handler.getGame().getHeight();
//                        xScaleFactor = (double) handler.getGame().getDisplay().getFrame().getWidth() / (double) handler.getGame().getWidth();
//                        for(UIObject object : MainMenu)
//                        {
//                            UIImageButton tempBtn = (UIImageButton) object;
//                            tempBtn.setWidth((int)(tempBtn.getWidth() * xScaleFactor));
//                            tempBtn.setHeight((int)(tempBtn.getHeight() * yScaleFactor));
//                            tempBtn.setX((float)(tempBtn.getX() * xScaleFactor));
//                            tempBtn.setY((float)(tempBtn.getY() * yScaleFactor));
//                            if(object == play)
//                            {
//                                tempBtn.setClicker(new ClickListener() {
//                                    @Override
//                                    public void onCLick() {
//                                        handler.getMouseManager().setUIManager(null);
//                                        State.setState(handler.getGame().gameState);
//                                    }
//                                });
//                            } else if (object == settings) {
//                                tempBtn.setClicker(new ClickListener() {
//                                    @Override
//                                    public void onCLick() {
//                                        tempBtn.onClick();
//                                    }
//                                });
//                            }
//                            MainMenu.add(tempBtn);
//                        }
//                        MainMenu.remove(0);
//                        MainMenu.remove(1);
//                        for(UIObject object : uiManager.getObjects())
//                        {
//                            UIImageButton tempBtn = (UIImageButton) object;
//                            tempBtn.setWidth((int)(tempBtn.getWidth() * xScaleFactor));
//                            tempBtn.setHeight((int)(tempBtn.getHeight() * yScaleFactor));
//                            tempBtn.setX((float)(tempBtn.getX() * xScaleFactor));
//                            tempBtn.setY((float)(tempBtn.getY() * yScaleFactor));
//                            if(object == fullscreen)
//                            {
//                                tempBtn.setClicker(new ClickListener() {
//                                    @Override
//                                    public void onCLick() {
//                                        fullscreen.onClick();
//                                    }
//                                });
//                            } else if(object == exitFullscreen)
//                            {
//                                tempBtn.setClicker(new ClickListener() {
//                                    @Override
//                                    public void onCLick() {
//                                        exitFullscreen.onClick();
//                                    }
//                                });
//                            } else if (object == back)
//                            {
//                                tempBtn.setClicker(new ClickListener() {
//                                    @Override
//                                    public void onCLick() {
//                                        tempBtn.onClick();
//                                    }
//                                });
//                            }
//                            uiManager.addObject(tempBtn);
//                        }
//                    }
//                }));
//
//                uiManager.addObject(new UIImageButton((float)(260 * xScaleFactor), (float)(120 * yScaleFactor), (int)(128 * xScaleFactor), (int)(64 * yScaleFactor), Assets.btn_start, new ClickListener() {
//                    @Override
//                    public void onCLick() {
//                        UIObject fullscreen = uiManager.getObjects().get(0);
//                        UIObject exitFullscreen = uiManager.getObjects().get(1);
//                        UIObject back = uiManager.getObjects().get(2);
//                        frame.dispose();
//                        frame.setUndecorated(false);
//                        device.setFullScreenWindow(null);
//                        frame.setVisible(true);
//                        ArrayList<UIObject> temp = new ArrayList<>();
//                        for(UIObject object : MainMenu)
//                        {
//                            UIImageButton tempBtn = (UIImageButton) object;
//                            tempBtn.setWidth((int)(tempBtn.getWidth() / xScaleFactor));
//                            tempBtn.setHeight((int)(tempBtn.getHeight() / yScaleFactor));
//                            tempBtn.setX((float)(tempBtn.getX() / xScaleFactor));
//                            tempBtn.setY((float)(tempBtn.getY() / yScaleFactor));
//
//                            tempBtn.setClicker(new ClickListener() {
//                                @Override
//                                public void onCLick() {
//                                    tempBtn.onClick();
//                                }
//                            });
//                            MainMenu.add(tempBtn);
//                        }
//                        MainMenu.remove(0);
//                        MainMenu.remove(1);
//                        temp = new ArrayList<>();
//                        for(UIObject object : uiManager.getObjects())
//                        {
//                            UIImageButton tempBtn = (UIImageButton) object;
//                            tempBtn.setWidth((int)(tempBtn.getWidth() / xScaleFactor));
//                            tempBtn.setHeight((int)(tempBtn.getHeight() / yScaleFactor));
//                            tempBtn.setX((float)(tempBtn.getX() / xScaleFactor));
//                            tempBtn.setY((float)(tempBtn.getY() / yScaleFactor));
//
//                            if(object == fullscreen)
//                            {
//                                tempBtn.setClicker(new ClickListener() {
//                                    @Override
//                                    public void onCLick() {
//                                        fullscreen.onClick();
//                                    }
//                                });
//                            } else if(object == exitFullscreen)
//                            {
//                                tempBtn.setClicker(new ClickListener() {
//                                    @Override
//                                    public void onCLick() {
//                                        this.onCLick();
//                                    }
//                                });
//                            } else if (object == back)
//                            {
//                                tempBtn.setClicker(new ClickListener() {
//                                    @Override
//                                    public void onCLick() {
//                                        tempBtn.onClick();
//                                    }
//                                });
//                            }
//                            uiManager.addObject(tempBtn);
//                        }
//                    }
//
//                }));
//                uiManager.addObject(new UIImageButton((float)(200 * xScaleFactor), (float)(190 * yScaleFactor), (int)(256 * xScaleFactor), (int)(128 * yScaleFactor), Assets.btn_start, new ClickListener() {
//                    @Override
//                    public void onCLick() {
//                        UIObject fullscreen = uiManager.getObjects().get(0);
//                        UIObject exitFullscreen = uiManager.getObjects().get(1);
//                        UIObject back = uiManager.getObjects().get(2);
//                        for (int i = 2; i > -1; i--) {
//                            uiManager.removeObject(uiManager.getObjects().get(i));
//                        }
//                        uiManager.addObject(play);
//                        uiManager.addObject(settings);
//                    }
//                }));
//
//            }
//        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
        if(handler.getControllerManager().options)
        {
            handler.getMouseManager().setUIManager(null);
            State.setState(handler.getGame().gameState);
        }

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
