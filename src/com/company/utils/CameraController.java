package com.company.utils;

import com.company.RealPoint;
import com.company.primitive.Line;
import com.company.screen.ScreenConverter;
import com.company.screen.ScreenPoint;

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

    private ScreenPoint prevPosition = null;

    private Line currentNewLine = null;



    public CameraController(ScreenConverter sc) {
        this.sc = sc;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3){
            prevPosition = new ScreenPoint(e.getX(), e.getY());
            onRepaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ScreenPoint currentPosition = new ScreenPoint(e.getX(), e.getY());
        if(prevPosition != null){
            ScreenPoint delta = new ScreenPoint(
                    -currentPosition.getX() + prevPosition.getX(),
                    -currentPosition.getY() + prevPosition.getY());
            RealPoint deltaReal = sc.converterScreen2Real(delta);

            sc.setxReal(deltaReal.getX());
            sc.setyReal(deltaReal.getY());
            prevPosition = currentPosition;
        }
        if(currentNewLine != null){
            currentNewLine.setPoint2(sc.converterScreen2Real(currentPosition));
        }
        onRepaint();
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
        sc.setxReal(sc.getxReal() * scale);
        sc.setyReal(sc.getyReal() * scale);
        sc.setWidthReal(sc.getWidthReal() * scale);
        sc.setHeightReal(sc.getHeightReal() * scale);
        onRepaint();
    }
}
