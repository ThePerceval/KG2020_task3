package com.company.drawUtils;

import com.company.interfaceUtils.LineDrawer;
import com.company.interfaceUtils.PixelDrawer;
import com.company.screen.ScreenConverter;

public interface IFigure {
    void drawFunction(ScreenConverter sc, PixelDrawer pd, LineDrawer ld);
}
