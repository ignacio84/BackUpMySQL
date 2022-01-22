package com.gff.util;

import static com.gff.util.Enviroment.PATH_IMG;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class LoadImages {

    InputStream imgStream;

    public ImageIcon loadImg(String imgName, Integer pixel) {
        imgStream = getClass().getResourceAsStream(PATH_IMG + imgName + pixel + "x" + pixel + ".png");
        return this.getImageIcon();
    }

    public ImageIcon loadImg(String imgName) {
        imgStream = getClass().getResourceAsStream(PATH_IMG + imgName + ".png");
        return this.getImageIcon();
    }

    public BufferedImage loadBufferImage(String imgName) {
        imgStream = getClass().getResourceAsStream(PATH_IMG + imgName);
        return this.getBufferImage();
    }

    private ImageIcon getImageIcon() {
        BufferedImage ingBuffered = null;
        ImageIcon img = null;
        try {
            ingBuffered = ImageIO.read(imgStream);
            if (ingBuffered != null) {
                img = new ImageIcon(ingBuffered);
            }
        } catch (Exception ex) {
            System.out.println("Error al cargar imagen :" + ex.getMessage());
        }
        return img;
    }

    public BufferedImage getBufferImage() {
        BufferedImage ingBuffered = null;
        try {
            ingBuffered = ImageIO.read(imgStream);
        } catch (Exception ex) {
            System.out.println("Error al cargar imagen :" + ex.getMessage());
        }
        return ingBuffered;
    }

}
