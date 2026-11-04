package com.github.eng1c2g4.eng1c2g4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class checkMask {
    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("map_mask.png"));

        // Area: x, y, width, height
        int x = 50, y = 50, width = 32, height = 32;
        RedBlue z = checkArea(img, x, y, width, height);
        System.out.printf("r=%b, b=%b", z.hasRed(), z.hasBlue());
    }
    record RedBlue(boolean hasRed, boolean hasBlue) {}//creates a sip
    static RedBlue checkArea(BufferedImage img, int x, int y, int width, int height) {
        int[] pixalarray = img.getRGB(x, y, width, height, null, 0, width);
        int maxR = 0, maxB = 0;
        for (int p : pixalarray) {
            if (((p >>> 24) & 0xFF) == 0) continue;
            maxR = Math.max(maxR, (p >> 16) & 0xFF);
            maxB = Math.max(maxB, p & 0xFF);
            if (maxR == 255 && maxB == 255) break;
        }
        return new RedBlue(maxR > 0, maxB > 0);
    }
}
