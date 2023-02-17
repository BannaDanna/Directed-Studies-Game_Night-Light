package handler.gfx;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Animation {

    private int speed, index;
    private BufferedImage[] frames;
    private long lastTime,timer;

    public Animation(int speed, BufferedImage[] frames)
    {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick()
    {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed)
        {
            index++;
            timer = 0;
            if(index >= frames.length)
            {
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame()
    {
        return frames[index];
    }

    public void setCurrentFrame(int index)
    {
        this.index = index;
    }
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public int getSpeed()
    {
        return speed;
    }
}
