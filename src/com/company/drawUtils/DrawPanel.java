package com.company.drawUtils;

import com.company.interfaceUtils.LineDrawer;
import com.company.interfaceUtils.PixelDrawer;
import com.company.screen.ScreenConverter;
import com.company.screen.ScreenPoint;
import com.company.utils.CameraController;
import com.company.primitive.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class DrawPanel extends JPanel implements CameraController.RepaintListener {
    private ScreenConverter sc = new ScreenConverter(-2,2, 4, 4, 800, 800);;
    private CameraController camControl;
    private DrawFunction function;

    @Override
    public void paint(Graphics g) {
        sc.setWidthScreen(getWidth()); sc.setHeightScreen(getHeight());

        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = bi.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.dispose();

        PixelDrawer pd = new BufferedImagePixelDrawer(bi);
        LineDrawer ld = new DDALineDrawer(new BufferedImagePixelDrawer(bi));

        //function.drawFunction(sc, pd,ld);


        ld.drawLine(new ScreenPoint(700, 700), new ScreenPoint(400, 400));

        Line line = new Line(1, 1, 2, -2);
        ld.drawLine(sc.converterReal2Screen(line.getPoint1()), sc.converterReal2Screen(line.getPoint2()));


        g.drawImage(bi, 0, 0, null);
    }

    public DrawPanel(){
        camControl = new CameraController(sc);
        camControl.addRepaintListener(this);
        this.addMouseListener(camControl);
        this.addMouseMotionListener(camControl);
        this.addMouseWheelListener(camControl);

        sc.setWidthScreen(getWidth()); sc.setHeightScreen(getHeight());
        this.setPreferredSize(new Dimension(800, 800));
        this.setMinimumSize(new Dimension(800, 800));
        this.setMaximumSize(new Dimension(800, 800));

    }

    @Override
    public void shouldRepaint() {
        repaint();
    }

    public void setFunction(DrawFunction function) {
        this.function = function;
    }
}
