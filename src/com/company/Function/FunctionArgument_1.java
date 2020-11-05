package com.company.Function;

import com.company.Function.IFunction;
import com.company.Function.ITimeDependentParam;

public class FunctionArgument_1 implements IFunction, ITimeDependentParam {

    @Override
    public double compute(double x) {
        return 0;
    }

    @Override
    public double getParamValue(double t) {
        return 0;
    }
}
