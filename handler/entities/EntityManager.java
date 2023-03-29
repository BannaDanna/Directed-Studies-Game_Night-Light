package handler.entities;

import handler.Handler;
import handler.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities, activelyAdd;
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
            {
                return -1;
            }
            return 1;
        }
    };

    public EntityManager(Handler handler, Player player)
    {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        activelyAdd = new ArrayList<Entity>();
        addEntity(player);
    }


    public void tick()
    {
        activelyAddEntities();
        Iterator<Entity> it = entities.listIterator();
        while(it.hasNext())
        {
            Entity e = it.next();
            e.tick();
            if(!e.isActive())
            {
                it.remove();
            }
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g)
    {
        for(Entity e : entities)
        {
            e.render(g);
        }
        player.postRender(g);
    }

    public void activelyAddEntity(Entity e)
    {
        activelyAdd.add(e);
    }

    public void activelyAddEntities()
    {
        for(int i = 0; i < activelyAdd.size(); i++)
        {
            entities.add(activelyAdd.get(i));
        }
    }

    public void addEntity(Entity e)
    {
        entities.add(e);
    }

    //Getters and Setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
