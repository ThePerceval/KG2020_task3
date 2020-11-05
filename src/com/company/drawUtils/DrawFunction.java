package com.company.drawUtils;

import com.company.interfaceUtils.LineDrawer;
import com.company.interfaceUtils.PixelDrawer;
import com.company.screen.ScreenConverter;

public class DrawFunction implements IFigure {
    private ScreenConverter sc;
    private PixelDrawer pd;
    private LineDrawer ld;

    @Override
    public void drawFunction(ScreenConverter sc, PixelDrawer pd, LineDrawer ld) {
        this.sc = sc;
        this.pd = pd;
        this.ld = ld;
    }

    private void draw() {

    }
}