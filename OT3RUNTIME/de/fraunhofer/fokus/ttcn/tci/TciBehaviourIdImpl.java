package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciBehaviourId;
import org.etsi.ttcn.tci.TciParameterTypeList;

public class TciBehaviourIdImpl extends QualifiedNameImpl implements TciBehaviourId {
    
    private TciParameterTypeList typeParameters;
    
    public TciBehaviourIdImpl(String moduleName, String baseName) {
        super(moduleName, baseName);
        typeParameters = null;
    }
    
    public TciBehaviourIdImpl(String moduleName, String baseName, TciParameterTypeList parms) {
        super(moduleName, baseName);
        typeParameters = parms;
    }

    public TciParameterTypeList getTypeParameters() {
        return typeParameters;
    }
}
