package handler.worlds;

import handler.Handler;
import handler.entities.EntityManager;
import handler.entities.creatures.gabi;
import handler.entities.creatures.mumó;
import handler.entities.statics.Couch;
import handler.entities.statics.Lamp;
import handler.entities.statics.GameTriggerBox;
import handler.events.EntityEvent;
import handler.items.ItemManager;
import handler.tiles.Tile;
import handler.utils.Utils;

import java.awt.*;

public class World {


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
        entityManager = new EntityManager(handler, new gabi(handler, 300, 300));
        itemManager = new ItemManager(handler);
        entityManager.addEntity(new Lamp(handler, 300, 150));
        entityManager.addEntity(new Lamp(handler, 2100, 150));
        entityManager.addEntity(new Lamp(handler, 1200, 300));
        entityManager.addEntity(new Couch(handler, 1068, 450));
//        entityManager.addEntity(new Tanx(handler, 400, 300));
        entityManager.addEntity(new GameTriggerBox(handler, 900, 900, 900, 900, "res/sounds/Glass-Break.wav"));
        entityManager.addEntity(new GameTriggerBox(handler, 900, 900, 900, 900, new EntityEvent(handler, entityManager, new mumó(handler, 400, 300), 1)));
        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
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

    private void loadWorld(String path)
    {
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
}
