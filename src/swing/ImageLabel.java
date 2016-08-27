/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

/**
 *
 * @author sunsc
 */
public class ImageLabel extends javax.swing.JLabel {

    public ImageLabel() {
    }
    private static final long serialVersionUID = 1L;
    private Image image;
    private int imgWidth;
    private int imgHeight;
    private final boolean isAutoResize = true;
    private static final Logger LOG = Logger.getLogger(ImageLabel.class.getName());

    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public void clear() {
        image = null;
        imgWidth = 0;
        imgHeight = 0;
        this.removeAll();

//        this.getParent().getParent().getParent().validate();
//        this.getParent().getParent().getParent().repaint();
    }

    public void setImagePath(String imgPath) {
        try {
            image = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            LOG.error(e);
        }
        if (image != null) {
            setImgWidth(image.getWidth(this));
            setImgHeight(image.getHeight(this));
        }
    }

    public void setImage(Image img) {
        image = img;
        if (image != null) {
            setImgWidth(image.getWidth(this));
            setImgHeight(image.getHeight(this));
        }
    }

    @Override
    public void paintComponent(Graphics g1) {
        int x = 0;
        int y = 0;
        int width;
        int height;
        Graphics g = (Graphics) g1;
        if (null == image) {
            return;
        }
        if (isAutoResize) {
            width = this.getWidth();
            height = this.getHeight();
            double imgRatio = ((double) getImgWidth()) / ((double) getImgHeight());
            double panelRatio = ((double) width / ((double) height));
            if (panelRatio > imgRatio) {
                width = (int) (height * imgRatio);
            } else {
                height = (int) (width / imgRatio);
            }
        } else {
            width = getImgWidth();
            height = getImgHeight();
        }

        g.drawImage(image, x, y, width, height, this);
        g = null;
    }
}
