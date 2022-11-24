package handler;

import handler.display.Display;
import handler.gfx.Assets;
import handler.gfx.GameCamera;
import handler.gfx.SpriteSheet;
import handler.input.KeyManager;
import handler.input.MouseManager;
import handler.states.GameState;
import handler.states.MenuState;
import handler.states.SettingsState;
import handler.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{

    private Display display;

    public String Title;
    private int Width;
    private int Height;

    private boolean running = false;

    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gameState;
    public State menuState;
    public State settingsState;

//    private BufferedImage testImage;
//    private BufferedImage banana;
    private BufferedImage sheet;
    private SpriteSheet sheetCrop;

    private KeyManager keyManager;
    private MouseManager mouseManager;

    private GameCamera gameCamera;

    public Handler handler;

    public Game(String Title, int Width, int Height)
    {
        this.Width = Width;
        this.Height = Height;
        this.Title = Title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init()
    {
        display = new Display(Title, Width, Height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0,0);


        settingsState = new SettingsState(handler);
        menuState = new MenuState(handler);
        gameState = new GameState(handler);
        State.setState(menuState);
    }

    private void tick()
    {
        keyManager.tick();

        if(State.getState() != null)
        {
            State.getState().tick();
        }
    }

    private void render()
    {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //clear screen
        g.clearRect(0,0,Width,Height);
        //Draw stuff...

        if(State.getState() != null)
        {
            State.getState().render(g);
        }

        //stop drawin'
        bs.show();
        g.dispose();
    }

    public void run()
    {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000)
            {
                System.out.println("Ticks and frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
    public MouseManager getMouseManager()
    {
        return mouseManager;
    }
    public GameCamera getGameCamera()
    {
        return gameCamera;
    }
    public synchronized void start()
    {
        if(running)
        {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public int getWidth()
    {
        return Width;
    }

    public int getHeight()
    {
        return Height;
    }

    public synchronized void stop()
    {
        if(!running)
        {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
