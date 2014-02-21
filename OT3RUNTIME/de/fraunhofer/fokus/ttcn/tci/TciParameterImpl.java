package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciParameter;
import org.etsi.ttcn.tci.Value;

public class TciParameterImpl implements TciParameter {
    
    private String nm;
    private int md;
    private Value par;

    @Override
    public String getParameterName() {
        return nm;
    }

    @Override
    public void setParameterName(String name) {
        nm = name;
    }

    @Override
    public int getParameterPassingMode() {
        return md;
    }

    @Override
    public void setParameterPassingMode(int mode) {
        md = mode;
    }

    @Override
    public Value getParameter() {
        return par;
    }

    @Override
    public void setParameter(Value parameter) {
        par = parameter;
    }

}
