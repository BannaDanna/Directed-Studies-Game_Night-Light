package handler;

import handler.gfx.GameCamera;
import handler.input.ControllerManager;
import handler.input.KeyManager;
import handler.input.MouseManager;
import handler.worlds.World;

public class Handler {

    private Game game;
    private World world;


    public GameCamera getGameCamera()
    {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }
    public MouseManager getMouseManager()
    {
        return game.getMouseManager();
    }

    public int getWidth()
    {
        return game.getWidth();
    }

    public int getHeight()
    {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Handler(Game game)
    {
        this.game = game;
    }

    public ControllerManager getControllerManager()
    {
        return game.getControllerManager();
    }

//    public int getXFactor()
//    {
//        return world.getxFactor();
//    }
//
//    public int getYFactor()
//    {
//        return world.getyFactor();
//    }
}
