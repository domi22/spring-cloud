package com.swetake.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Test2Vma {
    public static void main(String[] args) throws Exception {
        Qrcode x = new Qrcode();
        x.setQrcodeErrorCorrect('M');
        x.setQrcodeEncodeMode('B');
        x.setQrcodeVersion(7);
        String qrDate = "https://www.cnblogs.com/domi22/p/8718372.html";
        int width = 67 + 12 * (7 - 1);
        int heigth = 67 + 12 * (7 - 1);

        BufferedImage bufferedImage = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);
        Graphics2D gs = bufferedImage.createGraphics();
        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        gs.clearRect(0, 0, width, heigth);

        int pixoff = 2; //偏移量
        byte[] d = qrDate.getBytes("gb2312");
        if (d.length > 0 && d.length < 120) {
            boolean[][] s = x.calQrcode(d);
            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    if (s[j][i]) {
                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff,3,3);
                    }
                }
            }
        }

        gs.dispose();
        bufferedImage.flush();
        boolean png = ImageIO.write(bufferedImage, "png", new File("D:/WorkSpace/qrcode.png"));

    }

}
