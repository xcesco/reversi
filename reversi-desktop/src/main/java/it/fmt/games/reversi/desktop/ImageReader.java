package it.fmt.games.reversi.desktop;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ImageReader {

    public BufferedImage readImage (String fileName) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = Objects.requireNonNull(classLoader.getResource(fileName)).getPath();
        BufferedImage image = ImageIO.read(new File(path));
        return image;
    }
    public BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
