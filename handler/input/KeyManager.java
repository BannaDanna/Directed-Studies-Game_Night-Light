package handler.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;
    public boolean up, down, left, right, run;
    public boolean aUp, aDown, aLeft, aRight;
    public boolean esc;

    public KeyManager()
    {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void tick(){
        for (int i = 0; i < keys.length; i++)
        {
            if(cantPress[i] && !keys[i])
            {
                cantPress[i] = false;
            } else if(justPressed[i])
            {
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if(!cantPress[i] && keys[i])
            {
                justPressed[i] = true;
            }
        }

        if(keyJustPressed(KeyEvent.VK_E))
        {
            System.out.println("E just pressed");
        }

        up = keys[KeyEvent.VK_W];
        left = keys[KeyEvent.VK_A];
        down = keys[KeyEvent.VK_S];
        right = keys[KeyEvent.VK_D];
        run = keys[KeyEvent.VK_SHIFT];

        aUp = keys[KeyEvent.VK_UP];
        aLeft = keys[KeyEvent.VK_LEFT];
        aRight = keys[KeyEvent.VK_RIGHT];
        aDown = keys[KeyEvent.VK_DOWN];

        esc = keys[KeyEvent.VK_ESCAPE];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
        {
            return;
        }
        keys[e.getKeyCode()] = true;
//        System.out.println("pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
        {
            return;
        }
        keys[e.getKeyCode()] = false;
    }

    public boolean keyJustPressed(int keyCode)
    {
        if(keyCode < 0 || keyCode >= keys.length)
        {
            return false;
        }

        return justPressed[keyCode];
    }

    public boolean anyKeyJustPressed()
    {
        if(justPressed.length != 0)
        {
            return true;
        }
        return false;
    }
}
