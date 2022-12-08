package handler.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class UIImageButton extends UIObject{

    private BufferedImage[] images;
    private ClickListener clicker;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.clicker = clicker;
        this.images = images;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        if(hovering)
        {
            g.drawImage(images[1], (int) x, (int) y, width, height, null);
        } else {
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void onClick() {
        clicker.onCLick();
    }

    public ClickListener getClicker() {
        return clicker;
    }

    public void setClicker(ClickListener clicker) {
        this.clicker = clicker;
    }
}
