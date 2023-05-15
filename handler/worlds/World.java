package handler.worlds;

import handler.Handler;
import handler.entities.Entity;
import handler.entities.EntityManager;
import handler.entities.creatures.Gabi;
import handler.entities.creatures.Mumó;
import handler.entities.statics.Lamp;
import handler.entities.statics.GameTriggerBox;
import handler.events.EntityEvent;
import handler.events.EventManager;
import handler.items.ItemManager;
import handler.tiles.Tile;
import handler.utils.Utils;
import jdk.jfr.Event;

import java.lang.reflect.*;

import java.awt.*;

public class World {

    private int xFactor, yFactor;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private Handler handler;
    //Entities
    private EntityManager entityManager;

    private ItemManager itemManager;
    public World(Handler handler, String path){
        //added in 1366 * 768 res display
        this.handler = handler;
        xFactor = handler.getWidth() / 1366;
        yFactor = handler.getHeight() / 768;
        entityManager = new EntityManager(handler, new Gabi(handler, 300 * xFactor, 300 * yFactor));
        itemManager = new ItemManager(handler);
//        entityManager.addEntity(new Lamp(handler, 300 * xFactor, 150 * yFactor));
//        entityManager.addEntity(new Lamp(handler, 2100 * xFactor, 150 * yFactor));
//        entityManager.addEntity(new Lamp(handler, 1200 * xFactor, 300 * yFactor));
//        entityManager.addEntity(new Couch(handler, 1068 * xFactor, 450 * yFactor));
//        entityManager.addEntity(new Tanx(handler, 400, 300));
//        entityManager.addEntity(new GameTriggerBox(handler, 900 * xFactor, 900 * yFactor, 900 * xFactor, 900 * yFactor, "res/sounds/Glass-Break.wav"));
        entityManager.addEntity(new GameTriggerBox(handler, 900 * xFactor, 900 * yFactor, 900 * xFactor, 900 * yFactor, new EntityEvent(handler, entityManager, new Mumó(handler, 400 * xFactor, 300 * yFactor), 1)));
        try {
            loadWorld(path);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        entityManager.getPlayer().setX(spawnX * xFactor);
        entityManager.getPlayer().setY(spawnY * yFactor);
    }

    public void tick()
    {
        entityManager.tick();
        itemManager.tick();
    }

    public void render(Graphics g)
    {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEWIDTH);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);

        for(int y = yStart; y < yEnd; y++)
        {
            for(int x = xStart; x < xEnd; x++)
            {
                getTile(x,y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        //items
        itemManager.render(g);
        //entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.emptyTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];
        if(t== null)
        {
            return Tile.emptyTile;
        }
        return t;
    }

    private void loadWorld(String path) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }

        String args = Utils.loadFileAsString(tokens[(width * height) + 4]);
        String[] enemyTokens = args.split("\\s+");
        int staticsNum = Utils.parseInt(enemyTokens[0]);
        int enemiesNum = Utils.parseInt(enemyTokens[(staticsNum * 3) + 1]);
        for (int i = 1; i < (staticsNum * 3) + 1; i += 3) {
            entityManager.addEntity((Entity) Class.forName(enemyTokens[i]).getConstructor(handler.getClass(), float.class, float.class).newInstance(handler, Utils.parseInt(enemyTokens[i + 1]), Utils.parseInt(enemyTokens[i + 2])));
        }
        for (int i = (staticsNum * 3) + 2; i < (enemiesNum * 3) + (staticsNum * 5) + 1; i += 5) {
            if(Class.forName(enemyTokens[i + 4]).getClass().equals(String.class))
            {
                entityManager.addEntity(GameTriggerBox.class.getConstructor(handler.getClass(), float.class, float.class, int.class, int.class, String.class).newInstance(handler, Utils.parseInt(enemyTokens[i]), Utils.parseInt(enemyTokens[i + 1]), Utils.parseInt(enemyTokens[i + 2]), Utils.parseInt(enemyTokens[i + 3]), enemyTokens[i + 4]));
            } else {
                entityManager.addEntity(GameTriggerBox.class.getConstructor(handler.getClass(), float.class, float.class, int.class, int.class, EntityEvent.class, int.class).newInstance(handler, Utils.parseInt(enemyTokens[i]),Utils.parseInt(enemyTokens[i + 1]), Utils.parseInt(enemyTokens[i + 2]), Utils.parseInt(enemyTokens[i + 3]), Class.forName(enemyTokens[i + 4]).getConstructor(handler.getClass(), float.class, float.class).newInstance(handler, enemyTokens[i+5], enemyTokens[i+6]), enemyTokens[i+7]));
                i += 4;
            }
        }
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public int getxFactor() {
        return xFactor;
    }

    public void setxFactor(int xFactor) {
        this.xFactor = xFactor;
    }

    public int getyFactor() {
        return yFactor;
    }

    public void setyFactor(int yFactor) {
        this.yFactor = yFactor;
    }
}
