package com.company.Function;

import java.util.List;

public class FunctionArgument_1 implements ITimeDependentParam {
    private List<Double> functionArguments;

    public FunctionArgument_1(List<Double> functionArguments) {
        this.functionArguments = functionArguments;
    }

    @Override
    public double getParamValue(double t) {
        double  a = functionArguments.get(0),
                b = functionArguments.get(1),
                c = functionArguments.get(2),
                fi = functionArguments.get(3);
        return a * Math.sin(b * t + fi) + c;
    }
}