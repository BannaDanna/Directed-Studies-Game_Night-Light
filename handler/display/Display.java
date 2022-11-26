package handler.display;

import javax.swing.*;
import java.awt.*;

public class Display
{

    private JFrame Frame;
    private Canvas canvas;
    private String Title;
    private int Width;
    private int Height;

    public Display(String Title, int Width, int Height)
    {
        this.Title = Title;
        this.Width = Width;
        this.Height = Height;

        createDisplay();
    }

    private void createDisplay()
    {
        Frame = new JFrame(Title);
        Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Frame.setSize(Width, Height);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setResizable(false);
        Frame.setLocationRelativeTo(null);
        Frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(Width, Height));
        canvas.setMaximumSize(new Dimension(Width, Height));
        canvas.setMinimumSize(new Dimension(Width, Height));
        canvas.setFocusable(false);

        Frame.add(canvas);
        Frame.pack();
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public JFrame getFrame()
    {
        return Frame;
    }
}
