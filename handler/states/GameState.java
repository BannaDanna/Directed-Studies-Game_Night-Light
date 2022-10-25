package handler.states;

import handler.Handler;
import handler.entities.creatures.Player;
import handler.entities.statics.Lamp;
import handler.worlds.World;

import java.awt.*;

public class GameState extends State{


    private World world;

    public GameState(Handler handler)
    {
        super(handler);
        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        //Tile.tiles[0].render(g, 64,64);
        //Tile.tiles[1].render(g, 0, 0);
    }
}
