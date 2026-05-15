package handler.gfx;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

    public static Font loadFont(String path, float size)
    {
        try {
            InputStream is = FontLoader.class.getResourceAsStream(path);

            if (is == null)
            {
                System.out.println("Font resource not found: " + path);
                        return null;
            }

            return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
        } catch (FontFormatException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
