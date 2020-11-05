package com.company.utils;

import com.company.screen.ScreenConverter;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CameraController implements MouseListener, MouseMotionListener, MouseWheelListener {

    private List<RepaintListener> listeners = new ArrayList<RepaintListener>();

    public interface RepaintListener {
        void shouldRepaint();
    }

    public void addRepaintListener(RepaintListener repaintListener){
        listeners.add(repaintListener);
    }

    public void removeRepaintListener(RepaintListener repaintListener){
        listeners.remove(repaintListener);
    }

    private void onRepaint(){
        for(RepaintListener listener : listeners){
            listener.shouldRepaint();
        }
    }

    private ScreenConverter sc;

    public CameraController(ScreenConverter sc) {
        this.sc = sc;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int clicks = e.getWheelRotation();
        double scale = 1;
        double step = (clicks < 0) ? 0.9 : 1.1;
        for (int i = Math.abs(clicks); i > 0; i--) {
            scale = scale * step;
        }
        sc.setWidthReal(sc.getWidthReal() * scale);
        sc.setHeightReal(sc.getHeightReal() * scale);
        onRepaint();
    }
}
