/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocessing;

import java.io.File;
import java.io.IOException;
import util.ImageUtil;

/**
 *
 * @author Shadow
 */
public class ImageCompressor {

    public static void main(String[] args) throws IOException {
        convertImages();
    }

    private static void convertImages() throws IOException {
        File dir = new File("imgBig");
        File[] imgList = dir.listFiles();
        int i = 0;
        for (File img : imgList) {
            ImageUtil.resizeByWidth(img, new File("img/" + img.getName()), 64, 1.0f);
            i++;
            if (i % 50 == 0) {
                System.out.println("File done: " + i);
            }
        }
    }
}
