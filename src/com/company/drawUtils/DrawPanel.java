package com.company.drawUtils;

import com.company.interfaceUtils.LineDrawer;
import com.company.screen.ScreenConverter;
import com.company.screen.ScreenPoint;
import com.company.utils.CameraController;
import com.company.primitive.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class DrawPanel extends JPanel implements CameraController.RepaintListener {
    private ScreenConverter sc= new ScreenConverter(-2,2 , 4, 4, 800, 600);;
    private CameraController camControl;
    private Line line = new Line(-1, 0, 1, 0);
    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = bi.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.dispose();

        LineDrawer ld = new DDALineDrawer(new bufferedImagePixelDrawer(bi));
        ld.drawLine(new ScreenPoint(300, 300), new ScreenPoint(700, 700));


        g.drawImage(bi, 0, 0, null);
    }

    public DrawPanel(){
        camControl = new CameraController(sc);
        sc.setWidthScreen(getWidth()); sc.setHeightScreen(getHeight());
        this.setPreferredSize(new Dimension(800, 800));
        this.setMinimumSize(new Dimension(800, 800));
        this.setMaximumSize(new Dimension(800, 800));

    }

    @Override
    public void shouldRepaint() {
        repaint();
    }
}
