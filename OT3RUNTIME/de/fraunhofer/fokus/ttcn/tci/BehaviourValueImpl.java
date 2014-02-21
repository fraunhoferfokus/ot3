package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.BehaviourValue;
import org.etsi.ttcn.tci.TciModuleId;

public class BehaviourValueImpl implements BehaviourValue {
    
    private TciModuleId definingModule;
    private String name;
    
    public BehaviourValueImpl(TciModuleId mod, String nm) {
        name = nm;
        definingModule = mod;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public TciModuleId getDefiningModule() {
        return definingModule;
    }

}
