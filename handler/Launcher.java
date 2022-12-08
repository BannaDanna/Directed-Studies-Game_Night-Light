package handler;


import java.awt.*;

public class Launcher
{
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static void main(String[] args)
    {
        Game game = new Game("Night Light", screenSize.width, screenSize.height);
        game.start();
    }


}
