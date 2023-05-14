package handler.entities;

import handler.Handler;
import handler.entities.creatures.gabi;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private gabi gabi;
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

    public EntityManager(Handler handler, gabi gabi)
    {
        this.handler = handler;
        this.gabi = gabi;
        entities = new ArrayList<Entity>();
        activelyAdd = new ArrayList<Entity>();
        addEntity(gabi);
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
        gabi.postRender(g);
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
        for(int i = 0; i < activelyAdd.size(); i++)
        {
            activelyAdd.remove(i);
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

    public gabi getPlayer() {
        return gabi;
    }

    public void setPlayer(gabi gabi) {
        this.gabi = gabi;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
