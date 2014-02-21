package de.fraunhofer.fokus.ttcn.tri;

import org.etsi.ttcn.tri.TriFunctionId;

public class TriFunctionIdImpl implements TriFunctionId {
    
    private String name;
    
    TriFunctionIdImpl(String nm) {
        name = nm;
    }

    @Override
    public String getFunctionName() {
        return name;
    }

    @Override
    public boolean equals(TriFunctionId fun) {
        return name == fun.getFunctionName();
    }

}
