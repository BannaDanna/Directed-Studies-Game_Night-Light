package handler.entities.creatures;

import handler.HUD.HUD;
import handler.Handler;
import handler.entities.Entity;
import handler.gfx.Animation;
import handler.gfx.Assets;
import handler.inventory.Inventory;
import handler.items.Item;
import handler.pause.PauseMenu;
import handler.sounds.SoundManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Gabi extends Creature{

//animations

    private Animation[] movingAttacksLeft = new Animation[4], movingAttacksRight = new Animation[4], movingAttacksUp = new Animation[4], movingAttacksDown = new Animation[4];
    private Animation animIdle, animMoveLeft, animMoveRight, animMoveUp, animMoveDown, animAttackLeft, animAttackRight, animAttackUp, animAttackDown, lastAnimation, movingAttacksLeftUp, movingAttacksLeftDown, movingAttacksLeftLeft, movingAttacksLeftRight, movingAttacksRightUp, movingAttacksRightDown, movingAttacksRightLeft, movingAttacksRightRight, movingAttacksUpUp, movingAttacksUpDown, movingAttacksUpLeft, movingAttacksUpRight, movingAttacksDownUp, movingAttacksDownDown, movingAttacksDownLeft, movingAttacksDownRight;
    //attack, attack animation, and stamina regen timer & delay
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
    private long lastAnimationTimer, animationCooldown = 800, animationTimer = animationCooldown;
    private long lastStamRegen, stamRegenCooldown = 150, stamRegenTimer = stamRegenCooldown;

    private long lastStamTick, stamTickCooldown = 200, stamTickTimer = stamTickCooldown;

    private long lastDelayTimer, delayCooldown = 2500, delayTimer = delayCooldown;
    //inventory
    private Inventory inventory;

    private boolean controllerConnected = handler.getControllerManager().connected;

//    pause menu
    private PauseMenu pauseMenu;

    //HUD
    private HUD hud;
    //soundManager
    private SoundManager audioManager;
    //stamina
    private int stamina;
    private boolean running, tired = false;

    public Gabi(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        audioManager = new SoundManager();
        stamina = 100;

        bounds.x = handler.getWidth() / 64;
        bounds.y = (int)(handler.getHeight() / 9.6);
        bounds.height = (int)(handler.getWidth() / 7);
        bounds.width = (int) (handler.getHeight() / 9.5555);

        //Animations
        animIdle = new Animation(500, Assets.player_idle);
        animMoveLeft = new Animation(500, Assets.player_left);
        animMoveRight = new Animation(500, Assets.player_right);
        animMoveUp = new Animation(500, Assets.player_up);
        animMoveDown = new Animation(500, Assets.player_down);
        animAttackLeft = new Animation(100, Assets.player_attack_left);
        animAttackRight = new Animation(100, Assets.player_attack_right);
        animAttackUp = new Animation(100, Assets.player_attack_up);
        animAttackDown = new Animation(100, Assets.player_attack_down);
        movingAttacksUpUp = new Animation(100, Assets.player_attack_down);
        movingAttacksUpDown = new Animation(100, Assets.player_attack_down);
        movingAttacksUpLeft = new Animation(100, Assets.player_attack_down);
        movingAttacksUpRight = new Animation(100, Assets.player_attack_down);
        movingAttacksDownUp = new Animation(100, Assets.player_attack_down);
        movingAttacksDownDown = new Animation(100, Assets.player_attack_down);
        movingAttacksDownLeft = new Animation(100, Assets.player_attack_down);
        movingAttacksDownRight = new Animation(100, Assets.player_attack_down);
        movingAttacksLeftUp = new Animation(100, Assets.player_attack_down);
        movingAttacksLeftDown = new Animation(100, Assets.player_attack_down);
        movingAttacksLeftLeft = new Animation(100, Assets.player_attack_down);
        movingAttacksLeftRight = new Animation(100, Assets.player_attack_down);
        movingAttacksRightUp = new Animation(100, Assets.player_attack_down);
        movingAttacksRightDown = new Animation(100, Assets.player_attack_down);
        movingAttacksRightLeft = new Animation(100, Assets.player_attack_down);
        movingAttacksRightRight = new Animation(100, Assets.player_attack_down);
        movingAttacksDown[0] = movingAttacksDownUp;
        movingAttacksDown[1] = movingAttacksDownDown;
        movingAttacksDown[2] = movingAttacksDownLeft;
        movingAttacksDown[3] = movingAttacksDownRight;
        movingAttacksUp[0] = movingAttacksUpUp;
        movingAttacksUp[1] = movingAttacksUpDown;
        movingAttacksUp[2] = movingAttacksUpLeft;
        movingAttacksUp[3] = movingAttacksUpRight;
        movingAttacksLeft[0] = movingAttacksLeftUp;
        movingAttacksLeft[1] = movingAttacksLeftDown;
        movingAttacksLeft[2] = movingAttacksLeftLeft;
        movingAttacksLeft[3] = movingAttacksLeftRight;
        movingAttacksRight[0] = movingAttacksRightUp;
        movingAttacksRight[1] = movingAttacksRightDown;
        movingAttacksRight[2] = movingAttacksRightLeft;
        movingAttacksRight[3] = movingAttacksRightRight;

        hud = new HUD(handler);
        pauseMenu = new PauseMenu(handler);
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
      animMoveLeft.tick();
      animMoveRight.tick();
      animMoveUp.tick();
      animMoveDown.tick();
      animAttackLeft.tick();
      animAttackRight.tick();
      animAttackUp.tick();
      animAttackDown.tick();
        movingAttacksUpUp.tick();
        movingAttacksUpDown.tick();
        movingAttacksUpRight.tick();
        movingAttacksUpLeft.tick();
        movingAttacksRightRight.tick();
        movingAttacksRightLeft.tick();
        movingAttacksRightDown.tick();
        movingAttacksRightUp.tick();
        movingAttacksLeftRight.tick();
        movingAttacksLeftLeft.tick();
        movingAttacksLeftUp.tick();
        movingAttacksLeftDown.tick();
        movingAttacksDownRight.tick();
        movingAttacksDownLeft.tick();
        movingAttacksDownUp.tick();
        movingAttacksDownDown.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        //attack
        checkAttacks();
        //Inventory
        inventory.tick();
        //hud
        hud.tick();
        //pause menu
        pauseMenu.tick();

//        System.out.println(Couch.class.getName());


        stamRegenTimer += System.currentTimeMillis() - lastStamRegen;
        lastStamRegen = System.currentTimeMillis();
        delayTimer += System.currentTimeMillis() - lastDelayTimer;
        lastDelayTimer = System.currentTimeMillis();
        if(stamRegenTimer >= stamRegenCooldown && !running) {
            if(stamina < 0)
            {
                stamina = 0;
            }
            if(stamina >= 50 && tired)
            {
                tired = false;
            }
            if(stamina == 0)
            {
                tired = true;
            }
            if(delayTimer >= delayCooldown)
            {
                stamina += 1;
            }
            if(stamina > 100)
            {
                stamina = 100;
            }
            stamRegenTimer = 0;
        }


    }

    private void checkAttacks()
    {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown) {
            return;
        }

        if(inventory.isActive() || pauseMenu.isActive())
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

        if(handler.getKeyManager().aUp || handler.getControllerManager().yAttack > 0.15)
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
        } else if(handler.getKeyManager().aDown || handler.getControllerManager().yAttack < -0.15)
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
        } else if(handler.getKeyManager().aLeft || handler.getControllerManager().xAttack < -0.15)
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
        } else if(handler.getKeyManager().aRight || handler.getControllerManager().xAttack > 0.15)
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
        if(inventory.isActive() || pauseMenu.isActive())
        {
            return;
        }
        if(((handler.getKeyManager().run || handler.getControllerManager().lJoystick)&& !tired) && stamina > 0)
        {
            running = true;
            speed = (float) (DEFAULT_SPEED * 1.40);
            animMoveLeft.setSpeed((int) (animMoveLeft.getSpeed() * 1.4));
            animMoveUp.setSpeed((int) (animMoveUp.getSpeed() * 1.4));
            animMoveDown.setSpeed((int) (animMoveDown.getSpeed() * 1.4));
            animMoveRight.setSpeed((int) (animMoveRight.getSpeed() * 1.4));
        } else {
            running = false;
            speed = DEFAULT_SPEED;
            animMoveLeft.setSpeed(500);
            animMoveUp.setSpeed(500);
            animMoveDown.setSpeed(500);
            animMoveRight.setSpeed(500);
        }
        //normalize diagonal vectors
        if(((handler.getKeyManager().right || handler.getKeyManager().left) && (handler.getKeyManager().up || handler.getKeyManager().down)) || (Math.abs(handler.getControllerManager().yMovement) > 0.15 && Math.abs(handler.getControllerManager().xMovement) > 0.15))
        {
            speed = (float) (Math.sqrt(Math.pow(speed, 2) / 2));
        }

        stamTickTimer += System.currentTimeMillis() - lastStamTick;
        lastStamTick = System.currentTimeMillis();


        if(handler.getKeyManager().up || handler.getControllerManager().yMovement > 0.15)
        {
            if(controllerConnected)
            {
                yMove = -speed * handler.getControllerManager().yMovement;
            } else {
                yMove = -speed;
            }
            if(running && stamTickTimer >= stamTickCooldown)
            {
                stamina -= 2;
                stamTickTimer = 0;
                delayTimer = 0;
            }
        }
        if(handler.getKeyManager().down || handler.getControllerManager().yMovement < -0.15)
        {
            if(controllerConnected)
            {
                yMove = speed * -handler.getControllerManager().yMovement;
            } else {
                yMove = speed;
            }
            if(running && stamTickTimer >= stamTickCooldown)
            {
                stamina -= 2;
                stamTickTimer = 0;
                delayTimer = 0;
            }
        }
        if(handler.getKeyManager().left || handler.getControllerManager().xMovement < -0.15)
        {
            if(controllerConnected)
            {
                xMove = speed * handler.getControllerManager().xMovement;
            } else {
                xMove = -speed;
            }
            if(running && stamTickTimer >= stamTickCooldown)
            {
                stamina -= 2;
                stamTickTimer = 0;
                delayTimer = 0;
            }
        }
        if(handler.getKeyManager().right || handler.getControllerManager().xMovement > 0.15)
        {
            if(controllerConnected)
            {
                xMove = speed * handler.getControllerManager().xMovement;
            } else {
                xMove = speed;
            }
            if(running && stamTickTimer >= stamTickCooldown)
            {
                stamina -= 2;
                stamTickTimer = 0;
                delayTimer = 0;
            }
        }
        System.out.println(speed);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width, height * 2, null);
        g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
// attack area debug
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
        hud.render(g);
        inventory.render(g);
        pauseMenu.render(g);
    }

    private BufferedImage getCurrentAnimationFrame()
    {
        animationTimer += System.currentTimeMillis() - lastAnimationTimer;
        lastAnimationTimer = System.currentTimeMillis();

        if(animationTimer > animationCooldown) {
            if(handler.getKeyManager().aUp || handler.getControllerManager().yAttack > 0.15)
            {
                animationTimer = 0;
                if(xMove != 0 || yMove != 0)
                {
                    lastAnimation = checkMovingAttack(movingAttacksUp);
                    lastAnimation.setCurrentFrame(0);
                    return lastAnimation.getCurrentFrame();
                }
                lastAnimation = animAttackUp;
                lastAnimation.setCurrentFrame(0);
                return animAttackUp.getCurrentFrame();
            } else if (handler.getKeyManager().aDown || handler.getControllerManager().yAttack < -0.15) {
                animationTimer = 0;
                if(xMove != 0 || yMove != 0)
                {
                    lastAnimation = checkMovingAttack(movingAttacksDown);
                    lastAnimation.setCurrentFrame(0);
                    return lastAnimation.getCurrentFrame();
                }
                lastAnimation = animAttackDown;
                lastAnimation.setCurrentFrame(0);
                return animAttackDown.getCurrentFrame();
            } else if (handler.getKeyManager().aLeft || handler.getControllerManager().xAttack < -0.15) {
                animationTimer = 0;
                if(xMove != 0 || yMove != 0)
                {
                    lastAnimation = checkMovingAttack(movingAttacksLeft);
                    lastAnimation.setCurrentFrame(0);
                    return lastAnimation.getCurrentFrame();
                }
                lastAnimation = animAttackLeft;
                lastAnimation.setCurrentFrame(0);                return animAttackLeft.getCurrentFrame();
            } else if (handler.getKeyManager().aRight || handler.getControllerManager().xAttack > 0.15) {
                animationTimer = 0;
                if(xMove != 0 || yMove != 0)
                {
                    lastAnimation = checkMovingAttack(movingAttacksRight);
                    lastAnimation.setCurrentFrame(0);
                    return lastAnimation.getCurrentFrame();
                }
                lastAnimation = animAttackRight;
                lastAnimation.setCurrentFrame(0);
                return animAttackRight.getCurrentFrame();
            }

        if(xMove < 0) //left
        {
            lastAnimation = animMoveLeft;
            return animMoveLeft.getCurrentFrame();
        } else if(xMove > 0){ //right
            lastAnimation = animMoveRight;
            return animMoveRight.getCurrentFrame();
        } else if(yMove < 0){ //up
            lastAnimation = animMoveUp;
            return animMoveUp.getCurrentFrame();
        }else if(yMove > 0){ //down
            lastAnimation = animMoveDown;
            return animMoveDown.getCurrentFrame();
        }else{ //idle
            lastAnimation = animIdle;
            return animIdle.getCurrentFrame();
        }
        }
   return lastAnimation.getCurrentFrame();
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

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    private Animation checkMovingAttack(Animation[] attackDir)
    {
        int up = 0;
        int down = 1;
        int left = 2;
        int right = 3;
        if(xMove < 0)
        {
            return attackDir[left];
        }
        if (xMove > 0) {
            return attackDir[right];
        }
        if (yMove < 0) {
            return attackDir[up];
        }
        if (yMove > 0) {
            return attackDir[down];
        }
        return null;
    }

    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }
}