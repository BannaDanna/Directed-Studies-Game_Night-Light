package handler.entities.creatures;

import handler.Handler;
import handler.entities.Entity;
import handler.gfx.Animation;
import handler.gfx.Assets;
import handler.items.Item;

import java.awt.*;

public class Tanx extends Creature {

    private Animation animIdle;

    private long lastAttackTimer, attackCooldown = 1000, attackTimer = attackCooldown;

    private int attackDir;

    public Tanx(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = handler.getWidth() / 64;
        bounds.y = (int) (handler.getHeight() / 9.6);
        bounds.height = (int) (handler.getWidth() / 7);
        bounds.width = (int) (handler.getHeight() / 9.5555);

        animIdle = new Animation(500, Assets.player_idle);
    }

    @Override
    public void tick() {
        animIdle.tick();

        getinput();
        move();

        checkAttacks();
    }

    @Override
    public void render(Graphics g) {
        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();

        ar.width = handler.getWidth() / 40;
        ar.height = handler.getWidth() / 30;

        if (attackDir == 1) {
            ar.x = cb.x + cb.width / 2;
            ar.y = cb.y - ar.width;
        } else if (attackDir == 2)
        {
            ar.x = cb.x +cb.width / 2 - cb.width / 2 + 20;
            ar.y = cb.y + cb.height - 70;
        } else if (attackDir == 3)
        {
            ar.x = cb.x - cb.width;
            ar.y = cb.y + cb.height / 2 - cb.height / 2 - 48;
        } else if(attackDir == 4)
        {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - cb.height / 2 - 48;
        }

        g.fillRect(ar.x, ar.y, ar.width, ar.height);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.batteriesItem.createNew((int) (x + bounds.width / 2), (int) (y + bounds.y + bounds.height)));
    }

    private void getinput() {
        xMove = 0;
        yMove = 0;
        int action;

        action = Math.round(Math.round((Math.random() * 4) + 1));

        if (action == 1) {
            xMove = speed;
        } else if (action == 2) {
            xMove = -speed;
        } else if (action == 3) {
            yMove = speed;
        } else if (action == 4) {
            yMove = -speed;
        } else if (action == 5) {
            attackDir = Math.round(Math.round((Math.random() * 3) + 1));
        }
    }

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        }

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();

        ar.width = handler.getWidth() / 40;
        ar.height = handler.getWidth() / 30;

        if (attackDir == 1) {
            ar.x = cb.x + cb.width / 2;
            ar.y = cb.y - ar.width;
        } else if (attackDir == 2)
        {
            ar.x = cb.x +cb.width / 2 - cb.width / 2 + 20;
            ar.y = cb.y + cb.height - 70;
        } else if (attackDir == 3)
        {
            ar.x = cb.x - cb.width;
            ar.y = cb.y + cb.height / 2 - cb.height / 2 - 48;
        } else if(attackDir == 4)
        {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - cb.height / 2 - 48;
        } else {
            return;
        }

        for(Entity e : handler.getWorld().getEntityManager().getEntities())
        {
            if(e.equals(this))
            {
                continue;
            }
            if(!e.isKillable())
            {
                continue;
            }
                if(e.getCollisionBounds(0,0).intersects(ar))
                {
                    e.hurt(2);
                    System.out.println("GoTChA");
                    attackDir = 0;
                }
        }
    }

    @Override
    public String toString() {
        return "Tanx{}";
    }
}