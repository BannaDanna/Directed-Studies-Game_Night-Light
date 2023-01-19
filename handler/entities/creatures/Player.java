package handler.entities.creatures;

import handler.Handler;
import handler.entities.Entity;
import handler.gfx.Animation;
import handler.gfx.Assets;
import handler.inventory.Inventory;
import handler.items.Item;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{

//animations
    private Animation animIdle, animMoveLeft, animMoveRight, animMoveUp, animMoveDown, animAttackLeft, animAttackRight, animAttackUp, animAttackDown;
    //attack and attack animation timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
    private long lastAnimationTimer, animationCooldown = 500, animationTimer = animationCooldown;
    //inventory
    private Inventory inventory;

    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = handler.getWidth() / 64;
        bounds.y = (int)(handler.getHeight() / 9.6);
        bounds.height = (int)(handler.getWidth() / 6.4);
        bounds.width = (int) (handler.getHeight() / 5.3333);

        //Animations
        animIdle = new Animation(500, Assets.player_idle);
//        animMoveLeft = new Animation(500, Assets.player_move_left);
//        animMoveRight = new Animation(500, Assets.player_move_right);
//        animMoveUp = new Animation(500, Assets.player_move_up);
//        animMovDown = new Animation(500, Assets.player_move_down);
//        animAttackLeft = new Animation(800, Assets.player_attack_left);
//        animAttackRight = new Animation(800, Assets.player_attack_right);
//        animAttackUp = new Animation(800, Assets.player_attack_up);
//        animAttackDown = new Animation(800, Assets.player_attack_down);

        inventory = new Inventory(handler);
        inventory.addItem(Item.batteriesItem);
        inventory.addItem(Item.batteriesItem);
        inventory.addItem(Item.batteriesItem);
        inventory.addItem(Item.batteriesItem);
        inventory.addItem(Item.batteriesItem);
    }

    @Override
    public void tick() {
        //Animations
        animIdle.tick();
//      animMoveLeft.tick();
//      animMoveRight.tick();
//      animMoveUp.tick();
//      animMoveDown.tick();
//      animAttackLeft.tick();
//      animAttackRight.tick();
//      animAttackUp.tick();
//      animAttackDown.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        //attack
        checkAttacks();
        //Inventory
        inventory.tick();
    }

    private void checkAttacks()
    {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown) {
            return;
        }

        if(inventory.isActive())
        {
            return;
        }

        Rectangle cb = getCollisionBounds(0,0);
        Rectangle ar = new Rectangle();
        Rectangle ar2 = new Rectangle();
        Rectangle ar3 = new Rectangle();
        Rectangle ar4 = new Rectangle();
        Rectangle ar5 = new Rectangle();
        int arSize = handler.getWidth() / 40;
        ar.width = arSize;
        ar.height = arSize;

        if(handler.getKeyManager().aUp)
        {
            ar2.width = arSize * 2;
            ar2.height = arSize;
            ar3.width = arSize * 3;
            ar3.height = arSize;
            ar4.width = arSize * 4;
            ar4.height = arSize;
            ar5.width = arSize * 5;
            ar5.height = arSize;
            ar.x = cb.x +cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
            ar2.x = cb.x + cb.width / 2 - arSize;
            ar2.y = cb.y - arSize - ar.height;
            ar3.x = (int) (cb.x + cb.width / 2 - arSize * 1.5);
            ar3.y = cb.y - arSize - ar2.height * 2;
            ar4.x = cb.x + cb.width / 2 - arSize * 2;
            ar4.y = cb.y - arSize - ar3.height * 3;
            ar5.x = (int) (cb.x + cb.width / 2 - arSize * 2.5);
            ar5.y = cb.y - arSize - ar3.height * 4;
        } else if(handler.getKeyManager().aDown)
        {
            ar2.width = arSize * 2;
            ar2.height = arSize;
            ar3.width = arSize * 3;
            ar3.height = arSize;
            ar4.width = arSize * 4;
            ar4.height = arSize;
            ar5.width = arSize * 5;
            ar5.height = arSize;
            ar.x = cb.x +cb.width / 2 - arSize / 2 + 20;
            ar.y = cb.y + cb.height - 70;
            ar2.x = cb.x + cb.width / 2 - arSize + 20;
            ar2.y = cb.y + cb.height + arSize - 240;
            ar3.x = (int) (cb.x + cb.width / 2 - arSize * 1.5 + 20);
            ar3.y = cb.y + cb.height + arSize * 2 - 240;
            ar4.x = cb.x + cb.width / 2 - arSize * 2 + 20;
            ar4.y = cb.y + cb.height + arSize * 3 - 240;
            ar5.x = (int) (cb.x + cb.width / 2 - arSize * 2.5 + 20);
            ar5.y = cb.y + cb.height + arSize * 4 - 240;
        } else if(handler.getKeyManager().aLeft)
        {
            ar2.width = arSize;
            ar2.height = arSize * 2;
            ar3.width = arSize;
            ar3.height = arSize * 3;
            ar4.width = arSize;
            ar4.height = arSize * 4;
            ar5.width = arSize;
            ar5.height = arSize * 5;
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2 - 48;
            ar2.x = cb.x - arSize * 2;
            ar2.y = cb.y + cb.height / 2 - arSize - 48;
            ar3.x = cb.x - arSize * 3;
            ar3.y = (int)(cb.y + cb.height / 2 - arSize  * 1.5 - 48);
            ar4.x = cb.x - arSize * 4;
            ar4.y = cb.y + cb.height / 2 - arSize * 2 - 48;
            ar5.x = cb.x - arSize * 5;
            ar5.y = (int)(cb.y + cb.height / 2 - arSize * 2.5 - 48);
        } else if(handler.getKeyManager().aRight)
        {
            ar2.width = arSize;
            ar2.height = arSize * 2;
            ar3.width = arSize;
            ar3.height = arSize * 3;
            ar4.width = arSize;
            ar4.height = arSize * 4;
            ar5.width = arSize;
            ar5.height = arSize * 5;
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2 - 48;
            ar2.x = cb.x + cb.width + arSize;
            ar2.y = cb.y + cb.height / 2 - arSize - 48;
            ar3.x = cb.x + cb.width + arSize * 2;
            ar3.y = (int)(cb.y + cb.height / 2 - arSize  * 1.5 - 48);
            ar4.x = cb.x + cb.width + arSize * 3;
            ar4.y = cb.y + cb.height / 2 - arSize * 2 - 48;
            ar5.x = cb.x + cb.width + arSize * 4;
            ar5.y = (int)(cb.y + cb.height / 2 - arSize * 2.5 - 48);
        } else {
            return;
        }

        attackTimer = 0;
        Rectangle[] ARray = {ar, ar2, ar3, ar4, ar5};

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
            for (int i = 0; i < ARray.length; i++) {
                if(e.getCollisionBounds(0,0).intersects(ARray[i]))
                {
                    e.hurt(5-i);
                    System.out.println("Attack rectangle " + (i + 1));
                    i = ARray.length;
                }
            }
        }
    }

    private void getInput()
    {


        xMove = 0;
        yMove = 0;
        if(inventory.isActive())
        {
            return;
        }

        if(handler.getKeyManager().up)
        {
            yMove = -speed;
        }
        if(handler.getKeyManager().down)
        {
            yMove = speed;
        }
        if(handler.getKeyManager().left)
        {
            xMove = -speed;
        }
        if(handler.getKeyManager().right)
        {
            xMove = speed;
        }
        if(handler.getKeyManager().esc)
        {
            handler.getGame().getDisplay().getFrame().setVisible(false);
            handler.getGame().getDisplay().getFrame().dispose();
            System.exit(0);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width, height * 2, null);

//        g.setColor(Color.red);
//        Rectangle cb = getCollisionBounds(0,0);
//        Rectangle ar = new Rectangle();
//        Rectangle ar2 = new Rectangle();
//        Rectangle ar3 = new Rectangle();
//        Rectangle ar4 = new Rectangle();
//        Rectangle ar5 = new Rectangle();
//        int arSize = handler.getWidth() / 40;
//        ar.width = arSize;
//        ar.height = arSize;
//
//        if(handler.getKeyManager().aUp)
//        {
//            ar2.width = arSize * 2;
//            ar2.height = arSize;
//            ar3.width = arSize * 3;
//            ar3.height = arSize;
//            ar4.width = arSize * 4;
//            ar4.height = arSize;
//            ar5.width = arSize * 5;
//            ar5.height = arSize;
//            ar.x = cb.x +cb.width / 2 - arSize / 2;
//            ar.y = cb.y - arSize;
//            ar2.x = cb.x + cb.width / 2 - arSize;
//            ar2.y = cb.y - arSize - ar.height;
//            ar3.x = (int) (cb.x + cb.width / 2 - arSize * 1.5);
//            ar3.y = cb.y - arSize - ar2.height * 2;
//            ar4.x = cb.x + cb.width / 2 - arSize * 2;
//            ar4.y = cb.y - arSize - ar3.height * 3;
//            ar5.x = (int) (cb.x + cb.width / 2 - arSize * 2.5);
//            ar5.y = cb.y - arSize - ar3.height * 4;
//        } else if(handler.getKeyManager().aDown)
//        {
//            ar2.width = arSize * 2;
//            ar2.height = arSize;
//            ar3.width = arSize * 3;
//            ar3.height = arSize;
//            ar4.width = arSize * 4;
//            ar4.height = arSize;
//            ar5.width = arSize * 5;
//            ar5.height = arSize;
//            ar.x = cb.x +cb.width / 2 - arSize / 2 + 20;
//            ar.y = cb.y + cb.height - 70;
//            ar2.x = cb.x + cb.width / 2 - arSize + 20;
//            ar2.y = cb.y + cb.height + arSize - 240;
//            ar3.x = (int) (cb.x + cb.width / 2 - arSize * 1.5 + 20);
//            ar3.y = cb.y + cb.height + arSize * 2 - 240;
//            ar4.x = cb.x + cb.width / 2 - arSize * 2 + 20;
//            ar4.y = cb.y + cb.height + arSize * 3 - 240;
//            ar5.x = (int) (cb.x + cb.width / 2 - arSize * 2.5 + 20);
//            ar5.y = cb.y + cb.height + arSize * 4 - 240;
//        } else if(handler.getKeyManager().aLeft)
//        {
//            ar2.width = arSize;
//            ar2.height = arSize * 2;
//            ar3.width = arSize;
//            ar3.height = arSize * 3;
//            ar4.width = arSize;
//            ar4.height = arSize * 4;
//            ar5.width = arSize;
//            ar5.height = arSize * 5;
//            ar.x = cb.x - arSize;
//            ar.y = cb.y + cb.height / 2 - arSize / 2 - 48;
//            ar2.x = cb.x - arSize * 2;
//            ar2.y = cb.y + cb.height / 2 - arSize - 48;
//            ar3.x = cb.x - arSize * 3;
//            ar3.y = (int)(cb.y + cb.height / 2 - arSize  * 1.5 - 48);
//            ar4.x = cb.x - arSize * 4;
//            ar4.y = cb.y + cb.height / 2 - arSize * 2 - 48;
//            ar5.x = cb.x - arSize * 5;
//            ar5.y = (int)(cb.y + cb.height / 2 - arSize * 2.5 - 48);
//        } else if(handler.getKeyManager().aRight)
//        {
//            ar2.width = arSize;
//            ar2.height = arSize * 2;
//            ar3.width = arSize;
//            ar3.height = arSize * 3;
//            ar4.width = arSize;
//            ar4.height = arSize * 4;
//            ar5.width = arSize;
//            ar5.height = arSize * 5;
//            ar.x = cb.x + cb.width;
//            ar.y = cb.y + cb.height / 2 - arSize / 2 - 48;
//            ar2.x = cb.x + cb.width + arSize;
//            ar2.y = cb.y + cb.height / 2 - arSize - 48;
//            ar3.x = cb.x + cb.width + arSize * 2;
//            ar3.y = (int)(cb.y + cb.height / 2 - arSize  * 1.5 - 48);
//            ar4.x = cb.x + cb.width + arSize * 3;
//            ar4.y = cb.y + cb.height / 2 - arSize * 2 - 48;
//            ar5.x = cb.x + cb.width + arSize * 4;
//            ar5.y = (int)(cb.y + cb.height / 2 - arSize * 2.5 - 48);
//        }
//        g.fillRect(ar.x, ar.y, ar.width,ar.height);
//        g.fillRect(ar2.x, ar2.y, ar2.width, ar2.height);
//        g.fillRect(ar3.x, ar3.y, ar3.width, ar3.height);
//        g.fillRect(ar4.x, ar4.y, ar4.width, ar4.height);
//        g.fillRect(ar5.x, ar5.y, ar5.width, ar5.height);
    }

    public void postRender(Graphics g)
    {
        inventory.render(g);
    }

    private BufferedImage getCurrentAnimationFrame()
    {
//        animationTimer += System.currentTimeMillis() - lastAnimationTimer;
//        lastAnimationTimer = System.currentTimeMillis();
//        if(animationTimer > animationCooldown) {
//            if(handler.getKeyManager().aUp)
//            {
//                animAttackUp.tick();
//                return animAttackUp.getCurrentFrame();
//            } else if (handler.getKeyManager().aDown) {
//                animAttackDown.tick();
//                return animAttackDown.getCurrentFrame();
//            } else if (handler.getKeyManager().aLeft) {
//                animAttackLeft.tick()
//                return animAttackLeft.getCurrentFrame();
//            } else if (handler.getKeyManager().aRight) {
//                animAttackRight.tick();
//                return animAttackRight.getCurrentFrame();
//            }
//        }

//        if(xMove < 0) //left
//        {
//
//        } else if(xMove > 0){ //right
//
//        } else if(yMove < 0){ //up
//
//        }else if(yMove < 0){ //down
//
//        }else{
        return animIdle.getCurrentFrame();
//        }

    }

    @Override
     public void die()
     {
         System.out.println("You've Been Eliminated");
     }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}