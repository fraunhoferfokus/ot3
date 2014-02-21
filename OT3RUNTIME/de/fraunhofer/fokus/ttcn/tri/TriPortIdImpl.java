package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.TriComponentId;
import org.etsi.ttcn.tri.TriPortId;

public class TriPortIdImpl implements TriPortId {
    
    private String portName;
    private String portTypeName;
    private TriComponentId component;
    private boolean isarray;
    private int index;

    public TriPortIdImpl(String pname, String tname, TriComponentId comp, boolean arr, int ind) {
        portName = pname;
        portTypeName = tname;
        component = comp;
        isarray = arr;
        index = ind;
    }
    @Override
    public String getPortName() {
        return portName;
    }

    @Override
    public String getPortTypeName() {
        return portTypeName;
    }

    @Override
    public TriComponentId getComponent() {
        return component;
    }

    @Override
    public boolean isArray() {
        return isarray;
    }

    @Override
    public int getPortIndex() {
        return index;
    }

}
