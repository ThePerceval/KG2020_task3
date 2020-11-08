package com.company.Function;

import java.util.List;

public class FunctionArgument_2 implements ITimeDependentParam {
    private List<Double> functionArguments;

    public FunctionArgument_2(List<Double> functionArguments) {
        this.functionArguments = functionArguments;
    }

    @Override
    public double getParamValue(double t) {
        double  a = functionArguments.get(0),
                b = functionArguments.get(1),
                c = functionArguments.get(2);
        return a * 1 / (1 + Math.pow(Math.E, -b * t)) + c;
    }
}
