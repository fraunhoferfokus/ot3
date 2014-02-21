package de.fraunhofer.fokus.ttcn.tci;

import org.etsi.ttcn.tci.TciModuleParameter;
import org.etsi.ttcn.tci.Value;

public class TciModuleParameterImpl implements TciModuleParameter {
    
    private String name;
    private Value defaultval;
    
    public TciModuleParameterImpl(String moduleParameterName, Value defaultValue) {
        name = moduleParameterName;
        defaultval = defaultValue;
    }

    @Override
    public String getModuleParameterName() {
        return name;
    }

    @Override
    public Value getDefaultValue() {
        return defaultval;
    }

}
