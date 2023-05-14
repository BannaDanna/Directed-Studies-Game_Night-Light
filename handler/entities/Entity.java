package handler.entities;

import handler.Handler;

import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected int health;
    protected boolean active = true;
    protected boolean killable = true;
    protected boolean solid = true;

    protected boolean activelyAdd = false;
    public static final int DEFAULT_HEALTH = 10;

    public boolean isActivelyAdd() {
        return activelyAdd;
    }

    public void setActivelyAdd(boolean activelyAdd) {
        this.activelyAdd = activelyAdd;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isKillable() {
        return killable;
    }

    public void setKillable(boolean killable) {
        this.killable = killable;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public Entity(Handler handler, float x, float y, int width, int height)
    {
        health = DEFAULT_HEALTH;
        this.handler = handler;
        this.x = x/* * handler.getXFactor()*/;
        this.y = y/* * handler.getYFactor()*/;
        this.width = width/* * handler.getXFactor()*/;
        this.height = height/* * handler.getYFactor()*/;
        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public boolean checkEntityCollisions(float xOffset, float yOffset)
    {
        for(Entity e : handler.getWorld().getEntityManager().getEntities())
        {
            if(e.equals(this) || !e.isSolid())
            {
                continue;
            }
            if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset))) {
                return true;
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset)
    {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public void hurt(int amt)
    {
        health -= amt;
        System.out.println("Hit!");
        if(health <= 0)
        {
            active = false;
            die();
        }
    }

    public abstract void die();

}
