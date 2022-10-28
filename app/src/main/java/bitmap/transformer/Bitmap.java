package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bitmap {
    public void transform() throws IOException {

        int width = 100;
        int height = 100;
        BufferedImage outPutImg = null;
        BufferedImage inPutImg = null;
        File bitFile = null;

        try {
            bitFile = new File("app/src/main/resources/baldy-8bit.bmp");
            outPutImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            inPutImg = ImageIO.read(bitFile);
            System.out.println("read");
        } catch (IOException e) {
            System.out.println("error:" + e);
        }
        try {
            bitFile = new File("app/src/main/resources/transform.bmp");
            ImageIO.write(inPutImg, "bmp", bitFile);
            System.out.println("image written");
        } catch (IOException error) {
            System.out.println("error" + error);
        }
        changeColor(width, height, inPutImg, outPutImg);
        randomizeImage(width, height, outPutImg);


    }

    public void changeColor(int width, int height, BufferedImage inPutImg, BufferedImage outPutImg) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color inputColor = new Color(inPutImg.getRGB(i, j));
                int blue = inputColor.getBlue();
                int red = inputColor.getRed();
                int green = inputColor.getGreen();
                int grayScale = (red + green + blue)/3;
                Color newColor = new Color(
                        grayScale,
                        grayScale,
                        grayScale
                );
                outPutImg.setRGB(j, i, newColor.getRGB());
            }
        }
    }

    public void randomizeImage(int width, int height, BufferedImage outPutImg) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int a = (int) (Math.random() * 256);
                int r = (int) (Math.random() * 256);
                int g = (int) (Math.random() * 256);
                int b = (int) (Math.random() * 256);
                int pixel = (a<<24) |(r<<16) | (g<<8) | b;
                outPutImg.setRGB(j,i, pixel);
            }

        }
    }
}

