package com.company.drawUtils;

import com.company.interfaceUtils.LineDrawer;
import com.company.interfaceUtils.PixelDrawer;
import com.company.screen.ScreenPoint;

import java.awt.*;

public class DDALineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public DDALineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(ScreenPoint p1, ScreenPoint p2) {
        int x1 = p1.getX(), y1 = p1.getY(), x2 = p2.getX(), y2 = p2.getY();
        boolean step = Math.abs(x2 - x1) < Math.abs(y2 - y1);
        if(step){
            int tmp = x1; x1 = y1; y1 = tmp;
            tmp = y2; y2 = x2; x2 = tmp;
        }
        if(x1 > x2){
            int tmp = x1; x1 = x2; x2 = tmp;
            tmp = y1; y1 = y2; y2 = tmp;
        }


        double dx = x2 - x1, dy = Math.abs(y2 - y1);
        double k = dy / dx, y = y1;
        for (int x = x1; x < x2; x++) {
            pd.drawPixel(step ? (int) Math.round(y) : x, step ? x : (int) Math.round(y), Color.black);
            y = y + (y2 > y1 ? k : -k);
        }
    }
}
