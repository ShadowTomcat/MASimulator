/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author Shadow
 */
public class GradientPanel extends JPanel {

    public static final Color DEFAULT_LIGHT = new Color(240, 240, 240);
    public static final Color DEFAULT_LIGHT2 = new Color(220, 220, 220);
    public static final Color DEFAULT_MEDIUM = new Color(160, 160, 160);
    private Color color1 = new Color(230, 230, 230);
    private Color color2 = new Color(230, 230, 230);
    private Integer direction = 0;
    private float point2 = 1f;

    public GradientPanel() {
        super();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isOpaque()) {
            return;
        }

        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gradientPaint;
        switch (direction) {
            case 0:
                gradientPaint = new GradientPaint(0, 0, color1, width / point2, height / point2, color2, false);
                break;
            case 1:
                gradientPaint = new GradientPaint(0, height, color1, width / point2, height / point2, color2, false);
                break;
            case 2:
                gradientPaint = new GradientPaint(width, 0, color1, width / point2, height / point2, color2, false);
                break;
            case 3:
                gradientPaint = new GradientPaint(width, height, color1, width / point2, height / point2, color2, false);
                break;
            default:
                gradientPaint = new GradientPaint(0, 0, color1, width / point2, height / point2, color2, false);
                break;
        }

        //GradientPaint gradientPaint = new GradientPaint(width, 0, color1, width / 2, height / 2, color2, false);
        g2.setPaint(gradientPaint);
        g2.fillRect(0, 0, width, height);

    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public void setPoint2(float point2) {
        this.point2 = point2;
    }

    public static void main(String args[]) {
        JFrame jf = new JFrame("GradientPanelTest");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GradientPanel gp = new GradientPanel();
        jf.getContentPane().add(gp);
        jf.setSize(500, 400);
        jf.setVisible(true);
    }

}
