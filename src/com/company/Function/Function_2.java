package com.company.Function;

import java.util.List;

public class Function_2 implements IFunction {
    private List<ITimeDependentParam> functionArguments;

    public Function_2(List<ITimeDependentParam> functionArguments) {
        this.functionArguments = functionArguments;
    }

    @Override
    public double compute(double x, double t) {
        double A = functionArguments.get(0).getParamValue(t);
        double B = functionArguments.get(1).getParamValue(t);
        double C = functionArguments.get(2).getParamValue(t);
        double W = functionArguments.get(3).getParamValue(t);
        return A * Math.sin(B * x + W) + C;
    }
}
