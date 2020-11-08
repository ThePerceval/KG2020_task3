package com.company.drawUtils;

import com.company.Function.IFunction;
import com.company.interfaceUtils.LineDrawer;
import com.company.screen.ScreenConverter;

public interface IFigure {
    void drawFunction(ScreenConverter sc, LineDrawer ld, IFunction function, double time);
}
