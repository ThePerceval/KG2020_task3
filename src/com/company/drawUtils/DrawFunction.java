package com.company.drawUtils;

import com.company.Function.*;
import com.company.interfaceUtils.LineDrawer;
import com.company.screen.ScreenConverter;
import com.company.screen.ScreenPoint;

public class DrawFunction implements IFigure {
    @Override
    public void drawFunction(ScreenConverter sc, LineDrawer ld, IFunction function, double time) {
        double startX = sc.getxReal(),
                endX = sc.getxReal() + sc.getWidthReal(),
                step = sc.getWidthReal() / sc.getWidthScreen();
        double f1 = function.compute(startX, time);
        for (double x = startX + step; x < endX; x += step) {
            double f2 = function.compute(x, time);
            int x1 = (int)Math.round(sc.converterReal2ScreenX(x - step)),
                y1 = (int)Math.round(sc.converterReal2ScreenY(f1)),
                x2 = (int)Math.round(sc.converterReal2ScreenX(x)),
                y2 = (int)Math.round(sc.converterReal2ScreenY(f2));
                y1 = y1 < 0 ? -1 : Math.min(y1, sc.getHeightScreen() + 1);
                y2 = y2 < 0 ? -1 : Math.min(y2, sc.getHeightScreen() + 1);
            ld.drawLine(new ScreenPoint(x1, y1), new ScreenPoint(x2, y2));
            f1 = f2;
        }
    }
}