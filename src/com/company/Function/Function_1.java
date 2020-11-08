package com.company.Function;

import java.util.List;

public class Function_1 implements IFunction {
    private List<ITimeDependentParam> functionArguments;

    public Function_1(List<ITimeDependentParam> functionArguments) {
        this.functionArguments = functionArguments;
    }

    @Override
    public double compute(double x, double t) {
        double A = functionArguments.get(0).getParamValue(t);
        double B = functionArguments.get(1).getParamValue(t);
        double C = functionArguments.get(2).getParamValue(t);
        return A * Math.pow(x, 2) + B * x + C;
    }
}