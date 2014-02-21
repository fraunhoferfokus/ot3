package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciParameterType;
import org.etsi.ttcn.tci.Type;

public class TciParameterTypeImpl implements TciParameterType {
    
    private Type type;
    private int mode;
    
    public TciParameterTypeImpl(Type ty, int mo) {
        type = ty;
        mode = mo;
    }

    @Override
    public Type getParameterType() {
        return type;
    }

    @Override
    public int getParameterPassingMode() {
        return mode;
    }

}
