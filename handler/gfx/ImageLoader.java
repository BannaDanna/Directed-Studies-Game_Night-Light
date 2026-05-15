package handler.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageLoader {

    public static BufferedImage LoadImage(String path) {


        try {
            URL url = ImageLoader.class.getResource(path);
            System.out.println(url);
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
