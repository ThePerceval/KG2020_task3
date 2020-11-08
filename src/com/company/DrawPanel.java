package com.company;

import com.company.Function.IFunction;
import com.company.drawUtils.BufferedImagePixelDrawer;
import com.company.drawUtils.DDALineDrawer;
import com.company.drawUtils.DrawFunction;
import com.company.interfaceUtils.LineDrawer;
import com.company.screen.ScreenConverter;
import com.company.screen.ScreenPoint;
import com.company.utils.CameraController;
import com.company.primitive.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel implements CameraController.RepaintListener {
    private ScreenConverter sc = new ScreenConverter(-2,2, 4, 4, 800, 800);
    private CameraController camControl;
    private IFunction function = null;
    private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
    private double time = 0;

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
    public void paint(Graphics g) {
        sc.setWidthScreen(getWidth()); sc.setHeightScreen(getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = bi.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());

        LineDrawer ld = new DDALineDrawer(new BufferedImagePixelDrawer(bi));

        drawAxis(ld, gr);
        drawAll(ld);

        if(this.function != null)
            new DrawFunction().drawFunction(sc, ld, this.function, time);

        g.drawImage(bi, 0, 0, null);
        gr.dispose();
    }

    private void drawAll(LineDrawer ld){
        Line line = new Line(new RealPoint(-1, -1), new RealPoint(1, 1)) ;
        ld.drawLine(sc.converterReal2Screen(line.getPoint1()), sc.converterReal2Screen(line.getPoint2()));
    }

    private void drawAxis(LineDrawer ld, Graphics2D g){
        Line xAxis = new Line(sc.converterScreen2RealX(0), 0, sc.converterScreen2RealX(getWidth()),0);
        Line yAxis = new Line(0, sc.converterScreen2RealY(0), 0, sc.converterScreen2RealY(getWidth()));
        ld.drawLine(sc.converterReal2Screen(xAxis.getPoint1()), sc.converterReal2Screen(xAxis.getPoint2()));
        ld.drawLine(sc.converterReal2Screen(yAxis.getPoint1()), sc.converterReal2Screen(yAxis.getPoint2()));

        g.setStroke(new BasicStroke(2));
        g.setColor(Color.BLACK);

        int sizeX = (int)sc.getWidthReal() / 16;
        sizeX = sizeX > 0 ? sizeX : 1;
        for (int i = (int)sc.getxReal() / sizeX; i < (sc.getWidthReal() + sc.getxReal()) / sizeX; i++) {
            if(i == 0)
                continue;
            ld.drawLine(new ScreenPoint((int) Math.round(sc.converterReal2ScreenX(i * sizeX)),(int) Math.round(sc.converterReal2ScreenY(sc.getyReal()) + sc.converterReal2ScreenY(0)) + 5),
                        new ScreenPoint((int) Math.round(sc.converterReal2ScreenX(i * sizeX)),   (int) Math.round(sc.converterReal2ScreenY(sc.getyReal()) + sc.converterReal2ScreenY(0) - 5)));

            int width = g.getFontMetrics().stringWidth(String.valueOf(i * sizeX));
            g.drawString(String.valueOf(i * sizeX), Math.round(sc.converterReal2ScreenX(i * sizeX)) - (int)Math.round((double)width / 2),
                    (int)Math.round(sc.converterReal2ScreenY(sc.getyReal()) + sc.converterReal2ScreenY(0)) + 15);
        }

        int sizeY = (int)sc.getHeightReal() / 16;
        sizeY = sizeY > 0 ? sizeX : 1;
        for (int i = (int) sc.getyReal() / sizeY; i > (sc.getyReal() - sc.getHeightReal()) / sizeY; i--) {
            if(i == 0)
                continue;
            ld.drawLine(new ScreenPoint((int)Math.round(sc.converterReal2ScreenX(sc.getxReal()) + sc.converterReal2ScreenX(0)) + 5, (int)Math.round(sc.converterReal2ScreenY(i * sizeY))),
                        new ScreenPoint((int)Math.round(sc.converterReal2ScreenX(sc.getxReal()) + sc.converterReal2ScreenX(0)) - 5, (int)Math.round(sc.converterReal2ScreenY(i * sizeY))));

            int width = g.getFontMetrics().stringWidth(String.valueOf(i * sizeX));
            g.drawString(String.valueOf(i * sizeX), (int)Math.round(sc.converterReal2ScreenX(sc.getxReal()) + sc.converterReal2ScreenX(0)) - width - 5,
                    (int)Math.round(sc.converterReal2ScreenY(i * sizeY)) + font.getSize() / 2);
        }
    }


    @Override
    public void shouldRepaint() {
        repaint();
    }

    public void setFunction(IFunction function) {
        this.function = function;
        repaint();
    }

    public void drawTimeFunction(double t){
        this.time += t/ 1000;
        repaint();
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setCenterCoordinates(){
        sc.setxReal(-sc.getWidthReal() / 2);
        sc.setyReal(sc.getHeightReal() / 2);
        repaint();
    }
}